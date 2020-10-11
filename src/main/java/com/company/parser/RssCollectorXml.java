package com.company.parser;

import com.company.abstraction.AbstractRSSCollector;
import com.company.abstraction.AbstractRSSComponent;
import com.company.abstraction.AbstractRssRepository;

import java.io.IOException;
import java.io.StringReader;

public class RssCollectorXml extends AbstractRSSCollector {
    @Override
    public void collectRSSElements(AbstractRSSComponent component, AbstractRssRepository repository) throws IOException {

        StringReader stringReader = new StringReader(component.getuRL());

    }
}
