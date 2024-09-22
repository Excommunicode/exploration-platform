package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import kz.orbitalis.dto.rating.NewRatingDto;
import kz.orbitalis.dto.rating.RatingDto;
import kz.orbitalis.model.Rating;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingMapper {

    @Mappings({
            @Mapping(target = "user.id", source = "userId"),
            @Mapping(target = "event.id", source = "eventId")
    })
    Rating toModel(Long userId, Long eventId, NewRatingDto newRatingDto);

    RatingDto toDto(Rating ratingDto);


    RatingDto toDtoAfterUpdate(Long id, Long userId, NewRatingDto newRatingDto);

    List<RatingDto> toDtoList(List<Rating> ratings);

    List<Rating> toModelList(List<RatingDto> ratingDtoList);
}
