package kz.orbitalis.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import kz.orbitalis.dto.category.CategoryDto;
import kz.orbitalis.service.api.CategoryService;

import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

import static kz.orbitalis.constant.UserConstant.INITIAL_X;
import static kz.orbitalis.constant.UserConstant.LIMIT;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryPublicController {
    private final CategoryService categoryService;

    @GetMapping
    public List<CategoryDto> getCategoriesDto(@PositiveOrZero @RequestParam(defaultValue = INITIAL_X) int from,
                                              @Positive @RequestParam(defaultValue = LIMIT) int size) {
        return categoryService.getCategories(from, size);
    }

    @GetMapping("/{catId}")
    public CategoryDto getCategoryDto(@PathVariable Long catId) {
        return categoryService.getCategoryDto(catId);
    }
}