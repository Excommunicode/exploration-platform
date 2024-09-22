package kz.orbitalis.service.api;

import kz.orbitalis.dto.rating.NewRatingDto;
import kz.orbitalis.dto.rating.RatingDto;

public interface RatingPrivateService {
    RatingDto addRatingDto(Long userId, Long eventId, NewRatingDto ratingDto);

    RatingDto updateRatingDto(Long userId, Long ratingId, NewRatingDto newRatingDto);

    void deleteRatingDto(Long userId, Long ratingId);
}