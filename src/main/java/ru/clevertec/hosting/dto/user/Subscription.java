package ru.clevertec.hosting.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Subscription {
    private long channelId;
    private String name;
}
