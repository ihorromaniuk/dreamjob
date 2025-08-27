package core.basesyntax.dreamjob.mapper;

import core.basesyntax.dreamjob.config.MapperConfig;
import core.basesyntax.dreamjob.dto.job.external.GetroApiJobsRequestBody;
import core.basesyntax.dreamjob.dto.job.external.JobDetailsDto;
import core.basesyntax.dreamjob.dto.job.external.JobExternalDto;
import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.dto.job.internal.JobResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobsShortenedResponseDto;
import core.basesyntax.dreamjob.model.Job;
import java.util.HashSet;
import java.util.Set;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface JobMapper {
    @Mapping(target = "locations", source = "jobDetailsDto.locations")
    @Mapping(target = "jobPageUrl", source = "jobDetailsDto.techStarsUrl")
    @Mapping(target = "positionName", source = "jobExternalDto.title")
    @Mapping(target = "organizationJobPageUrl", source = "jobExternalDto.url")
    @Mapping(target = "postedDate", source = "jobExternalDto.createdAt")
    @Mapping(target = "updatedAt", ignore = true)
    Job toModel(JobExternalDto jobExternalDto, JobDetailsDto jobDetailsDto);

    @Mapping(target = "logoUrl", source = "organization.logoUrl")
    @Mapping(target = "organizationTitle", source = "organization.title")
    @Mapping(target = "tags", source = "job", qualifiedByName = "setTags")
    JobResponseDto toDto(Job job);

    GetroApiJobsRequestBody toGetroRequestDto(JobRequestDto requestDto);

    @Mapping(target = "logoUrl", source = "organization.logoUrl")
    @Mapping(target = "organizationTitle", source = "organization.title")
    JobsShortenedResponseDto toShortenedDto(Job job);

    @Named("setTags")
    default Set<String> setTags(Job job) {
        Set<String> tags = new HashSet<>(job.getOrganization().getIndustryTags());
        tags.add(job.getSeniority());
        return tags;
    }
}
