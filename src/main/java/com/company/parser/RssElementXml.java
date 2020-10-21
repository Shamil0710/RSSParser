package com.company.parser;

import com.company.abstraction.AbstractRSSElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.time.LocalDateTime;

//@XmlType(name = "item")


public class RssElementXml extends AbstractRSSElement {


    public RssElementXml() {
        super();
    }

    @Override
    @XmlElement(name = "title")
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    @XmlElement(name = "link")
    public void setUrl(String url) {
        super.setUrl(url);
    }

    @Override
    @XmlElement(name = "pubDate")
    public void setPublicationDate(LocalDateTime publicationDate) {
        super.setPublicationDate(publicationDate);
    }

    @Override
    public void setComponentUrl(String componentUrl) {
        super.setComponentUrl(componentUrl);
    }
}
