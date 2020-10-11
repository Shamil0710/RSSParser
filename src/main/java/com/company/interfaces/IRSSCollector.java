package com.company.interfaces;

import com.company.abstraction.AbstractRSSComponent;
import com.company.abstraction.AbstractRssRepository;
import com.company.parser.RSSComponent;
import com.company.parser.RssRepository;

import java.io.IOException;


public interface IRSSCollector {

    void collectRSSElements(AbstractRSSComponent component, AbstractRssRepository repository) throws IOException;

    void toPrintAndSort(int numberOfLines, boolean sorting, String saveDirectory, RssRepository repository) throws IOException;


}
