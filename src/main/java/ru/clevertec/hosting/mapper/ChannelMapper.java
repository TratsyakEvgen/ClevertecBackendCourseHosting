package ru.clevertec.hosting.mapper;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.clevertec.hosting.dto.channel.*;
import ru.clevertec.hosting.dto.user.Subscription;
import ru.clevertec.hosting.entity.Channel;

import java.util.Optional;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class, LanguageMapper.class})
public interface ChannelMapper {
    ChannelFilter getChannelFilter(ChannelFilterDTO channelFilterDTO);

    Subscription getSubscription(Channel channel);

    ChannelPageContentDTO getChannelPageContentDto(Channel channel);

    @Mapping(target = "author.userId", source = "authorId")
    @Mapping(target = "language.languageId", source = "languageId")
    @Mapping(target = "category.categoryId", source = "categoryId")
    Channel getChannel(ChannelDTO channelDTO);

    @Mapping(target = "language.languageId", source = "languageId")
    @Mapping(target = "category.categoryId", source = "categoryId")
    Channel getChannel(UpdateChannelDTO updateChannelDTO);

    @Mapping(target = "date", source = "createDate", dateFormat = "dd-MM-yyyy")
    ChannelInfoDTO getChannelInfoDto(Channel channel);

    @Mapping(target = "description", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "language", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "image", ignore = true)
    void updateOnlyNull(Channel repositoryChannel, @MappingTarget Channel channel);

    @AfterMapping
    default void updateSubscribersCount(Channel channel, @MappingTarget ChannelPageContentDTO channelPageContentDTO) {
        Optional.ofNullable(channel.getSubscribers())
                .ifPresent(users -> channelPageContentDTO.setSubscribesCount(users.size()));

    }

}
