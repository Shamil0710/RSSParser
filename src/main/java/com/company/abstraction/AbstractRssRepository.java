package com.company.abstraction;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRssRepository {

    //TODO бсудить как должен правильно реализовыватся паттерн репозиторий

    List<AbstractRSSElement> rssElements = new ArrayList<>();

    public List<AbstractRSSElement> getRssElements() {
        return rssElements;
    }

    //TODO После слияния веток сюда будут перенесенны методы связанне с фильтрацией, поиском и выводом.

}
