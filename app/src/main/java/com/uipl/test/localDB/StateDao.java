
package com.uipl.test.localDB;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.ArrayList;
import java.util.List;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface StateDao {

    @Query("select * from tblState where country_code = :cCode")
    List<State> getStateByCountryCode(String cCode);

    @Query("select * from tblState where id = :id")
    State getStateById(int id);

    @Insert(onConflict = IGNORE)
    void insertState(State user);

    @Insert(onConflict = IGNORE)
    void insertOrReplaceStates(State... states);

    @Delete
    void deleteState(State st);

    @Query("DELETE FROM tblState")
    void deleteAll();
}