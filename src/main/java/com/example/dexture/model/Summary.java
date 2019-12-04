package com.example.dexture.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Summary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int summaryID;

    @NotNull
    int year;

    @NotNull
    String type;

    @NotNull
    String tag;

    @NotNull
    int percentage;

    public int getSummaryID() {
        return summaryID;
    }

    public void setSummaryID(int summaryID) {
        this.summaryID = summaryID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
