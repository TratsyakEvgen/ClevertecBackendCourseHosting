package ru.clevertec.hosting.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends UpdateUserDTO {
    private long userId;
}
