package ru.clevertec.hosting.dto.channel;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NotNull(message = "ChannelFilterDTO must not be null")
public class ChannelFilterDTO extends ChannelFilter {
    @Min(value = 0, message = "Page id must be greater than or equal to 0")
    private int page;
    @Min(value = 1, message = "Size id must be greater than 0")
    private int size;
}
