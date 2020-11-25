package com.company.services.interfaces;

import com.company.dto.RssDto;

import java.time.LocalDateTime;
import java.util.List;

//общее описание сервиса, сервис нужен для предоставления результатов к запросу пользователя
public interface RssWorker {

    List<RssDto> getAll();
    List<RssDto> sort (String fieldName, List<RssDto> rssDtoList);
    List<RssDto> filter (String fieldName, String request, List<RssDto> rssDtoList);
    List<RssDto> filterOfDate (LocalDateTime dateTime, List<RssDto> rssDtoList);

}
