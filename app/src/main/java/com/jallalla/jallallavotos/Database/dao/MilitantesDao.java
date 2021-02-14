package com.jallalla.jallallavotos.Database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jallalla.jallallavotos.Database.entities.Militantes;

import java.util.List;

@Dao
public interface MilitantesDao {

    @Query("SELECT * from militante ORDER by id")
    List<Militantes> getMilitante();


    @Query("DELETE FROM militante")
    void deleteAllMilitante();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMilitante(Militantes militante);
}
