package com.jallalla.jallallavotos.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.jallalla.jallallavotos.Database.dao.MilitantesDao;
import com.jallalla.jallallavotos.Database.entities.Militantes;


@Database(entities = {
        Militantes.class
} ,  version = 1)
public abstract class MyDataBase extends RoomDatabase {

    public abstract MilitantesDao militantesDao();

}
