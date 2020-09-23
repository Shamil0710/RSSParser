package com.company.parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                    parseDate(element.getElementsByTag("pubDate").text(), component)

            )));

        }


    }

    private LocalDateTime parseDate(String pubDate, RSSComponent component) {

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(component.getDataFormat(), Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);

        return localDate;
    }

    /**
     * @param numberOfLines Количество новостей для вывода
     * @param sorting       Необходима ли сортировка
     * @param saveDirectory Директория для сохронения результата
     * @throws IOException
     */

    public void toPrintAndSort(int numberOfLines, boolean sorting, String saveDirectory) throws IOException {

        FileWriter f = new FileWriter(saveDirectory, false);


        BufferedWriter bw = new BufferedWriter(f);

        int count = 0;

        if (sorting) {

            rssElements.sort(RSSElement::compareTo);
        }

        try {


            for (RSSElement element : rssElements) {


                bw.write(element.getTitle() + "<br>");
                bw.write("<a href=\"" + element.getUrl() + "\">" + element.getUrl() + "</a>" + "<br>");
                bw.write(element.getPublicationDate().toString() + "<br>");
                bw.write("<p>");

                count++;
                if (count == numberOfLines) break;


            }

        } finally {
            bw.close();
            f.close();

        }

    }

    public static String readOfFile(String directory) {


        //Пытаемся прочитать файл
        try {

            return Files.readString(Paths.get(directory));

        } catch (IOException e) {
            e.fillInStackTrace();
        }


        return null;
    }

    public static void collectRSSElementsXML(String directory) throws ParserConfigurationException, IOException, SAXException {


        //оздаем конструктор документа
        DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();

        //Создаем дерево документа

//            Document document = documentBuilder.parse(Files.readString(Paths.get(directory)));

//            Document document = documentBuilder.parse(directory);

        Document document = documentBuilder.parse(new File(directory));

        //Получаем корневой элемент
        //TODO Это RSS или Chanel?

        Node root = document.getDocumentElement();

        //Получаем все под элементы корневого нода

        NodeList items = root.getChildNodes();

        for (int i = 0; i < items.getLength(); i++) {

            Node item = items.item(i);

            if (item.getNodeName().equals("title")) {
                System.out.println(item.getTextContent());
            }

        }


    }


}








