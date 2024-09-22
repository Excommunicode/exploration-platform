package kz.orbitalis.dto.event;

import lombok.Builder;
import lombok.Data;
import kz.orbitalis.dto.category.CategoryDto;
import kz.orbitalis.dto.user.UserShortDto;
import kz.orbitalis.model.Location;
import kz.orbitalis.enums.State;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class UpdateEventUserRequestOutput {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private Integer confirmedRequests;
    private LocalDateTime createdOn;
    private String description;
    private String eventDate;
    private UserShortDto initiator;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    private String title;
    private Integer views;
}