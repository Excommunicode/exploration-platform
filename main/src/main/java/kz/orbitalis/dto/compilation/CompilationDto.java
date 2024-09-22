package kz.orbitalis.dto.compilation;

import lombok.Builder;
import lombok.Data;
import kz.orbitalis.dto.event.EventShortDto;

import java.util.List;

@Data
@Builder(toBuilder = true)
public class CompilationDto {
    private Long id;
    private List<EventShortDto> events;
    private Boolean pinned;
    private String title;
}