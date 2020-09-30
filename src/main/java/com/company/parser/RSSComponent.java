package com.company.parser;

import com.company.abstraction.AbstractRSSComponent;

public class RSSComponent extends AbstractRSSComponent {

    /**
     * @param uRL        URL адрес RSS-ленты
     * @param dataFormat Формат записи даты/времмени для данной RSS-ленты
     */

    public RSSComponent(String uRL, String dataFormat) {

        super(uRL, dataFormat);

    }
}
