package com.company.mappers;

import com.company.dto.RssDto;
import com.company.mappers.interfaces.Mapper;
import com.company.entities.RSSElement;

public class RssElementsMapper implements Mapper<RSSElement, RssDto> {
    @Override
    public RssDto toDto(RSSElement element) {
        return new RssDto(element.getTitle(), element.getUrl(), element.getPublicationDate());
    }
}
