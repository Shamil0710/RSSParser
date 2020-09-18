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


    public String getUrl() {
        return url;
    }


    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }






    @Override
    public int compareTo(RSSElement o) {


        if (!o.getPublicationDate().equals(null)){
            int result = publicationDate.compareTo(o.getPublicationDate());

            //При отсутсвии данного преобразования сортировка просходит по возрастнания (От более старого к более новому), что противоречит задаче
            if (result<0) return 1;
            if (result>0) return -1;

        }

      return  0;

    }


}

