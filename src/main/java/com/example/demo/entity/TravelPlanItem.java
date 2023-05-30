package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="TRAVEL_PLAN_ITEM")
public class TravelPlanItem {
    
    @Id    
    @Column(name="TRAVEL_PLAN_ID")
    private Integer travelPlanId;

    @Column(name="TRAVEL_PLAN_SEQUENCE")
    private Integer travelPlanSeqence;
    
}
