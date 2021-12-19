package com.sparta.sakila.entity.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sparta.sakila.entity.Staff;
import com.sparta.sakila.entity.Store;

import java.util.List;

public class StoreAndStaff {

    @JsonProperty("store")
    private Store store;

    @JsonProperty("staff")
    private List<Staff> allStaff;

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public List<Staff> getAllStaff() {
        return allStaff;
    }

    public void setAllStaff(List<Staff> allStaff) {
        this.allStaff = allStaff;
    }
}
