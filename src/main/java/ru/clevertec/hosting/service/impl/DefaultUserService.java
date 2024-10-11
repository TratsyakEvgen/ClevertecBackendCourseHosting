package ru.clevertec.hosting.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.clevertec.hosting.dto.user.Subscription;
import ru.clevertec.hosting.dto.user.UpdateUserDTO;
import ru.clevertec.hosting.dto.user.UserDTO;
import ru.clevertec.hosting.entity.Channel;
import ru.clevertec.hosting.entity.User;
import ru.clevertec.hosting.mapper.ChannelMapper;
import ru.clevertec.hosting.mapper.UserMapper;
import ru.clevertec.hosting.repository.ChannelRepository;
import ru.clevertec.hosting.repository.UserRepository;
import ru.clevertec.hosting.service.ServiceException;
import ru.clevertec.hosting.service.UserService;

import java.util.List;

@Service
@Transactional
@Validated
@RequiredArgsConstructor
public class DefaultUserService implements UserService {
    private final UserMapper userMapper;
    private final ChannelMapper channelMapper;
    private final UserRepository userRepository;
    private final ChannelRepository channelRepository;

    @Override
    public UserDTO create(UpdateUserDTO updateUserDTO) {
        User user = userMapper.getUser(updateUserDTO);
        userRepository.save(user);
        return userMapper.getUserDTO(user);
    }

    @Override
    public UserDTO update(long id, UpdateUserDTO updateUserDTO) {
        User user = userMapper.getUser(updateUserDTO);
        userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id " + id + " not found"));
        user.setUserId(id);
        userRepository.save(user);
        return userMapper.getUserDTO(user);
    }

    @Override
    public List<Subscription> getSubscription(long id) {
        return getUserById(id).getSubscriptions()
                .stream()
                .map(channelMapper::getSubscription)
                .toList();
    }

    @Override
    public void subscribe(long userId, long channelId) {
        Channel channel = findChannelById(channelId);
        getUserById(userId).getSubscriptions().add(channel);
    }


    @Override
    public void unsubscribe(long userId, long channelId) {
        Channel channel = findChannelById(channelId);
        getUserById(userId).getSubscriptions().remove(channel);
    }

    private Channel findChannelById(long channelId) {
        return channelRepository.findById(channelId)
                .orElseThrow(() -> new ServiceException("Channel with id " + channelId + " not found"));
    }

    private User getUserById(long userId) {
        return userRepository.findByUserId(userId)
                .orElseThrow(() -> new ServiceException("User with id " + userId + " not found"));
    }
}
