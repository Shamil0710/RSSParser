package com.company.abstraction;

import com.company.Property;
import com.company.interfaces.IRSSCollector;
import com.company.parser.RssRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class AbstractRSSCollector implements IRSSCollector {


    private List<Element> connectAndGetItems(AbstractRSSComponent rssComponent) throws IOException {

        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag(Property.ITEM);

    }


    public void collectRSSElements(AbstractRSSComponent component, AbstractRssRepository repository) {


        List<Element> elements = null;
        try {
            elements = connectAndGetItems(component);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при попытке соединения");
        }


        for (Element element : elements) {

            repository.getRssElements().add((new AbstractRSSElement(
                    element.getElementsByTag("title").text(),
                    element.getElementsByTag("link").text(),
                    parseDate(element.getElementsByTag("pubDate").text(), component.getDataFormat()), component.getuRL()

            )
            {

            }));

        }


    }

    private LocalDateTime parseDate(String pubDate, String dateFormat) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);

        return localDate;
    }

    /**
     * @param numberOfLines Количество новостей для вывода
     * @param sorting       Необходима ли сортировка
     * @param saveDirectory Директория для сохронения результата
     * @throws IOException
     */

    public void toPrintAndSort(int numberOfLines, boolean sorting, String saveDirectory, RssRepository repository)

    //TODO Уточнить как сделать вменяемую обработку ошибок

    {

        FileWriter f = null;

        try {
            f = new FileWriter(saveDirectory, false);
        } catch (IOException e) {

            System.out.println("Ошибка при создании выходного потока");

            e.printStackTrace();
        }


        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;

        if (sorting) {

            repository.getRssElements().sort(AbstractRSSElement::compareTo);

        }

        try {


            for (AbstractRSSElement element : repository.getRssElements()) {


                bw.write(element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<a href=\"" + element.getComponentUrl() + "\">" + element.getComponentUrl() + "</a>" + "<br>");
                bw.write("<p>");

                count++;

                if (count == numberOfLines) break;


            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            try {
                bw.close();
                f.close();
            } catch (IOException e) {
                e.printStackTrace();

            }


        }

    }

}
