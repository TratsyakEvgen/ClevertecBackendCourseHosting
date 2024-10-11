package ru.clevertec.hosting.dto.channel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull(message = "ChannelDTO must not be null")
public class ChannelDTO extends UpdateChannelDTO {
    @Min(value = 1, message = "Author id must be greater than 0")
    private long authorId;
}
