package com.company.abstraction;

import com.company.interfaces.IRSSCollector;
import com.company.interfaces.RssRepository;

import java.util.List;

public abstract class AbstractRssRepository<C extends AbstractRSSComponent> implements RssRepository<AbstractRSSElement> {

    private final C component;
    private final IRSSCollector collector;

    public AbstractRssRepository(C component, IRSSCollector collector){
        this.component = component;
        this.collector = collector;
    }

    //коллектор получает элементы и обрабатывает их с помощью какогото компонента
    protected List<AbstractRSSElement> connectAndCollect(){
        return this.collector.collectRSSElements(this.component);
    }



//    List<AbstractRSSElement> rssElements = new ArrayList<>();
//    public List<AbstractRSSElement> getRssElements() {
//        return rssElements;
//    }

    //TODO После слияния веток сюда будут перенесенны методы связанне с фильтрацией, поиском и выводом.

}
