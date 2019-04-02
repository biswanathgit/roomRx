package com.uipl.test.localDB;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


@Entity(tableName = "tblCountry")
public class Country {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    int id;
    @ColumnInfo(name = "country_name")
    String countryName;
    @ColumnInfo(name = "country_code")
    String countryCode;

    @ColumnInfo(name = "country_no")
    String countryNo;

    public Country(@NonNull int id, String countryName, String countryCode, String countryNo) {
        this.id = id;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.countryNo = countryNo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
