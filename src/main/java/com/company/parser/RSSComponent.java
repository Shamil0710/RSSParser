package com.company.parser;

import java.util.List;


//Базовый класс. Содержит урл страницы, формат даты для парса. Лист содержащий обекты RSSElement содержащих информацию из <Item>

public class RSSComponent {

    String uRL;
    String dataFormat;

    List<RSSElement> rssElements;

    public List<RSSElement> getRssElements() {
        return rssElements;
    }

    public void setRssElements(List<RSSElement> rssElements) {
        this.rssElements = rssElements;
    }

    public RSSComponent(String uRL, String dataFormat) {
        this.uRL = uRL;
        this.dataFormat = dataFormat;
    }

    public String getuRL() {
        return uRL;
    }

    public void setuRL(String uRL) {
        this.uRL = uRL;
    }

    public String getDataFormat() {
        return dataFormat;
    }

    public void setDataFormat(String dataFormat) {
        this.dataFormat = dataFormat;
    }
}
