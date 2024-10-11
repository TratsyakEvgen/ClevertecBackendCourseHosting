package ru.clevertec.hosting.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import ru.clevertec.hosting.dto.channel.*;
import ru.clevertec.hosting.dto.page.PageDTO;
import ru.clevertec.hosting.entity.Category;
import ru.clevertec.hosting.entity.Channel;
import ru.clevertec.hosting.entity.Language;
import ru.clevertec.hosting.entity.User;
import ru.clevertec.hosting.mapper.ChannelMapper;
import ru.clevertec.hosting.mapper.PageMapper;
import ru.clevertec.hosting.repository.CategoryRepository;
import ru.clevertec.hosting.repository.ChannelRepository;
import ru.clevertec.hosting.repository.LanguageRepository;
import ru.clevertec.hosting.repository.UserRepository;
import ru.clevertec.hosting.service.ChannelService;
import ru.clevertec.hosting.service.ServiceException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Validated
public class DefaultChannelService implements ChannelService {
    private final ChannelMapper channelMapper;
    private final ChannelRepository channelRepository;
    private final PageMapper pageMapper;
    private final LanguageRepository languageRepository;
    private final CategoryRepository categoryRepository;
    private final UserRepository userRepository;

    @Override
    public PageDTO findChannels(ChannelFilterDTO channelFilterDTO) {
        ChannelFilter filter = channelMapper.getChannelFilter(channelFilterDTO);
        Pageable pageable = PageRequest.of(channelFilterDTO.getPage(), channelFilterDTO.getSize());

        Page<Channel> pageChannels = channelRepository.findAllUsingFilter(filter, pageable);

        List<Long> channelsId = pageChannels.getContent()
                .stream()
                .map(Channel::getChannelId)
                .toList();

        List<ChannelPageContentDTO> content = channelRepository.findAllByChannelIdIn(channelsId)
                .stream()
                .map(channelMapper::getChannelPageContentDto)
                .toList();

        return pageMapper.getPageDTO(pageChannels).setContent(content);
    }


    @Override
    public ChannelInfoDTO findChannel(long id) {
        return channelRepository.findByChannelId(id)
                .map(channelMapper::getChannelInfoDto)
                .orElseThrow(() -> new ServiceException("Channel with id " + id + " not found"));
    }

    @Override
    public ChannelInfoDTO create(ChannelDTO channelDTO) {
        Channel channel = channelMapper.getChannel(channelDTO)
                .setCreateDate(LocalDate.now());
        setAuthor(channel);
        setLanguage(channel);
        setCategory(channel);
        channelRepository.save(channel);
        return channelMapper.getChannelInfoDto(channel);
    }

    @Override
    public ChannelInfoDTO update(long id, UpdateChannelDTO updateChannelDTO) {
        Channel channel = channelMapper.getChannel(updateChannelDTO);
        Channel repositoryChannel = channelRepository.findByChannelId(id)
                .orElseThrow(() -> new ServiceException("Channel with id " + id + " not found"));
        setCategory(channel);
        setLanguage(channel);
        channelMapper.updateOnlyNull(repositoryChannel, channel);
        channelRepository.save(channel);
        return channelMapper.getChannelInfoDto(channel);
    }

    private void setAuthor(Channel channel) {
        long userId = channel.getAuthor().getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ServiceException("User with id " + userId + " not found"));
        channel.setAuthor(user);
    }

    private void setLanguage(Channel channel) {
        long languageId = channel.getLanguage().getLanguageId();
        Language language = languageRepository.findById(languageId)
                .orElseThrow(() -> new ServiceException("Language with id " + languageId + " not found"));
        channel.setLanguage(language);
    }


    private void setCategory(Channel channel) {
        long categoryId = channel.getCategory().getCategoryId();
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ServiceException("Category with id " + categoryId + " not found"));
        channel.setCategory(category);
    }

}
