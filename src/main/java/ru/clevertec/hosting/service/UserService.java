package ru.clevertec.hosting.service;

import jakarta.validation.Valid;
import ru.clevertec.hosting.dto.user.Subscription;
import ru.clevertec.hosting.dto.user.UpdateUserDTO;
import ru.clevertec.hosting.dto.user.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO create(@Valid UpdateUserDTO updateUserDTO);

    UserDTO update(long id, @Valid UpdateUserDTO updateUserDTO);

    List<Subscription> getSubscription(long id);

    void subscribe(long userId, long channelId);

    void unsubscribe(long userId, long channelId);
}
