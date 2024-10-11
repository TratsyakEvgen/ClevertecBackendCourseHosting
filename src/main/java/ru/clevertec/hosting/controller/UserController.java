package ru.clevertec.hosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.hosting.dto.user.Subscription;
import ru.clevertec.hosting.dto.user.UpdateUserDTO;
import ru.clevertec.hosting.dto.user.UserDTO;
import ru.clevertec.hosting.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}/subscriptions")
    public List<Subscription> getSubscriptions(@PathVariable long id) {
        return userService.getSubscription(id);
    }

    @PostMapping("/{userId}/channels/{channelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void subscribe(@PathVariable long userId, @PathVariable long channelId) {
        userService.subscribe(userId, channelId);
    }

    @PostMapping
    public UserDTO create(@RequestBody UpdateUserDTO updateUserDTO) {
        return userService.create(updateUserDTO);
    }

    @PutMapping("/{id}")
    public UserDTO update(@PathVariable long id, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.update(id, updateUserDTO);
    }

    @DeleteMapping("/{userId}/channels/{channelId}")
    public void unsubscribe(@PathVariable long userId, @PathVariable long channelId) {
        userService.unsubscribe(userId, channelId);
    }
}
