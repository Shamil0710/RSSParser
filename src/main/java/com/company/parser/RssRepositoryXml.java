package com.company.parser;

import com.company.abstraction.AbstractRSSElement;
import com.company.abstraction.AbstractRssRepository;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class RssRepositoryXml extends AbstractRssRepository {

    List<AbstractRSSElement> rssElements = new ArrayList<>();

    @XmlElementWrapper(name = "channel")
    public void setRssElements(List<AbstractRSSElement> rssElements) {
        this.rssElements = rssElements;
    }

}
