package core.basesyntax.dreamjob.mapper;

import core.basesyntax.dreamjob.config.MapperConfig;
import core.basesyntax.dreamjob.dto.job.external.JobExternalDto;
import core.basesyntax.dreamjob.model.Organization;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)

public interface OrganizationMapper {
    @Mapping(target = "id", source = "jobExternalDto.organization.id")
    @Mapping(target = "title", source = "jobExternalDto.organization.name")
    @Mapping(target = "logoUrl", source = "jobExternalDto.organization.logoUrl")
    @Mapping(target = "industryTags", source = "jobExternalDto.organization.industryTags")
    Organization toModel(JobExternalDto jobExternalDto);
}
