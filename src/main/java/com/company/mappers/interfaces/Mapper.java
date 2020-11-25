package com.company.mappers.interfaces;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<F,T> {

    T toDto(F element);

    default List<T> toDtoList(List<F> entityList){
        return entityList.stream().map(this::toDto).collect(Collectors.toList());
    }

}
