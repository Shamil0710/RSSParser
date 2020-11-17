package com.company.parser;


import com.company.abstraction.AbstractRSSElement;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;


@XmlRootElement(name = "item")
public class RSSElement extends AbstractRSSElement {

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {
        super(title, url, publicationDate, componentUrl);
    }
}

