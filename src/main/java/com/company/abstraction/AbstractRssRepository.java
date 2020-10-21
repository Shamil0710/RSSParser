package com.company.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRssRepository {

    List<RSSElement> rssElements = new ArrayList<>();

    public List<RSSElement> getRssElements() {
        return rssElements;
    }

    //TODO После слияния веток сюда будут перенесенны методы связанне с фильтрацией, поиском и выводом.

}
