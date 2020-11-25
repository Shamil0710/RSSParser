package com.company.collectors;

import com.company.property.PropertySystem;
import com.company.collectors.abstraction.AbstractRSSCollector;
import com.company.entities.RSSComponent;
import com.company.entities.RSSElement;
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
            final List<Element> rawElements = Jsoup.connect(component.getuRL()).get().getElementsByTag(PropertySystem.ITEM);
            for (Element element : rawElements) {
                rssElements.add(new RSSElement(
                        element.getElementsByTag(PropertySystem.TITLE).text(),
                        element.getElementsByTag(PropertySystem.LINK).text(),
                        parseDate(element.getElementsByTag(PropertySystem.DATE_TIME).text(), component.getDataFormat()), component.getuRL()
                ));
            }
            return rssElements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rssElements;
    }
}
