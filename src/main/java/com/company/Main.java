package com.company;


import com.company.parser.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Main {

    public static void main(String[] args) throws IOException, JAXBException {
//        RSSCollector rssCollector = new RSSCollector();
//
//        RSSComponent retailRssComponent = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");
//
//        rssCollector.collectRSSElements(retailRssComponent);
//
//        RSSComponent retailPressRssComponent = new RSSComponent("https://www.retail.ru/rss/press_releases/", "E, dd MMM yyyy HH:mm:ss Z");
//
//        rssCollector.collectRSSElements(retailPressRssComponent);
//
//        RSSComponent yandexInternetRssComponent = new RSSComponent("https://news.yandex.ru/internet.rss", "dd MMM yyyy HH:mm:ss Z");
//
//        rssCollector.collectRSSElements(yandexInternetRssComponent);
//        RSSComponent rssComponentXmlSimple = new RSSComponent("H:\\www.retail.ru.xml", "E, dd MMM yyyy HH:mm:ss Z");
//
//        rssCollector.collectRSSElementsXML(rssComponentXmlSimple);
//        rssCollector.parseJAXB(rssComponentXmlSimple);
//
//        rssCollector.toPrintAndSort(200, false, "H:\\Parsing result.html");

        List<RSSElement> rssElements = new ArrayList<RSSElement>();
        RSSComponent rssComponentJsoup = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");
        RSSComponent rssComponentXML = new RSSComponent("H:\\Parsing result.html", "E, dd MMM yyyy HH:mm:ss Z");
        rssElements = RSSCollectorJsoup.collectRSSElements(rssComponentJsoup, rssElements);
        rssElements = RSSCollectorXML.collectRSSElements(rssComponentXML, rssElements);
        rssElements = RssRepository.sort(rssElements, "publicationDate");
        RSSPrinter.toPrint(Property.NUMBER_OF_LINES, Property.SAVE_DIRECTORY, rssElements);
    }
}
