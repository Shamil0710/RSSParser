package com.company;


import com.company.parser.RSSCollector;
import com.company.parser.RSSComponent;
import com.company.parser.RssRepository;

import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {


        RSSCollector rssCollector = new RSSCollector();
        RssRepository repository = new RssRepository();


        RSSComponent retailRssComponent = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(retailRssComponent, repository);

        RSSComponent retailPressRssComponent = new RSSComponent("https://www.retail.ru/rss/press_releases/", "E, dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(retailPressRssComponent, repository);

        RSSComponent yandexInternetRssComponent = new RSSComponent("https://news.yandex.ru/internet.rss", "dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(yandexInternetRssComponent, repository);


        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);
        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);
        rssCollector.toPrintAndSort(50, true, "H:\\Parsing result.html", repository);

    }
}
