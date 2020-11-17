package com.company.parser;


import com.company.abstraction.AbstractRSSCollector;
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


public class RSSCollector extends AbstractRSSCollector {

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

    /* Фильтрация и сортировка стримами */

    public void sort(String fieldName) {
        switch (fieldName) {
            case ("publicationDate"): {
                rssElements = rssElements.stream()
                        .sorted((o1, o2) -> -o1.compareTo(o2))
                        .collect(Collectors.toList());
                break;

            }
            case ("url"): {
                rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getPublicationDate))
                        .collect(Collectors.toList());
                break;

            }
            case ("title"): {
                rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getTitle))
                        .collect(Collectors.toList());
                break;
            }
            default: {
                System.out.println("Некорректное поле сортировки");
            }
        }
    }

    /**
     * Фильтрация по полям Title и Url
     *
     * @param request   Аргумен по которому происходит фидьтрация
     * @param fieldName Поле в котором должен содержатся аргумент для фильтрации
     */

    public void filter(String request, String fieldName) {
        switch (fieldName) {

            //TODO стоит ли добавить фильтрацию как по полному так и по частичному совпадению или оставить только один?

            //Фильтрация по полному совпажению

//            case ("title"): {
//
//                rssElements = rssElements.stream()
//                        .filter(rssElements -> rssElements.getTitle().equals(request))
//                        .collect(Collectors.toList());
//
//                break;
//
//            }

            //Фильтрация по частичному совпадению
            case ("title"): {
                rssElements = rssElements.stream()
                        .filter(rssElements -> rssElements.getTitle().contains(request))
                        .collect(Collectors.toList());
                break;

            }
            case ("url"): {
                rssElements = rssElements.stream()
                        .filter(rssElements -> rssElements.getUrl().contains(request))
                        .collect(Collectors.toList());
                break;
            }

            default: {
                System.out.println("Некорректное поле фильтрации");
            }
        }
    }

    //Филтрация по DataTime
    public void filterByDateAndTime(String request, String dataFormat) {
        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getPublicationDate().equals(parseDate(request, dataFormat)))
                .collect(Collectors.toList());
    }

    public void filterByDate(String request, String dataFormat) {
        rssElements = rssElements.stream()
                .filter(rssElements -> rssElements.getPublicationDate().toLocalDate().equals(parseDate(request, dataFormat).toLocalDate()))
                .collect(Collectors.toList());
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








