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
    @ColumnInfo(name = "nombres")
    private String nombres;

    @NonNull
    @ColumnInfo(name = "apellidos")
    private String apellidos;

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

    @NonNull
    public String getNombres() {
        return nombres;
    }

    @NonNull
    public String getApellidos() {
        return nombres;
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

    public void setNombres(@NonNull String ci) {
        this.nombres = nombres;
    }

    public void setApellidos(@NonNull String ci) {
        this.apellidos = apellidos;
    }

}
