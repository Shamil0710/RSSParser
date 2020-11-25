package com.company.services;

import com.company.property.PropertySystem;
import com.company.dto.RssDto;
import com.company.repositories.interfaces.RssRepository;
import com.company.mappers.interfaces.Mapper;
import com.company.entities.RSSElement;
import com.company.services.interfaces.RssWorker;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("uncheked")
public class RssWorkerImpl implements RssWorker {

    private final RssRepository retailRepository;
    private final RssRepository xmlRepository;
    //маппер конвертит из entity bean, в dto
    private final Mapper<RSSElement, RssDto> rssDtoMapper;

    //принимает два репозитория, может и 3 и 4 и 5 сколь угодно объедененных одной логикой - работа с рсс лентой
    public RssWorkerImpl(RssRepository retailRepository, RssRepository xmlRepository, Mapper<RSSElement, RssDto> rssDtoMapper) {
        this.retailRepository = retailRepository;
        this.xmlRepository = xmlRepository;
        this.rssDtoMapper = rssDtoMapper;
    }

    //сервисный метод получения элементов приведенных к единому виду
    @Override
    public List<RssDto> getAll() {

        final List<RSSElement> retailElements = retailRepository.getAll();
        final List<RSSElement> xmlElements = xmlRepository.getAll();

        final List<RssDto> rssDtos = rssDtoMapper.toDtoList(retailElements);
        rssDtos.addAll(rssDtoMapper.toDtoList(xmlElements));

        return rssDtos;
    }

    //TODO: добавить фильтрацию и сортировку

    public List<RssDto> sort (String fieldName, List<RssDto> rssDtoList) {

        switch (fieldName) {
            case (PropertySystem.TITLE): {
                return rssDtoList.stream()
                        .sorted(Comparator.comparing(RssDto::getTittle))
                        .collect(Collectors.toList());
            }
            case (PropertySystem.LINK): {
                return rssDtoList.stream()
                        .sorted(Comparator.comparing(RssDto::getUrl))
                        .collect(Collectors.toList());
            }
            case (PropertySystem.DATE_TIME): {
                return rssDtoList.stream()
                        .sorted(Comparator.comparing(RssDto::getDateTime))
                        .collect(Collectors.toList());
            }
            default: {
                throw new IllegalArgumentException("Некорректное поле сортировки");
            }
        }
    }

    public List<RssDto> filter (String fieldName, String request, List<RssDto> rssDtoList) {
        switch (fieldName) {
            case (PropertySystem.TITLE): {
                return rssDtoList.stream()
                        .filter(s -> s.getTittle().contains(request))
                        .collect(Collectors.toList());
            }
            case (PropertySystem.LINK): {
                return rssDtoList.stream()
                        .filter(s -> s.getUrl().contains(request))
                        .collect(Collectors.toList());
            }
            default: {
                throw new IllegalArgumentException("Некорректное поле сортировки");
            }
        }
    }

    public List<RssDto> filterOfDate (LocalDateTime dateTime, List<RssDto> rssDtoList) {
        return rssDtoList.stream()
                .filter(s -> s.getDateTime().toLocalDate().equals(dateTime))
                .collect(Collectors.toList());
    }
}
