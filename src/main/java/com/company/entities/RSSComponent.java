package com.company.entities;

import com.company.entities.abstraction.AbstractRSSComponent;

public class RSSComponent extends AbstractRSSComponent {

    /**
     * @param uRL        URL адрес RSS-ленты
     * @param dataFormat Формат записи даты/времмени для данной RSS-ленты
     */
    public RSSComponent(String uRL, String dataFormat) {
        super(uRL, dataFormat);
    }
}
