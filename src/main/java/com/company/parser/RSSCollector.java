package com.company.parser;



import com.company.abstraction.AbstractRSSCollector;
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

    private static java.io.InputStream readOfFile(String directory) {


        try {

            InputStream inputStream;
            return new BufferedInputStream(Files.newInputStream(Path.of(directory)));

        } catch (IOException e) {
            e.fillInStackTrace();
        }


        return null;
    }

    public void collectRSSElementsXML(RSSComponent component) {


        try {


            //оздаем конструктор документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();


            //Получаем документ
            Document document = documentBuilder.parse(readOfFile(component.getuRL()));



            //Получаем лист элементов внутри тегов "item"

            NodeList itemNodeList = document.getElementsByTagName("item");

            //Проходимся по всем итемам

            for (int i = 0; i < itemNodeList.getLength(); i++) {


                if (itemNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {

                    //Если нода является "ELEMENT_NODE приводим её к типу element"
                    org.w3c.dom.Element itemElement = (org.w3c.dom.Element) itemNodeList.item(i);


                    //Создаём список дочерних нодво
                    NodeList childNodes = itemElement.getChildNodes();

                    RSSElement rssElement = new RSSElement();

                    //Перебираем дочерние ноды
                    for (int j = 0; j < childNodes.getLength(); j++) {

                        if (childNodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            org.w3c.dom.Element childElement = (org.w3c.dom.Element) childNodes.item(j);


                            switch (childElement.getNodeName()) {
                                case "title": {

                                    rssElement.setTitle(childElement.getTextContent());

                                }
                                break;

                                case "link": {

                                    rssElement.setUrl(childElement.getTextContent());

                                }
                                break;

                                case "pubDate": {

                                    rssElement.setPublicationDate(parseDate(childElement.getTextContent(), component));

                                }
                                break;


                            }


                        }


                    }

                    rssElements.add(rssElement);


                }
            }


        } catch (ParserConfigurationException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        }

    }


}








