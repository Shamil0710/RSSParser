package com.company.parser;


import java.time.LocalDateTime;


public class RSSElement implements Comparable<RSSElement> {

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



    public String getUrl() {
        return url;
    }



    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }




// В связи с реализации сортировки стримами необходимость в данном комараторе отпадает

    @Override
    public int compareTo(RSSElement o) {


      return publicationDate.compareTo(o.getPublicationDate());


    }



}

