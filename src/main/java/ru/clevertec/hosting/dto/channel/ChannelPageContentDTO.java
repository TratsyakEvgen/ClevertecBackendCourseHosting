package ru.clevertec.hosting.dto.channel;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import ru.clevertec.hosting.dto.category.CategoryDTO;
import ru.clevertec.hosting.dto.language.LanguageDTO;

@Setter
@Getter
@Accessors(chain = true)
public class ChannelPageContentDTO {
    private long channelId;
    private String name;
    private long subscribesCount;
    private String image;
    private CategoryDTO category;
    private LanguageDTO language;
}
