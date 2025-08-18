package core.basesyntax.dreamjob.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import core.basesyntax.dreamjob.dto.job.external.GetroApiJobsRequestBody;
import core.basesyntax.dreamjob.dto.job.external.JobDetailsDto;
import core.basesyntax.dreamjob.dto.job.external.JobExternalDto;
import core.basesyntax.dreamjob.dto.job.external.GetroApiJobsResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.BatchOfJobsResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.mapper.JobMapper;
import core.basesyntax.dreamjob.mapper.OrganizationMapper;
import core.basesyntax.dreamjob.model.Job;
import core.basesyntax.dreamjob.model.Organization;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TechStarsService {
    private static final int DEFAULT_HITS_PER_PAGE = 20;
    private static final String DATA_TESTID_ATTRIBUTE = "data-testid";
    private static final String CAREER_PAGE_ATTRIBUTE_VALUE = "careerPage";
    private static final String CONTENT_ATTRIBUTE_VALUE = "content";
    private static final String TECHSTARS_COMPANIES_URL = "https://jobs.techstars.com/companies/";
    private static final Character PATH_SEPARATOR = '/';
    private static final String JOBS = "jobs";
    private static final String CONTENT = "#content";
    private static final String LOCATION_SEPARATOR = " · ";
    private static final String LABOR_FUNCTIONS_SEPARATOR = ", ";
    private static final String GETRO_API_JOBS_URL =
            "https://api.getro.com/api/v2/collections/89/search/jobs";

    private final ObjectMapper objectMapper;
    private final OkHttpClient client;
    private final JobMapper jobMapper;
    private final JobService jobService;
    private final OrganizationService organizationService;
    private final OrganizationMapper organizationMapper;

    public BatchOfJobsResponseDto fetchAndSaveBatchOfJobs(JobRequestDto requestBody) {
        if (requestBody.getHitsPerPage() == 0) {
            requestBody.setHitsPerPage(DEFAULT_HITS_PER_PAGE);
        }
        GetroApiJobsResponseDto getroApiJobsResponseDto = fetchBatchOfJobsFromGetro(requestBody);
        List<JobExternalDto> filteredJobList = getroApiJobsResponseDto.getResults().getJobs()
                .stream()
                .filter(JobExternalDto::isHasDescription)
                .toList();
        BatchOfJobsResponseDto responseDto = new BatchOfJobsResponseDto(
                getroApiJobsResponseDto.getResults().getCount(), new ArrayList<>());
        for (JobExternalDto jobExternalDto : filteredJobList) {
            Organization savedOrganization = organizationService
                    .save(organizationMapper.toModel(jobExternalDto));
            JobDetailsDto jobDetails = getJobDetails(jobExternalDto);
            Job savedJob = jobService.save(jobMapper.toModel(jobExternalDto, jobDetails));
            savedJob.setOrganization(savedOrganization);
            responseDto.jobDtos().add(jobMapper.toShortenedDto(savedJob));
        }
        return responseDto;
    }


    private GetroApiJobsResponseDto fetchBatchOfJobsFromGetro(
            JobRequestDto requestDto
    ) {
        GetroApiJobsRequestBody getroRequestBody = jobMapper.toGetroRequestDto(requestDto);
        RequestBody requestBody;
        try {
            requestBody = RequestBody
                    .create(objectMapper.writeValueAsBytes(getroRequestBody));
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Can't convert request body object to bytes. "
                    + "Request body object: " + getroRequestBody, e);
        }
        Request request = new Request.Builder()
                .url(GETRO_API_JOBS_URL)
                .post(requestBody)
                .addHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .addHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
                .build();
        Response response;
        try {
            response = client.newCall(request).execute();
        } catch (IOException e) {
            throw new RuntimeException("Can't execute HTTP call to: " + request.url(), e);
        }
        try {
            return objectMapper
                    .readValue(response.body().string(), GetroApiJobsResponseDto.class);
        } catch (IOException e) {
            throw new RuntimeException("Can't read response body", e);
        }
    }

    private JobDetailsDto getJobDetails(JobExternalDto job) {
        String url = new StringBuilder(TECHSTARS_COMPANIES_URL)
                .append(job.getOrganization().getSlug())
                .append(PATH_SEPARATOR)
                .append(JOBS)
                .append(PATH_SEPARATOR)
                .append(job.getSlug())
                .append(CONTENT)
                .toString();
        Document doc;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Can't connect to url. Url: " + url, e);
        }
        String description = doc
                .getElementsByAttributeValue(DATA_TESTID_ATTRIBUTE, CAREER_PAGE_ATTRIBUTE_VALUE)
                .getFirst().html();
        Element contentData = doc.getElementsByAttributeValue(DATA_TESTID_ATTRIBUTE,
                        CONTENT_ATTRIBUTE_VALUE).getFirst()
                .child(0)
                .child(0)
                .child(0)
                .child(1)
                .child(0);
        String laborFunctionData = contentData
                .child(0)
                .text();
        List<String> laborFunctions = Arrays.asList(laborFunctionData
                .split(LABOR_FUNCTIONS_SEPARATOR));
        String locationData = contentData
                .child(1)
                .text();
        List<String> locations = Arrays.asList(locationData.split(LOCATION_SEPARATOR));
        return new JobDetailsDto(url,
                new HashSet<>(laborFunctions),
                description,
                new HashSet<>(locations));
    }
}
