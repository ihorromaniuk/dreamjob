package core.basesyntax.dreamjob.controller;

import core.basesyntax.dreamjob.dto.exception.ExceptionDto;
import core.basesyntax.dreamjob.dto.job.internal.BatchOfJobsResponseDto;
import core.basesyntax.dreamjob.dto.job.internal.JobRequestDto;
import core.basesyntax.dreamjob.dto.job.internal.JobResponseDto;
import core.basesyntax.dreamjob.service.JobService;
import core.basesyntax.dreamjob.service.TechStarsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/jobs")
@RequiredArgsConstructor
public class JobController {
    private final TechStarsService techStarsService;
    private final JobService jobService;

    @Operation(summary = "Get job by id from local database")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Found the job",
                    content = @Content(schema = @Schema(implementation = JobResponseDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Didn't find the job",
                    content = @Content(schema = @Schema(implementation = ExceptionDto.class))
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<JobResponseDto> getJob(@PathVariable Long id) {
        return ResponseEntity.ok().body(jobService.getJobResponseById(id));
    }

    @Operation(summary = "Get all jobs from local database with pagination and filters")
    @ApiResponse(
            responseCode = "200",
            description = "Found all jobs with pagination and filters",
            content = @Content(schema = @Schema(implementation = BatchOfJobsResponseDto.class))
    )
    @PostMapping("/local")
    public ResponseEntity<BatchOfJobsResponseDto> getJobsFromLocalDb(
            @RequestBody JobRequestDto requestBody
    ) {
        return ResponseEntity.ok().body(jobService.getJobs(requestBody));
    }
}
