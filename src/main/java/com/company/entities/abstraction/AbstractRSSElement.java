package com.company.entities.abstraction;

import java.time.LocalDateTime;

public abstract class AbstractRSSElement implements Comparable<AbstractRSSElement> {

    private String title;
    private String url;
    private LocalDateTime publicationDate;
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

    public int compareTo(AbstractRSSElement datetime) {

        if (datetime != null) {
            return publicationDate.compareTo(datetime.getPublicationDate());
        }

        return 0;
    }
}
