package ru.clevertec.hosting.dto.channel;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelFilter {
    private String name;
    @Min(value = 1, message = "Language id must be greater than 0")
    private Long languageId;
    @Min(value = 1, message = "Category id must be greater than 0")
    private Long categoryId;
}
