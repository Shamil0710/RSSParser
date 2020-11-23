package com.company.collectors;

import com.company.Property;
import com.company.abstraction.AbstractRSSCollector;
import com.company.parser.RSSComponent;
import com.company.parser.RSSElement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RetailCollector extends AbstractRSSCollector<RSSElement, RSSComponent> {

    @Override
    public List<RSSElement> collectRSSElements(RSSComponent component) {
        List<RSSElement> rssElements = new ArrayList<>();
        try {
            final List<Element> rawElements = Jsoup.connect(component.getuRL()).get().getElementsByTag(Property.ITEM);
            for (Element element : rawElements) {
                rssElements.add(new RSSElement(
                        element.getElementsByTag(Property.TITLE).text(),
                        element.getElementsByTag(Property.LINK).text(),
                        parseDate(element.getElementsByTag(Property.DATE_TIME).text(), component.getDataFormat()), component.getuRL()
                ));
            }
            return rssElements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rssElements;
    }
}
