package com.example.demo.mapper;

public interface BaseMapper<D, E> {
    E toEntity(D d);
    D toDto(E e);
}
