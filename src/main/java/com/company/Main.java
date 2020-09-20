package com.company;


import com.company.parser.RSSCollector;
import com.company.parser.RSSComponent;
import java.io.IOException;


public class Main {

    public static void main(String[] args) throws IOException {


        RSSCollector rssCollector = new RSSCollector();


        RSSComponent retailRssComponent = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(retailRssComponent);

        RSSComponent retailPressRssComponent = new RSSComponent("https://www.retail.ru/rss/press_releases/", "E, dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(retailPressRssComponent);

        RSSComponent yandexInternetRssComponent = new RSSComponent("https://news.yandex.ru/internet.rss", "dd MMM yyyy HH:mm:ss Z");

        rssCollector.collectRSSElements(yandexInternetRssComponent);

        rssCollector.sort("url");

//        rssCollector.filter("Сотрудничество Tork и СВЧ поможет поварам стать профессионалами в области гигиены", "title");

        rssCollector.filterByDate("20 Sep 2020 12:33:41", "dd MMM yyyy HH:mm:ss");

        rssCollector.toPrint(200,"H:\\Parsing result.html");

    }
}
