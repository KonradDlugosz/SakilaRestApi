package com.sparta.sakila.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Store {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("managerStaff")
    private Staff managerStaff;

    @JsonProperty("address")
    private Address address;

    @JsonProperty("lastUpdate")
    private String lastUpdate;

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Staff getManagerStaff() {
        return managerStaff;
    }

    public void setManagerStaff(Staff managerStaff) {
        this.managerStaff = managerStaff;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}