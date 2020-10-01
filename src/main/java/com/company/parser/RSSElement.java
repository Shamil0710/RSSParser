package com.company.parser;


import com.company.abstraction.AbstractRSSElement;

import java.time.LocalDateTime;

public class RSSElement extends AbstractRSSElement {

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {

        super(title, url, publicationDate, componentUrl);

    }



}

