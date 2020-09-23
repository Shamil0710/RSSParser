package com.company;


import com.company.parser.RSSCollector;
import com.company.parser.RSSComponent;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import com.company.parser.*;

import javax.xml.bind.JAXBException;
import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {


//        RSSCollector rssCollector = new RSSCollector();
//
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
//
//
//        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html");

//        System.out.print(RSSCollector.readOfFile("H:\\www.retail.ru.xml"));
//
//        RSSCollector.readOfFile("H:\\www.retail.ru.xml");

        RSSCollector.collectRSSElementsXML("H:\\www.retail.ru.xml");



        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);
        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);
        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);

    }
}
