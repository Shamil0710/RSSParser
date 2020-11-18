package com.company.parser;


import com.company.Property;
import com.company.abstraction.AbstractRSSCollector;
import com.company.interfaces.IRSSCollector;
import org.jsoup.nodes.Element;
import java.io.*;
import java.util.List;

public class RSSCollectorJsoup extends AbstractRSSCollector implements IRSSCollector {

//    public List<RSSElement> getRssElements() {
//        return rssElements;
//    }

//    private final List<RSSElement> rssElements = new ArrayList<RSSElement>();

    //TODO Готово
    public static List<RSSElement> collectRSSElements(RSSComponent component, List<RSSElement> rssElements) {
        List<Element> elements = null;
        try {
            elements = RssRepository.connectAndGet(component);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Element element : elements) {
            rssElements.add(new RSSElement(
                    element.getElementsByTag(Property.TITLE).text(),
                    element.getElementsByTag(Property.LINK).text(),
                    parseDate(element.getElementsByTag(Property.DATE_TIME).text(), component.getDataFormat()), component.getuRL()
            ));
        }
        return rssElements;
    }

//    //TODO Готово
//    private LocalDateTime parseDate(String pubDate, String dataFormat) {
//        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dataFormat, Locale.ROOT);
//        LocalDateTime localDate = LocalDateTime.parse(pubDate, dateTimeFormatter);
//        return localDate;
//    }
//
//    public void parseJAXB(RSSComponent component) throws JAXBException {
//
//        String xmlData = "";
//
//        try {
//            InputStream inputStream;
//            xmlData = new BufferedInputStream(Files.newInputStream(Path.of(component.getuRL()))).toString();
//        } catch (IOException e) {
//            e.fillInStackTrace();
//        }
//        StringReader stringReader = new StringReader(xmlData);
//        JAXBContext context = JAXBContext.newInstance(RSSElement.class);
//        Unmarshaller unmarshaller = context.createUnmarshaller();
//        RSSElement rssElement = (RSSElement) unmarshaller.unmarshal(stringReader);
//    }
//

}








