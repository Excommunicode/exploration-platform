package kz.orbitalis.dto.event;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import kz.orbitalis.dto.category.CategoryDto;
import kz.orbitalis.enums.StateAction;
import kz.orbitalis.model.Location;

@Data
public class UpdateEventAdminRequest {

    @Length(min = 20, max = 2000)
    private String annotation;

    private CategoryDto categoryDto;

    @Length(min = 20, max = 7000)
    private String description;

    private String eventDate;
    private Location location;
    private Boolean paid;
    private Integer participantLimit;
    private Boolean requestModeration;
    private StateAction stateAction;

    @Length(min = 3, max = 120)
    private String title;
}
