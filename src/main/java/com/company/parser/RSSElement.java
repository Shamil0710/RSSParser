package com.company.parser;


import java.time.LocalDateTime;

public class RSSElement extends com.company.abstraction.RSSElement implements Comparable<RSSElement>{

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {

        super(title, url, publicationDate, componentUrl);

    }


    @Override
    public int compareTo(RSSElement o) {

        int result = publicationDate.compareTo(o.getPublicationDate());

        if (result<0) return 1;
        if (result>0) return -1;

      return  0;


    }


}

