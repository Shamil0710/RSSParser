package com.company.parser;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDateTime;


@XmlRootElement(name = "item")
public class RSSElement implements Comparable<RSSElement>{

    private String title;
    private String url;
    private LocalDateTime publicationDate;


    public RSSElement(String title, String url, LocalDateTime publicationDate) {
        this.title = title;
        this.url = url;
        this.publicationDate = publicationDate;
    }

    public RSSElement() {

    }


    @XmlElement(name = "title")
    public void setTitle(String title) {
        this.title = title;
    }


    public String getTitle() {
        return title;
    }


    public String getUrl() {
        return url;
    }

    @XmlElement(name = "link")
    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    @XmlElement(name = "pubDate")
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

