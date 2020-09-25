package com.company.parser;

public class RSSComponent {

   private String uRL;
   private String dataFormat;

    /**
     *
     * @param uRL URL адрес RSS-ленты
     * @param dataFormat Формат записи даты/времмени для данной RSS-ленты
     */

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
