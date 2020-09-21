package com.company.parser;

//Набор элементов получаемых из <item>. Служебный

import com.company.AbstractRSS.AbstractRSSElement;

import java.time.LocalDateTime;

public class RSSElement extends AbstractRSSElement implements Comparable<RSSElement>{

    private String title;
    private String url;
    private LocalDateTime publicationDate;

    public RSSElement(String title, String url, LocalDateTime publicationDate) {
        this.title = title;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }



    //todo
    @Override
    public int compareTo(RSSElement o) {

        int result = publicationDate.compareTo(getPublicationDate());


        if (result == 1 ) {
            return -1;
        }
        else if (result == -1) {
            return 1;
        }
        else return 0;

    }


}

