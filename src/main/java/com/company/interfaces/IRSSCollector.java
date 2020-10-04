package com.company.interfaces;

import com.company.parser.RSSComponent;
import com.company.parser.RssRepository;

import java.io.IOException;


public interface IRSSCollector {

    void collectRSSElements(RSSComponent component, RssRepository repository) throws IOException;

    void toPrintAndSort(int numberOfLines, boolean sorting, String saveDirectory, RssRepository repository) throws IOException;


}
