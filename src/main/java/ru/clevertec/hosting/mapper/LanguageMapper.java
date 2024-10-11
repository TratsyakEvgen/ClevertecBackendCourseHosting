package ru.clevertec.hosting.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.hosting.dto.language.LanguageDTO;
import ru.clevertec.hosting.entity.Language;
@Mapper(componentModel = "spring")
public interface LanguageMapper {
    LanguageDTO getLanguageDTO(Language language);

}
