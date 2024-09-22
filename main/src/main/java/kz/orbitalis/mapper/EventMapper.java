package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import kz.orbitalis.dto.event.EventFullDto;
import kz.orbitalis.dto.event.EventShortDto;
import kz.orbitalis.dto.event.NewEventDto;
import kz.orbitalis.dto.event.UpdateEventAdminRequest;
import kz.orbitalis.dto.event.UpdateEventUserRequest;
import kz.orbitalis.dto.event.UpdateEventUserRequestOutput;
import kz.orbitalis.enums.State;
import kz.orbitalis.enums.StateAction;
import kz.orbitalis.model.Event;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static kz.orbitalis.constant.EventConstant.DATE_TIME_FORMATTER;
import static kz.orbitalis.enums.State.CANCELED;
import static kz.orbitalis.enums.State.PENDING;
import static kz.orbitalis.enums.State.PUBLISHED;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EventMapper {
    @Mappings({
            @Mapping(target = "category.id", source = "category"),
            @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    })
    Event toModel(NewEventDto newEventDto);

    @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    EventFullDto toFullDto(Event event);

    @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    Event toModelFromFullDto(EventFullDto eventFullDto);

    List<EventFullDto> toDtoList(List<Event> events);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "category.id", source = "newEventDto.category"),
            @Mapping(target = "eventDate", source = "newEventDto.eventDate", dateFormat = DATE_TIME_FORMATTER),
            @Mapping(target = "initiator.id", source = "userId")
    })
    Event toEvent(NewEventDto newEventDto, Long userId, State state, LocalDateTime createdOn);

    @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    UpdateEventUserRequestOutput toUpdateDtoOutput(Event event);

    @Mappings({
            @Mapping(target = "participantLimit", ignore = true),
            @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    })
    EventFullDto updateEvent(@MappingTarget EventFullDto eventFullDto, UpdateEventUserRequest updateEventUserRequest);

    default EventFullDto updateEventByUser(EventFullDto eventFullDto, UpdateEventUserRequest updateEventUserRequest) {
        StateAction stateAction = updateEventUserRequest.getStateAction();
        if (Objects.nonNull(stateAction)) {
            switch (stateAction) {
                case PUBLISH_EVENT:
                    eventFullDto.setState(PUBLISHED);
                    break;
                case SEND_TO_REVIEW:
                    eventFullDto.setState(PENDING);
                    break;
                case CANCEL_REVIEW:
                case REJECT_EVENT:
                    eventFullDto.setState(CANCELED);
                    break;
            }
        }
        return updateEvent(eventFullDto, updateEventUserRequest);
    }

    @Mappings({
            @Mapping(target = "location", ignore = true),
            @Mapping(target = "eventDate", source = "eventDate", dateFormat = DATE_TIME_FORMATTER)
    })
    EventFullDto updateEventAdmin(@MappingTarget EventFullDto eventFullDto, UpdateEventAdminRequest updateEventAdminRequest);

    default EventFullDto updateEventByAdmin(EventFullDto eventFullDto, UpdateEventAdminRequest updateEventAdminRequest) {
        StateAction stateAction = updateEventAdminRequest.getStateAction();
        if (Objects.nonNull(stateAction)) {
            switch (stateAction) {
                case PUBLISH_EVENT:
                    eventFullDto.setState(PUBLISHED);
                    eventFullDto.setPublishedOn(LocalDateTime.now());
                    break;
                case SEND_TO_REVIEW:
                    eventFullDto.setState(PENDING);
                    break;
                case CANCEL_REVIEW:
                case REJECT_EVENT:
                    eventFullDto.setState(CANCELED);
                    break;
            }
        }
        return updateEventAdmin(eventFullDto, updateEventAdminRequest);
    }

    default Event toEntity(Long id) {
        if (id == null) {
            return null;
        }
        Event event = new Event();
        event.setId(id);
        return event;
    }

    List<Event> toEntityList(List<Long> ids);

    @Mapping(target = "id", source = "id")
    EventShortDto toDto(Long id);

    List<EventShortDto> toDtoListCompilation(List<Long> ids);

    List<EventFullDto> toEventFullDtoAfterIterable(Iterable<Event> eventIterable);
}