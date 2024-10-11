package ru.clevertec.hosting.dto.page;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
public class PageDTO {
    private List<?> content;
    private int page;
    private int size;
    private int totalElements;
    private int totalPages;
}
