package ru.clevertec.hosting.service;

import jakarta.validation.Valid;
import ru.clevertec.hosting.dto.channel.ChannelDTO;
import ru.clevertec.hosting.dto.channel.ChannelFilterDTO;
import ru.clevertec.hosting.dto.channel.ChannelInfoDTO;
import ru.clevertec.hosting.dto.channel.UpdateChannelDTO;
import ru.clevertec.hosting.dto.page.PageDTO;

public interface ChannelService {
    PageDTO findChannels(@Valid ChannelFilterDTO channelFilterDTO);

    ChannelInfoDTO findChannel(long id);

    ChannelInfoDTO create(@Valid ChannelDTO channelDTO);

    ChannelInfoDTO update(long id, @Valid UpdateChannelDTO updateChannelDTO);
}

