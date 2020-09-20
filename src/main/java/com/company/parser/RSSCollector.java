package com.company.parser;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class RSSCollector {



    public List<RSSElement> getRssElements() {
        return rssElements;
    }

    private List<RSSElement> rssElements = new ArrayList<RSSElement>();


    private List<Element> connectAndGetItems(RSSComponent rssComponent) throws IOException {

        return Jsoup.connect(rssComponent.getuRL()).get().getElementsByTag("item");

    }


    public void collectRSSElements(RSSComponent component) throws IOException {



        List<Element> elements = connectAndGetItems(component);



        for (Element element : elements) {
            rssElements.add((new RSSElement(
                    element.getElementsByTag("title").text(),
                    element.getElementsByTag("link").text(),
                    parseDate(element.getElementsByTag("pubDate").text(), component.dataFormat)

            )));


        }


    }

    private LocalDateTime parseDate (String pubDate, String dateFormat) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat, Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);

        return localDate;
    }

    /**
     *
     * @param numberOfLines Количество новостей для вывода
     * @param saveDirectory Директория для сохронения результата
     * @throws IOException
     */

    public void toPrint (int numberOfLines, String saveDirectory) throws IOException {

        FileWriter f = new FileWriter(saveDirectory, false);


        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;


        try {


            for (RSSElement  element: rssElements) {

                count++;
                bw.write(count + " " + element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<p>");


                if (count == numberOfLines) break;


            }

        }
        finally {
            bw.close();
            f.close();

        }

    }


    //Сортировка с применением Stream API

    /**
     * ортировка по любому из полей
     * @param fieldName Имя поля для сортировки
     */

    public void sort (String fieldName) {
        if (fieldName.equals("publicationDate")){

            rssElements = rssElements.stream()  //TODO Допустимо ли подобное присваевание
                .sorted((o1, o2) -> - o1.compareTo(o2) )
                .collect(Collectors.toList());

        } else  if (fieldName.equals("url")) {

            rssElements = rssElements.stream()
                    .sorted(Comparator.comparing(RSSElement::getPublicationDate))
                    .collect(Collectors.toList());

        } else if (fieldName.equals("title")){

            rssElements = rssElements.stream()
                    .sorted(Comparator.comparing(RSSElement::getTitle))
                    .collect(Collectors.toList());

        } else {
            System.out.println("Некорректное поле сортировки");
        }
    }

    /**
     * Фильтрация по полям Title и Url
     * @param request Аргумен по которому происходит фидьтрация
     * @param fieldName Поле в котором должен содержатся аргумент для фильтрации
     */

    public void filter (String request, String fieldName) {

        if (fieldName.equals("title")){

            rssElements = rssElements.stream()
                    .filter(rssElements -> rssElements.getTitle().equals(request))
                    .collect(Collectors.toList());

        } else if (fieldName.equals("url")) {

            rssElements = rssElements.stream()
                    .filter(rssElements -> rssElements.getUrl().equals(request))
                    .collect(Collectors.toList());
        } else {
            System.out.println("Некорректное поле фильтрации");
        }


    }

    //Филтрация по DataTime

    public void filterByDate (String request, String dataFormat) {

        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getPublicationDate().equals(parseDate(request, dataFormat)))
                .collect(Collectors.toList());

    }



    }









