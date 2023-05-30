package com.example.demo.entity;

import com.example.demo.constant.Destination.DestinationTypeEnum;
import com.example.demo.constant.Travel.TravelTypeEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TRAVEL_PLAN")
public class TravelPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="TRAVEL_PLAN_ID")
    private Integer travelPlanId;

    @Enumerated(EnumType.STRING)
    private TravelTypeEnum travelType;

    @Enumerated(EnumType.STRING)
    private DestinationTypeEnum destinationType;
    
}
