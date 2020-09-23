package com.company.parser;


import java.time.LocalDateTime;

public class RSSElement implements Comparable<RSSElement>{

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




    @Override
    public int compareTo(RSSElement o) {

        int result = publicationDate.compareTo(o.getPublicationDate());

        if (result<0) return 1;
        if (result>0) return -1;

      return  0;


    }


}

