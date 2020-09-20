package com.company.parser;



import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import java.io.BufferedWriter;
import java.io.Console;
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
                    parseDate(element.getElementsByTag("pubDate").text(), component)

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

    //TODO Стоит ли объединить в один метод и сортировать в зависимости от перанного аргумента (Название поля в формате страки)

    //Сортировка с применением Stream API

    /**
     *
     * @param fieldName Имя строки для сортировки
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

        } else { Exception e = new Exception();
        e.getStackTrace();
            System.out.println("Некорректное поле сортировки");
        }
    }

    public void filter (String request) {

        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getTitle().equals(request)).collect(Collectors.toList());

    }

    }

//    public void sortByTitle () {


//      rssElements = rssElements.stream() //todo Допустимо ли такое присваевание?
//              .sorted(Comparator.comparing(RSSElement::getTitle))
//              .collect(Collectors.toList());
//
////        rssElements.sort(RSSElement::compareTo);
//    }
//
//    public void sortByUrl () {
//        rssElements = rssElements.stream()
//                .sorted(Comparator.comparing(RSSElement::getPublicationDate))
//                .collect(Collectors.toList());
//    }
//
//    public void sortByPublicationDate () {
//        rssElements = rssElements.stream()
//                .sorted((o1, o2) -> - o1.compareTo(o2) )
//                .collect(Collectors.toList());
//    }

//    public void filter () {
//        rssElements.stream().filter(rssElements.)
//    }








