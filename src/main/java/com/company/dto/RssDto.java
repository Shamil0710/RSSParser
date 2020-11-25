package com.company.dto;

import java.time.LocalDateTime;

public class RssDto {

    private String tittle;
    private String url;
    private LocalDateTime dateTime;

    public RssDto(String tittle, String url, LocalDateTime dateTime){
        this.tittle = tittle;
        this.url = url;
        this.dateTime = dateTime;
    }

    public String getTittle() {
        return tittle;
    }

    public String getUrl() {
        return url;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
