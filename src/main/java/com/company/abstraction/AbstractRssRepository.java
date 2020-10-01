package com.company.abstraction;

import com.company.parser.RSSElement;

import java.util.ArrayList;
import java.util.List;

public class AbstractRssRepository {

    List<AbstractRSSElement> rssElements = new ArrayList<>();

    public List<AbstractRSSElement> getRssElements() {
        return rssElements;
    }

}
