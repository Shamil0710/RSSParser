package com.company.parser;

import com.company.abstraction.AbstractRSSCollector;
import com.company.abstraction.AbstractRSSComponent;
import com.company.abstraction.AbstractRssRepository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;

public class RssCollectorXml extends AbstractRSSCollector {
    @Override
    public void collectRSSElements(AbstractRSSComponent component, AbstractRssRepository repository) throws JAXBException {

        String xmlData = "";


        try {

            InputStream inputStream;
            xmlData =  new BufferedInputStream(Files.newInputStream(Path.of(component.getuRL()))).toString();

        } catch (IOException e) {
            e.fillInStackTrace();
        }

        StringReader stringReader = new StringReader(xmlData);

        JAXBContext context = JAXBContext.newInstance(RssElementXml.class, RssRepositoryXml.class);

    }
}
