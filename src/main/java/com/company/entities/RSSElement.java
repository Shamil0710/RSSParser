package com.company.entities;


import com.company.entities.abstraction.AbstractRSSElement;
import java.time.LocalDateTime;

public class RSSElement extends AbstractRSSElement {

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {
        super(title, url, publicationDate, componentUrl);
    }

    public RSSElement(){

    }
}

