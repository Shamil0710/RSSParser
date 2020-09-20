package com.company.parser;


import java.time.LocalDateTime;


public class RSSElement implements Comparable<RSSElement> {

    private String title;
    private String url;
    private LocalDateTime publicationDate;
    private String componentUrl;

    public RSSElement(String title, String url, LocalDateTime publicationDate, String componentUrl) {
        this.title = title;
        this.url = url;
        this.publicationDate = publicationDate;
        this.componentUrl = componentUrl; //TODO Разобратся как добавлять данную строку к объекту
    }


    public RSSElement(String title, String url, LocalDateTime publicationDate) {
        this.title = title;
        this.url = url;
        this.publicationDate = publicationDate;
    }



    public String getTitle() {
        return title;
    }



    public String getUrl() {
        return url;
    }



    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public String getComponentUrl() {
        return componentUrl;
    }

    public void setComponentUrl(String componentUrl) {
        this.componentUrl = componentUrl;
    }




// В связи с реализации сортировки стримами необходимость в данном комараторе отпадает

    @Override
    public int compareTo(RSSElement o) {


      return publicationDate.compareTo(o.getPublicationDate());


    }



}

