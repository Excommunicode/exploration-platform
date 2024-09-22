package kz.orbitalis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kz.orbitalis.dto.comment.CommentDto;
import kz.orbitalis.dto.event.EventFullDto;
import kz.orbitalis.enums.CommentSort;
import kz.orbitalis.enums.EventSort;
import kz.orbitalis.service.api.CommentPublicService;
import kz.orbitalis.service.api.EventPublicService;
import kz.orbitalis.service.api.RatingPublicService;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDateTime;
import java.util.List;

import static kz.orbitalis.constant.EventConstant.DATE_TIME_FORMATTER;
import static kz.orbitalis.constant.UserConstant.INITIAL_X;
import static kz.orbitalis.constant.UserConstant.LIMIT;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/events")
public class EventPublicController {
    private final EventPublicService eventPublicService;
    private final CommentPublicService commentPublicService;
    private final RatingPublicService ratingPublicService;

    @GetMapping
    public List<EventFullDto> getPublicEventDto(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) List<Long> categories,
            @RequestParam(required = false) boolean paid,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime rangeStart,
            @RequestParam(required = false) @DateTimeFormat(pattern = DATE_TIME_FORMATTER) LocalDateTime rangeEnd,
            @RequestParam(defaultValue = "false") Boolean onlyAvailable,
            @PositiveOrZero @RequestParam(defaultValue = INITIAL_X) int from,
            @Positive @RequestParam(defaultValue = LIMIT) int size,
            @RequestParam(required = false) EventSort sort,
            HttpServletRequest httpServletRequest) {
        return eventPublicService.getEventsDto(text, categories, paid, rangeStart, rangeEnd, onlyAvailable, sort, from, size, httpServletRequest);
    }

    @GetMapping("/{id}")
    public EventFullDto getByIdPublic(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        return eventPublicService.getEventByIdPublic(id, httpServletRequest);
    }

    @GetMapping("/{eventId}/comments")
    public List<CommentDto> getAllCommentsDto(@PathVariable Long eventId,
                                              @RequestParam(required = false) CommentSort commentSort,
                                              @PositiveOrZero @RequestParam(defaultValue = INITIAL_X) int from,
                                              @Positive @RequestParam(defaultValue = LIMIT) int size) {
        return commentPublicService.findCommentsDtoById(eventId, commentSort, from, size);
    }

    @GetMapping("/{eventId}/comments/count")
    public long countComments(@PathVariable Long eventId) {
        return commentPublicService.countCommentsByEventId(eventId);
    }

    @GetMapping("/ratings/{eventId}/avg-assessment")
    public double getAvgAssessment(@PathVariable Long eventId) {
        return ratingPublicService.getAvgAssessment(eventId);
    }
}