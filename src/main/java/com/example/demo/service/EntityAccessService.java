package com.example.demo.service;

/* 직접 entity 접근(CRUD) 할때 구현해줘야 하는 인터페이스. */
public interface EntityAccessService<DTO, ENTITY> {    
    public ENTITY toEntity(DTO dto);
    public DTO toDto(ENTITY user);

    /*
    //C
    public ENTITY createEntityFromDto(DTO dto);    
    public ENTITY createEntity(ENTITY entity);    

    //R
    public ENTITY getEntityFromPKkey(Object entityPkObject);    

    //U
    public ENTITY updateEntityFromDto(DTO dto);    
    public ENTITY updateEntity(ENTITY entity);

    //D
    public void deleteEntity(ENTITY dto);    
    public void deleteEntityFromPKKey(Object entityPkObject);
    */
}
