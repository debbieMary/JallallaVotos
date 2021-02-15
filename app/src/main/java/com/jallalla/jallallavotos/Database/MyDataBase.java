package com.jallalla.jallallavotos.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jallalla.jallallavotos.Database.dao.ListTaskDetailsDao;
import com.jallalla.jallallavotos.Database.dao.MilitantesDao;
import com.jallalla.jallallavotos.Database.entities.ListTaskDetails;
import com.jallalla.jallallavotos.Database.entities.Militantes;

import java.util.List;


@Database(entities = {
        Militantes.class,
        ListTaskDetails.class
} ,  version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract MilitantesDao militantesDao();

    public abstract ListTaskDetailsDao listTaskDetailsDao();

}
