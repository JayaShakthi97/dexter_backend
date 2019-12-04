package com.example.dexture.model;

public class ResBody {
    public String token;
    public Farmer farmer;
    public Buyer buyer;
    public String message;
    public boolean accepted = true;

    public ResBody() {
    }

    public ResBody(String token) {
        this.token = token;
    }

    public ResBody(String msg, boolean value){
        this.message = msg;
    }

    public ResBody(String token, Farmer farmer) {
        this.token = token;
        this.farmer = farmer;

    }

    public ResBody(String token, Buyer buyer) {
        this.token = token;
        this.buyer = buyer;
    }

    public ResBody(boolean accepted) {
        this.accepted = accepted;
    }
}
