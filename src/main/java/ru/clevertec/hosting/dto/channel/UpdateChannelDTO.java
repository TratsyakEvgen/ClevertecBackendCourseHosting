package ru.clevertec.hosting.dto.channel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull
public class UpdateChannelDTO {
    @NotBlank(message = "Name mustn't be empty")
    private String name;
    @NotBlank(message = "Description mustn't be empty")
    private String description;
    @Min(value = 1, message = "Language id must be greater than 0")
    private long languageId;
    @Min(value = 1, message = "Category id must be greater than 0")
    private long categoryId;
    @NotBlank(message = "Link image mustn't be empty")
    @Pattern(
            regexp = "^((https?|ftp|smtp)://)?(www.)?[a-z0-9]+\\.[a-z]+(/[a-zA-Z0-9#]+/?)*$",
            message = "Incorrect format image link"
    )
    private String image;
}
