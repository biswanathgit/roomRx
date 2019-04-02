
package com.uipl.test.localDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface CountryDao {
    @Query("select * from tblCountry")
    List<Country> loadAllCountries();

    @Query("select * from tblCountry where country_name = :name")
    Country getCountryByName(String name);

    @Query("select * from tblCountry where id = :id")
    Country getCountryById(int id);

    @Query("select country_code from tblCountry where country_name = :countryName")
    String getCountryCodeByCountryName(String countryName);

    @Insert(onConflict = IGNORE)
    void insertCountry(Country country);

    @Delete
    void deleteCountry(Country user);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceCountry(Country... users);

    @Delete
    void deleteUsers(Country country);

    @Query("DELETE FROM tblCountry")
    void deleteAll();
}