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


public class RSSCollector extends AbstractRSSCollector {


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








