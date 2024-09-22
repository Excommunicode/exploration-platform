package kz.orbitalis.dto.registration;

import lombok.Builder;
import lombok.Data;
import kz.orbitalis.enums.ParticipationRequestStatus;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
public class ParticipationRequestDto {
    private Long id;
    private Long requester;
    private Long event;
    private LocalDateTime created;
    private ParticipationRequestStatus status;
}