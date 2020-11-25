package com.company.collectors;

import com.company.property.PropertySystem;
import com.company.collectors.abstraction.AbstractRSSCollector;
import com.company.entities.RSSComponent;
import com.company.entities.RSSElement;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class XMLCollector extends AbstractRSSCollector<RSSElement, RSSComponent> {

    @Override
    public List<RSSElement> collectRSSElements(RSSComponent component) {
        List<RSSElement> rssElements = new ArrayList<RSSElement>();
        try {
            //оздаем конструктор документа
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Получаем документ
            Document document = documentBuilder.parse(new BufferedInputStream(Files.newInputStream(Path.of(component.getuRL()))));
            //Получаем лист элементов внутри тегов "item"
            NodeList itemNodeList = document.getElementsByTagName(PropertySystem.ITEM);
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
                                case PropertySystem.TITLE: {
                                    rssElement.setTitle(childElement.getTextContent());
                                }
                                break;

                                case PropertySystem.LINK: {
                                    rssElement.setUrl(childElement.getTextContent());
                                }
                                break;

                                case PropertySystem.DATE_TIME: {
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
