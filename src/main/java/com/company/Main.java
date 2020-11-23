package com.company;


import com.company.collectors.RetailCollector;
import com.company.collectors.XMLCollector;
import com.company.interfaces.IRSSCollector;
import com.company.interfaces.RssRepository;
import com.company.parser.*;
import com.company.repositories.RetailRepository;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, JAXBException { ;

        List<RSSElement> rssElements = new ArrayList<RSSElement>();
        RSSComponent rssComponentJsoup = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");
        RSSComponent rssComponentXML = new RSSComponent("H:\\Parsing result.html", "E, dd MMM yyyy HH:mm:ss Z");
//        rssElements = RSSCollectorJsoup.collectRSSElements(rssComponentJsoup, rssElements);
//        rssElements = RSSCollectorXML.collectRSSElements(rssComponentXML, rssElements);
//        rssElements = RssRepository.sort(rssElements, "publicationDate");
//        rssElements = RssRepository.filter("Ap", "title", rssElements);

        IRSSCollector<RSSElement, RSSComponent> retailCollector = new RetailCollector();
        IRSSCollector<RSSElement, RSSComponent> XMLCollector = new XMLCollector();
        RssRepository<RSSElement> retailRepository = new RetailRepository(rssComponentJsoup, retailCollector);
        RssRepository<RSSElement> XMLRepository = new RetailRepository(rssComponentXML, XMLCollector);
        rssElements = retailRepository.getAll();
        rssElements.addAll(XMLRepository.getAll());
        RSSPrinter.printToConsole(Property.NUMBER_OF_LINES, Property.SAVE_DIRECTORY, rssElements);
    }
}
