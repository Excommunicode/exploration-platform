package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import kz.orbitalis.dto.EndpointDto;
import kz.orbitalis.model.Statistic;

import static kz.orbitalis.constant.StatisticConstant.DATE_TIME_FORMATTER;

@Mapper(componentModel = "spring")
public interface StatisticMapper {
    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "timestamp", source = "timestamp", dateFormat = DATE_TIME_FORMATTER)
    })
    Statistic toEndpointDto(EndpointDto endpointDto);
}
