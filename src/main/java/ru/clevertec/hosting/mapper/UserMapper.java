package ru.clevertec.hosting.mapper;

import org.mapstruct.Mapper;
import ru.clevertec.hosting.dto.user.UpdateUserDTO;
import ru.clevertec.hosting.dto.user.UserDTO;
import ru.clevertec.hosting.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User getUser(UpdateUserDTO updateUserDTO);

    UserDTO getUserDTO(User user);
}
