package com.company.repositories.abstraction;

import com.company.collectors.interfaces.IRSSCollector;
import com.company.entities.abstraction.AbstractRSSComponent;
import com.company.entities.abstraction.AbstractRSSElement;
import com.company.repositories.interfaces.RssRepository;

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

}
