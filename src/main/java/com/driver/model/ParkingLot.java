package com.driver.model;

import javax.persistence.*;import java.util.ArrayList;import java.util.List;

@Entity
public class ParkingLot
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;

    private String address;

    public ParkingLot() {}

    public ParkingLot(int id, String name, String address) {
        this.id=id;
        this.name=name;
        this.address=address;
    }
    @OneToMany(mappedBy = "parkingLot",cascade = CascadeType.ALL)
    List<Spot> spotList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Spot> getSpotList() {
        return spotList;
    }

    public void setSpotList(List<Spot> spotList) {
        this.spotList = spotList;
    }
}