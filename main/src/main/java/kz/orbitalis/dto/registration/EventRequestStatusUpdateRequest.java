package kz.orbitalis.dto.registration;

import lombok.Data;
import kz.orbitalis.enums.ParticipationRequestStatus;

import java.util.List;

@Data
public class EventRequestStatusUpdateRequest {
    private List<Long> requestIds;
    private ParticipationRequestStatus status;
}
