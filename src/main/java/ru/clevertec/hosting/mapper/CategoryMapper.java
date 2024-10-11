package ru.clevertec.hosting.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.hosting.dto.category.CategoryDTO;
import ru.clevertec.hosting.entity.Category;
@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryDTO getCategoryDto(Category category);
}
