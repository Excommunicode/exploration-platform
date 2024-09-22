package kz.orbitalis.dto.event;

import lombok.Builder;
import lombok.Data;
import kz.orbitalis.dto.category.CategoryDto;
import kz.orbitalis.dto.user.UserShortDto;

@Data
@Builder(toBuilder = true)
public class EventShortDto {
    private Long id;
    private String annotation;
    private CategoryDto category;
    private Long confirmedRequests;
    private String eventDate;
    private UserShortDto initiator;
    private Boolean paid;
    private String title;
    private Long views;
}