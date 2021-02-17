package com.jallalla.jallallavotos.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "register")
public class Register {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_register")
    private String id_register;

    @NonNull
    @ColumnInfo(name = "observaciones")
    private String observaciones;

    @NonNull
    @ColumnInfo(name = "foto")
    private String foto;

    @NonNull
    @ColumnInfo(name = "id_mesa")
    private String id_mesa;

    @NonNull
    public String getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(@NonNull String id_mesa) {
        this.id_mesa = id_mesa;
    }

    @NonNull
    public String getFecha_alta() {
        return fecha_alta;
    }


    public void setFecha_alta(@NonNull String fecha_alta) {
        this.fecha_alta = fecha_alta;
    }

    @NonNull
    @ColumnInfo(name = "fecha_alta")
    private String fecha_alta;

    @NonNull
    @ColumnInfo(name = "votos_jallalla_alcalde")
    private String votos_jallalla_alcalde;

    @NonNull
    @ColumnInfo(name = "votos_jallalla_concejal")
    private String votos_jallalla_concejal;

    @NonNull
    @ColumnInfo(name = "votos_mas_alcalde")
    private String votos_mas_alcalde;

    @NonNull
    @ColumnInfo(name = "votos_mas_concejal")
    private String votos_mas_concejal;

    @NonNull
    public String getId_register() {
        return id_register;
    }

    public void setId_register(@NonNull String id_register) {
        this.id_register = id_register;
    }

    @NonNull
    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(@NonNull String observaciones) {
        this.observaciones = observaciones;
    }

    @NonNull
    public String getFoto() {
        return foto;
    }

    public void setFoto(@NonNull String foto) {
        this.foto = foto;
    }

    @NonNull
    public String getVotos_jallalla_alcalde() {
        return votos_jallalla_alcalde;
    }

    public void setVotos_jallalla_alcalde(@NonNull String votos_jallalla_alcalde) {
        this.votos_jallalla_alcalde = votos_jallalla_alcalde;
    }

    @NonNull
    public String getVotos_jallalla_concejal() {
        return votos_jallalla_concejal;
    }

    public void setVotos_jallalla_concejal(@NonNull String votos_jallalla_concejal) {
        this.votos_jallalla_concejal = votos_jallalla_concejal;
    }

    @NonNull
    public String getVotos_mas_alcalde() {
        return votos_mas_alcalde;
    }

    public void setVotos_mas_alcalde(@NonNull String votos_mas_alcalde) {
        this.votos_mas_alcalde = votos_mas_alcalde;
    }

    @NonNull
    public String getVotos_mas_concejal() {
        return votos_mas_concejal;
    }

    public void setVotos_mas_concejal(@NonNull String votos_mas_concejal) {
        this.votos_mas_concejal = votos_mas_concejal;
    }
}
