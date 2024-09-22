package kz.orbitalis.mapper;

import org.mapstruct.Mapper;
import kz.orbitalis.dto.category.CategoryDto;
import kz.orbitalis.model.Category;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toModel(CategoryDto categoryDto);

    CategoryDto toDto(Category category);

    List<CategoryDto> toDtoList(List<Category> categories);
}
