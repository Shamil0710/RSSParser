package com.company;

import com.company.parser.RSSCompareDate;
import com.company.parser.RSSComponent;
import com.company.parser.RSSElement;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Collection;


public class Main {

    public static void main(String[] args) throws ParseException, IOException {

        RSSComponent alltodayMarketRssComponent = new RSSComponent("http://www.alltoday.ru/rss2/rss_market.xml", "E, d MMM yyyy HH:mm:ss Z");
        RSSCollector rssCollector = new RSSCollector();

        rssCollector.connectAndGetItems(alltodayMarketRssComponent);
        rssCollector.collectRSSElements(alltodayMarketRssComponent, true);

        //todo

       RSSComponent lentaRssComponent = new RSSComponent("https://lenta.ru/rss/news", "E, dd MMM yyyy HH:mm:ss Z");


        rssCollector.connectAndGetItems(lentaRssComponent);
        rssCollector.collectRSSElements(lentaRssComponent, true);

        RSSComponent retailRssComponent = new RSSComponent("https://www.retail.ru/rss/news/", "E, dd MMM yyyy HH:mm:ss Z");

        rssCollector.connectAndGetItems(retailRssComponent);
        rssCollector.collectRSSElements(retailRssComponent, true);

        //Тестовый комператор
        RSSCompareDate compareDate = new RSSCompareDate();

        rssCollector.getRssElements().sort(compareDate);

        rssCollector.toPrint(rssCollector);

//
//        for (int i = 0; i < rssCollector.rssElements.size(); i++){
//
//           System.out.println(rssCollector.rssElements.get(i).getTitle());
//            System.out.println(rssCollector.rssElements.get(i).getUrl());
//            System.out.println(rssCollector.rssElements.get(i).getPublicationDate());
//            System.out.println();
//        }
//
//        FileWriter f = new FileWriter("H:\\A.html", false);
//        BufferedWriter bw = new BufferedWriter(f);
//
//        for (int i = rssCollector.getRssElements().size() - 1; i >= rssCollector.getRssElements().size() - 50; i--){
//
//            bw.write(rssCollector.getRssElements().get(i).getTitle() + "<br>");
//
//            bw.write("<a href=\"" + rssCollector.getRssElements().get(i).getUrl() + ">" + rssCollector.getRssElements().get(i).getUrl() + "</a>" + "<br>");
//
//            bw.write(rssCollector.getRssElements().get(i).getPublicationDate().toString() + "<br>");
//
//            bw.write("<p>");
//
//        }

     


    }
}
