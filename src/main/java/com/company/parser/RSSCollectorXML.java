package com.company.parser;

import com.company.Property;
import com.company.abstraction.AbstractRSSCollector;
import com.company.interfaces.IRSSCollector;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class RSSCollectorXML extends AbstractRSSCollector implements IRSSCollector {

    private static java.io.InputStream readOfFile(String directory) {
        try {
            InputStream inputStream;
            return new BufferedInputStream(Files.newInputStream(Path.of(directory)));
        } catch (IOException e) {
            e.fillInStackTrace();
        }
        return null;
    }
    //TODO Стоит ли этот метод вывести в абстракцию?
//    private LocalDateTime parseDate(String pubDate, String dataFormat) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat, Locale.ROOT);
//        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);
//        return localDate;
//    }

    public static List<RSSElement> collectRSSElements(RSSComponent component, List<RSSElement> rssElements) {
//        List<RSSElement> rssElements = new ArrayList<RSSElement>();
        try {
            //оздаем конструктор документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Получаем документ
            Document document = documentBuilder.parse(new BufferedInputStream(Files.newInputStream(Path.of(component.getuRL()))));
            //Получаем лист элементов внутри тегов "item"
            NodeList itemNodeList = document.getElementsByTagName(Property.ITEM);
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
                            //TODO Почему не могу обратится к классу Property нутри конструкции wwitch/case?
                            switch (childElement.getNodeName()) {
                                case Property.TITLE: {
                                    rssElement.setTitle(childElement.getTextContent());
                                }
                                break;

                                case Property.LINK: {
                                    rssElement.setUrl(childElement.getTextContent());
                                }
                                break;

                                case Property.DATE_TIME: {
                                    rssElement.setPublicationDate(parseDate(childElement.getTextContent(), component.getDataFormat()));
                                }
                                break;
                            }
                        }
                    }
                    rssElement.setComponentUrl(component.getuRL());
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

        return rssElements;
    }

}
