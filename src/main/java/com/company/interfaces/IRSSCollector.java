package com.company.interfaces;

import com.company.abstraction.AbstractRSSComponent;
import com.company.abstraction.AbstractRSSElement;
import com.company.abstraction.AbstractRssRepository;
import com.company.parser.RSSComponent;
import com.company.parser.RSSElement;
import com.company.parser.RssRepository;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;


public interface IRSSCollector {

    List<RSSElement> collectRSSElements(AbstractRSSComponent component, List<AbstractRSSElement> rssElements);

}
