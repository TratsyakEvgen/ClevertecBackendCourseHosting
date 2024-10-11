package ru.clevertec.hosting.dto.channel;

import lombok.Getter;
import lombok.Setter;
import ru.clevertec.hosting.dto.user.UserDTO;

@Getter
@Setter
public class ChannelInfoDTO extends ChannelPageContentDTO {
    private String description;
    private String date;
    private UserDTO author;
}
