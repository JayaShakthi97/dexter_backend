package com.example.dexture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Land {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double size;

    private float latitude;

    private float longitude;

    @ManyToOne
    @JoinColumn(name = "farmer_id")
    @JsonIgnore
    private Farmer farmer;

    @OneToMany(mappedBy = "land")
    private Set<Harvest> harvestSet = new HashSet<>();

    @Transient
    private int farmerId;

    public Land() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public void setFarmer(Farmer farmer) {
        this.farmer = farmer;
    }

    public int getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(int farmerId) {
        this.farmerId = farmerId;
    }

    public Set<Harvest> getHarvestSet() {
        return harvestSet;
    }

    public void setHarvestSet(Set<Harvest> harvestSet) {
        this.harvestSet = harvestSet;
    }

    public void addHarvest(Harvest harvest){
        this.harvestSet.add(harvest);
    }
}
