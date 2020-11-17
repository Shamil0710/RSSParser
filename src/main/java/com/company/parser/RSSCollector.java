package com.company.parser;


import com.company.Property;
import com.company.abstraction.AbstractRSSCollector;
import com.company.abstraction.AbstractRSSElement;
import com.company.interfaces.IRSSCollector;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


public class RSSCollector extends AbstractRSSCollector implements IRSSCollector {

//    public List<RSSElement> getRssElements() {
//        return rssElements;
//    }

//    private final List<RSSElement> rssElements = new ArrayList<RSSElement>();

//TODO Готово
    public List<RSSElement> collectRSSElements(RSSComponent component)  {
        List<Element> elements = null;
        try {
            elements = RssRepository.connectAndGet(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<RSSElement> rssElements = new ArrayList<RSSElement>();
        for (Element element : elements) {
            rssElements.add(new RSSElement(
                    element.getElementsByTag(Property.TITLE).text(),
                    element.getElementsByTag(Property.LINK).text(),
                    parseDate(element.getElementsByTag(Property.DATE_TIME).text(), component.getDataFormat()), component.getuRL()
            ));
        }
        return rssElements;
    }

    //TODO Готово
    private LocalDateTime parseDate(String pubDate, String dataFormat) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat, Locale.ROOT);
        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);
        return localDate;
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

    public void parseJAXB(RSSComponent component) throws JAXBException {

        String xmlData = "";

        try {
            InputStream inputStream;
            xmlData = new BufferedInputStream(Files.newInputStream(Path.of(component.getuRL()))).toString();
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        StringReader stringReader = new StringReader(xmlData);
        JAXBContext context = JAXBContext.newInstance(RSSElement.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        RSSElement rssElement = (RSSElement) unmarshaller.unmarshal(stringReader);
    }


}








