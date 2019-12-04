package com.example.dexture.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class ExpectedCultivation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String type;

    private int expectedYear;

    private int expectedQuantity;

    public ExpectedCultivation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getExpectedYear() {
        return expectedYear;
    }

    public void setExpectedYear(int expectedYear) {
        this.expectedYear = expectedYear;
    }

    public int getExpectedQuantity() {
        return expectedQuantity;
    }

    public void setExpectedQuantity(int expectedQuantity) {
        this.expectedQuantity = expectedQuantity;
    }

    public void add(ExpectedCultivation expectedCultivation){
        this.expectedQuantity += expectedCultivation.getExpectedQuantity();
    }
}
