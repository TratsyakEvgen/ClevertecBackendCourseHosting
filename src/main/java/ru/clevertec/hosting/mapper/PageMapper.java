package ru.clevertec.hosting.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import ru.clevertec.hosting.dto.page.PageDTO;

@Mapper(componentModel = "spring")
public interface PageMapper {
    @Mapping(target = "page", source = "number")
    PageDTO getPageDTO(Page<?> page);
}
