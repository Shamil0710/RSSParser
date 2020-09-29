package com.company.parser;



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

    private final List<RSSElement> rssElements = new ArrayList<RSSElement>();


    private List<Element> connectAndGetItems(RSSComponent rssComponent) throws IOException {

        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag("item");

    }


    public void collectRSSElements(RSSComponent component) throws IOException {



        List<Element> elements = connectAndGetItems(component);



        for (Element element : elements) {
            rssElements.add((new RSSElement(
                    element.getElementsByTag("title").text(),
                    element.getElementsByTag("link").text(),
                    parseDate(element.getElementsByTag("pubDate").text(), component), component.getuRL()

            )));

        }


    }

    private LocalDateTime parseDate (String pubDate, RSSComponent component) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(component.getDataFormat(), Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);

        return localDate;
    }

    /**
     *
     * @param numberOfLines Количество новостей для вывода
     * @param sorting Необходима ли сортировка
     * @param saveDirectory Директория для сохронения результата
     * @throws IOException
     */

    public void toPrintAndSort (int numberOfLines, boolean sorting, String saveDirectory) throws IOException {

   FileWriter f = new FileWriter(saveDirectory, false);



        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;

        if (sorting) {

            rssElements.sort(RSSElement::compareTo);
        }

        try {



            for (RSSElement  element: rssElements) {


                bw.write(element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<a href=\"" + element.getComponentUrl() + "\">" + element.getComponentUrl() + "</a>" + "<br>");
                bw.write("<p>");

                count++;
                if (count == numberOfLines) break;


            }

        }
        finally {
            bw.close();
            f.close();

        }

    }
    }




