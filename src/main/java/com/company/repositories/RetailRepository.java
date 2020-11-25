package com.company.repositories;

import com.company.property.PropertySystem;
import com.company.repositories.abstraction.AbstractRssRepository;
import com.company.collectors.interfaces.IRSSCollector;
import com.company.entities.RSSComponent;
import com.company.entities.RSSElement;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class RetailRepository extends AbstractRssRepository {

    //хранение данных в репозиторие
    private List<RSSElement> rssElements;

    //в конструктор передаешь компонент для конкретного репозитория и какойто абстрактый вспомогательный класс для обработки входящих данных
    public RetailRepository(RSSComponent component, IRSSCollector collector) {
        super(component, collector);
        this.rssElements = super.connectAndCollect();
    }

    @Override
    public List<RSSElement> getAll() {
        return this.rssElements;
    }

    @Override
    public List<RSSElement> sort(String fieldName) {
        switch (fieldName) {
            case ("publicationDate"): {
                return rssElements = rssElements.stream()
                        .sorted((o1, o2) -> -o1.compareTo(o2))
                        .collect(Collectors.toList());
            }
            case ("url"): {
                return rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getPublicationDate))
                        .collect(Collectors.toList());
            }
            case ("title"): {
                return rssElements = rssElements.stream()
                        .sorted(Comparator.comparing(RSSElement::getTitle))
                        .collect(Collectors.toList());
            }
            default: {
                throw new IllegalArgumentException("Некорректное поле сортировки");
            }
        }
    }

    @Override
    public List<RSSElement> filter(String request, String fieldName) {
        switch (fieldName) {
            //Фильтрация по частичному совпадению
            case (PropertySystem.TITLE): {
                return rssElements = rssElements.stream()
                        .filter(element -> element.getTitle().contains(request))
                        .collect(Collectors.toList());
            }
            case (PropertySystem.LINK): {
                return rssElements = rssElements.stream()
                        .filter(element -> element.getUrl().contains(request))
                        .collect(Collectors.toList());
            }
            default: {
                throw new IllegalArgumentException("Некорректное поле фильтрации");
            }
        }
    }
}
