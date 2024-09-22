package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import kz.orbitalis.dto.registration.EventRequestStatusUpdateResult;
import kz.orbitalis.dto.registration.ParticipationRequestDto;
import kz.orbitalis.enums.ParticipationRequestStatus;
import kz.orbitalis.model.ParticipationRequest;

import java.time.LocalDateTime;
import java.util.List;

import static kz.orbitalis.constant.EventConstant.DATE_TIME_FORMATTER;

@Mapper(componentModel = "spring")
public interface ParticipationMapper {

    @Mappings({
            @Mapping(target = "event", source = "event.id"),
            @Mapping(target = "requester", source = "requester.id"),
            @Mapping(target = "created", source = "created", dateFormat = DATE_TIME_FORMATTER)
    })
    ParticipationRequestDto toDto(ParticipationRequest participationRequest);

    @Mappings({
            @Mapping(target = "requester.id", source = "userId"),
            @Mapping(target = "event.id", source = "event"),
            @Mapping(target = "created", source = "created", dateFormat = DATE_TIME_FORMATTER)
    })
    ParticipationRequest toModel(Long userId, Long event, LocalDateTime created, ParticipationRequestStatus status);

    List<ParticipationRequestDto> toListDto(List<ParticipationRequest> events);

    EventRequestStatusUpdateResult toUpdateResult(List<ParticipationRequestDto> confirmedRequests,
                                                  List<ParticipationRequestDto> rejectedRequests);
}