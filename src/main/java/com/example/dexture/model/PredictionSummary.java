package com.example.dexture.model;

public class PredictionSummary {
    private String type;
    private int currentQ = 0;
    private int expectedQ = 0;
    private float percentage;

    public PredictionSummary(String type) {
        this.type = type;
    }

    public PredictionSummary(String type, int expectedQ) {
        this.type = type;
        this.expectedQ = expectedQ;
    }

    public PredictionSummary(String type, int currentQ, int expectedQ) {
        this.type = type;
        this.currentQ = currentQ;
        this.expectedQ = expectedQ;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCurrentQ() {
        return currentQ;
    }

    public void setCurrentQ(int currentQ) {
        this.currentQ = currentQ;
    }

    public int getExpectedQ() {
        return expectedQ;
    }

    public void setExpectedQ(int expectedQ) {
        this.expectedQ = expectedQ;
    }

    public float getPercentage() {
        return percentage;
    }

    public void setPercentage(float percentage) {
        this.percentage = percentage;
    }
}
