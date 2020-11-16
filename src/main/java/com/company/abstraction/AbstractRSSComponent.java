package com.company.abstraction;

public abstract class AbstractRSSComponent {

    String uRL;
    String dataFormat;

    public AbstractRSSComponent(String uRL, String dataFormat) {
        this.uRL = uRL;
        this.dataFormat = dataFormat;
    }

    public String getuRL() {
        return uRL;
    }

    public String getDataFormat() {
        return dataFormat;
    }
}
