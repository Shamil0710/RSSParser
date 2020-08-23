package com.company;

import com.company.parser.RSSCompareDate;
import com.company.parser.RSSComponent;
import com.company.parser.RSSElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class RSSCollector {

    public List<RSSElement> getRssElements() {
        return rssElements;
    }

    private List<RSSElement> rssElements = new ArrayList<RSSElement>();

    //Конектимся и получаем <item>

    protected List<Element> connectAndGetItems(RSSComponent rssComponent) throws IOException {

        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag("item");

    }


    public void collectRSSElements(RSSComponent component, boolean flushAll) throws IOException {



        List<Element> elements = connectAndGetItems(component);


//Как обратится к листу в RSSComponent???
        for (Element element : elements) {
            rssElements.add((new RSSElement(
                    element.getElementsByTag("title").text(),
                    element.getElementsByTag("link").text(),
                    parseDate(element.getElementsByTag("pubDate").text(), component)

            )));

        }

        //todo так можно?
        elements.clear();

//        //Если мы перезаписываем массив
//        if (flushAll) {
//            component.setRssElements(rssElements);
//        }
//        //Если добавляем в массив
//        else {
//            component.getRssElements().addAll(rssElements);
//        }
//
//
//        return component;


    }




    //Метод для пара даты из String в LocalDateTime
    private LocalDateTime parseDate (String pubDate, RSSComponent component) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(component.getDataFormat(), Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);

        return localDate;
    }
//
//    public void toPrint (RSSCollector rssCollector) throws IOException {
//        FileWriter f = new FileWriter("H:\\A.html", false);
//        BufferedWriter bw = new BufferedWriter(f);
//
//
//
//            try {
//
//                for (int i = rssCollector.getRssElements().size() - 1; i >= rssCollector.getRssElements().size() - 50; i--) {
//
//                    bw.write(rssCollector.getRssElements().get(i).getTitle() + "<br>");
////
////            bw.write( rssCollector.getRssElements().get(i).getUrl() + "<br>");
////
//                    bw.write("<a href=\"" + rssCollector.getRssElements().get(i).getUrl() + "\">" + rssCollector.getRssElements().get(i).getUrl() + "</a>" + "<br>");
//
//                    bw.write(rssCollector.getRssElements().get(i).getPublicationDate().toString() + "<br>");
//
//                    bw.write("<p>");
//                }
//
//            }
//            finally {
//                bw.close();
//                f.close();
//
//            }

    public void toPrint (int numberOfLines, boolean sorting) throws IOException {

        FileWriter f = new FileWriter("H:\\A.html", false);
        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;

        if (sorting) {
            //todo спроси у ивана так ли работает это говно

            rssElements.sort(RSSElement::compareTo); ////БЛЯЯЯЯЯЯЯЯЯЯ

        }

        try {



            for (RSSElement  element: rssElements) {

                count++;


               bw.write(element.getTitle() + "<br>");
//
////            bw.write( rssCollector.getRssElements().get(i).getUrl() + "<br>");
//
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");

                bw.write(element.getPublicationDate().toString() + "<br>");

                bw.write("<p>");

                if (count == numberOfLines) break;



            }

//            for (int i = rssElements.size() - 1; i >= rssElements.size() - 50; i--) {
//
//                bw.write(rssElements.get(i).getTitle() + "<br>");
//
////            bw.write( rssCollector.getRssElements().get(i).getUrl() + "<br>");
//
//                bw.write("<a href=\"" + rssElements.get(i).getUrl() + "\">" + rssElements.get(i).getUrl() + "</a>" + "<br>");
//
//                bw.write(rssElements.get(i).getPublicationDate().toString() + "<br>");
//
//                bw.write("<p>");
//            }

        }
        finally {
            bw.close();
            f.close();

        }






    }
    }




