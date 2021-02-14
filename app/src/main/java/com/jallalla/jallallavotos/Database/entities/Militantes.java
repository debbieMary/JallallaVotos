package com.jallalla.jallallavotos.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "militante")
public class Militantes {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private Integer id;

    @NonNull
    @ColumnInfo(name = "usuario")
    private String usuario;


    @NonNull
    @ColumnInfo(name = "ci")
    private Integer ci;


    @NonNull
    public String getUsuario() {
        return usuario;
    }

    @NonNull
    public Integer getId() {
        return id;
    }


    @NonNull
    public Integer getCi() {
        return ci;
    }

    public void setUsuario(@NonNull String usuario) {
        this.usuario = usuario;
    }

    public void setId(@NonNull Integer id) {
        this.id = id;
    }

    public void setCi(@NonNull Integer ci) {
        this.ci = ci;
    }
}
