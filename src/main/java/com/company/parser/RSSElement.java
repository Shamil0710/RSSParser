package com.company.parser;


import java.time.LocalDateTime;

public class RSSElement extends com.company.abstraction.RSSElement implements Comparable<RSSElement>{

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {

        super(title, url, publicationDate, componentUrl);

    }


    @Override
    public int compareTo(RSSElement o) {


      return publicationDate.compareTo(o.getPublicationDate());


    }


}

