/*package com.jallalla.jallallavotos.Database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;


import com.jallalla.jallallavotos.Database.entities.ListTaskDetails;

import java.util.List;

@Dao
public interface ListTaskDetailsDao {

    @Query("SELECT * from list_task_details where estado = :ESTADO")
    List<ListTaskDetails> getListTasksDetails(Integer ESTADO);

    @Query("DELETE FROM list_task_details")
    void deleteAllTasksDetails();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListTasks(List<ListTaskDetails> listTaskDetails);
}*/

package com.jallalla.jallallavotos.Database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.jallalla.jallallavotos.Database.entities.ListTaskDetails;
import com.jallalla.jallallavotos.Database.entities.Militantes;

import java.util.ArrayList;
import java.util.List;


@Dao
public interface ListTaskDetailsDao {

    @Query("SELECT * from list_task_details where estado= :ESTADO ORDER by id_mesa")
    List<ListTaskDetails> getListTaksDetails(Integer ESTADO);


    @Query("DELETE FROM list_task_details")
    void deleteAllListTakDetails();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListTasksDetails(List<ListTaskDetails> listTaskDetails);
}
