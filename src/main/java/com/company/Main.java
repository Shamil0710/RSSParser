package com.company;

import com.company.collectors.RetailCollector;
import com.company.collectors.XMLCollector;
import com.company.dto.RssDto;
import com.company.entities.RSSComponent;
import com.company.entities.RSSElement;
import com.company.collectors.interfaces.IRSSCollector;
import com.company.property.Property;
import com.company.property.PropertySystem;
import com.company.repositories.interfaces.RssRepository;
import com.company.mappers.RssElementsMapper;
import com.company.parser.*;
import com.company.repositories.RetailRepository;
import com.company.services.RssWorkerImpl;
import com.company.services.interfaces.RssWorker;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args){

        RSSComponent rssComponentJsoup = new RSSComponent(Property.JSOUP_DIRECTORY, "E, dd MMM yyyy HH:mm:ss Z");
        RSSComponent rssComponentXML = new RSSComponent(Property.XML_DIRECTORY, "E, dd MMM yyyy HH:mm:ss Z");
        IRSSCollector<RSSElement, RSSComponent> retailCollector = new RetailCollector();
        IRSSCollector<RSSElement, RSSComponent> XMLCollector = new XMLCollector();
        RssRepository<RSSElement> retailRepository = new RetailRepository(rssComponentJsoup, retailCollector);
        RssRepository<RSSElement> XMLRepository = new RetailRepository(rssComponentXML, XMLCollector);
        RssWorker rssWorker = new RssWorkerImpl(retailRepository,XMLRepository, new RssElementsMapper());

        List<RssDto> rssDtos = new ArrayList<>();
        rssDtos = rssWorker.getAll();

        RSSPrinter.printToConsole(Property.NUMBER_OF_LINES, rssWorker.filter(PropertySystem.TITLE, "Кризис", rssDtos));
        RSSPrinter.printToHtml(Property.NUMBER_OF_LINES, Property.SAVE_DIRECTORY, rssDtos);
    }
}
