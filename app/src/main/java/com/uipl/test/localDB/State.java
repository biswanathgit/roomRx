package com.uipl.test.localDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity(tableName = "tblState")
public class State {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "state_code")
    String stateCode;
    @ColumnInfo(name = "country_code")
    String countryCode;
    @ColumnInfo(name = "state_name")
    String stateName;


    public State(int id, String stateCode, String countryCode, String stateName) {
        this.id = id;
        this.stateCode = stateCode;
        this.countryCode = countryCode;
        this.stateName = stateName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }
}
