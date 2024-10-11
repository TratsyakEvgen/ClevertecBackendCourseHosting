package ru.clevertec.hosting.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.hosting.dto.channel.ChannelDTO;
import ru.clevertec.hosting.dto.channel.ChannelFilterDTO;
import ru.clevertec.hosting.dto.channel.ChannelInfoDTO;
import ru.clevertec.hosting.dto.channel.UpdateChannelDTO;
import ru.clevertec.hosting.dto.page.PageDTO;
import ru.clevertec.hosting.service.ChannelService;

@RestController
@RequestMapping("/channels")
@RequiredArgsConstructor
public class ChannelController {
    private final ChannelService channelService;

    @GetMapping
    public PageDTO getChannels(@ModelAttribute ChannelFilterDTO channelFilterDTO) {
        return channelService.findChannels(channelFilterDTO);
    }

    @GetMapping("/{id}")
    public ChannelInfoDTO getChannel(@PathVariable long id) {
        return channelService.findChannel(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ChannelInfoDTO create(@RequestBody ChannelDTO channelDTO) {
        return channelService.create(channelDTO);
    }

    @PatchMapping("/{id}")
    public ChannelInfoDTO update(@PathVariable long id, @RequestBody UpdateChannelDTO updateChannelDTO) {
        return channelService.update(id, updateChannelDTO);
    }
}
