package kz.orbitalis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kz.orbitalis.dto.event.EventFullDto;
import kz.orbitalis.dto.event.UpdateEventAdminRequest;
import kz.orbitalis.service.api.CommentAdminService;
import kz.orbitalis.service.api.EventAdminService;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

import static kz.orbitalis.constant.EventConstant.DATE_TIME_FORMATTER;
import static kz.orbitalis.constant.UserConstant.INITIAL_X;
import static kz.orbitalis.constant.UserConstant.LIMIT;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/admin/events")
public class EventAdminController {
    private final EventAdminService eventAdminService;
    private final CommentAdminService commentAdminService;

    @GetMapping
    public List<EventFullDto> findEventForAdmin(
            @RequestParam(required = false) List<Long> users,
            @RequestParam(required = false) List<String> states,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = INITIAL_X) int from,
            @RequestParam(defaultValue = LIMIT) int size) {
        return eventAdminService.findEventForAdmin(users, states, categories, rangeStart, rangeEnd, from, size);
    }

    @PatchMapping("/{eventId}")
    public EventFullDto updateEventBydAdmin(@PathVariable Long eventId,
                                            @Valid @RequestBody UpdateEventAdminRequest updateEventAdminRequest) {
        return eventAdminService.updateEventByAdmin(eventId, updateEventAdminRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteCommentByAdmin(@PathVariable Long commentId) {
        commentAdminService.deleteCommentByAdmin(commentId);
    }
}
