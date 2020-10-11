package com.company.abstraction;

import java.time.LocalDateTime;

public abstract class AbstractRSSElement implements Comparable<com.company.parser.RSSElement> {

    private String title;
    private String url;
    protected LocalDateTime publicationDate;
    private String componentUrl;

    public AbstractRSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {
        this.title = title;
        this.url = url;
        this.publicationDate = publicationDate;
        this.componentUrl = componentUrl;
    }

    public AbstractRSSElement() {

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

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl;
    }

    @Override
    public int compareTo(com.company.parser.RSSElement o) {

        if (!(o == null)) {

            return publicationDate.compareTo(o.getPublicationDate());

        }


        return 0;


    }

}
