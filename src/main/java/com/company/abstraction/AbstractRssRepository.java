package com.company.abstraction;

import com.company.parser.RSSElement;

import java.util.ArrayList;
import java.util.List;

public class AbstractRssRepository {

    List<RSSElement> rssElements = new ArrayList<>();

    public List<RSSElement> getRssElements() {
        return rssElements;
    }

}
