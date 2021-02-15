package com.jallalla.jallallavotos.Database.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "list_task_details")
public class ListTaskDetails {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id_pendiente")
    private String id_pendiente;


    @NonNull
    @ColumnInfo(name = "nombre_distrito")
    private String nombre_distrito;

    @NonNull
    @ColumnInfo(name = "nombre_unidad")
    private String nombre_unidad;

    @NonNull
    @ColumnInfo(name = "nro_mesa")
    private Integer nro_mesa;

    @NonNull
    @ColumnInfo(name = "id_mesa")
    private Integer id_mesa;

    @NonNull
    @ColumnInfo(name = "codigo_distrito")
    private String codigo_distrito;

    @NonNull
    @ColumnInfo(name = "codigo_colegio")
    private String codigo_colegio;

    @NonNull
    @ColumnInfo(name = "estado")
    private Integer estado;


    @NonNull
    public String getNombre_distrito() {
        return nombre_distrito;
    }

    public void setNombre_distrito(@NonNull String nombre_distrito) {
        this.nombre_distrito = nombre_distrito;
    }

    @NonNull
    public String getNombre_unidad() {
        return nombre_unidad;
    }

    public void setNombre_unidad(@NonNull String nombre_unidad) {
        this.nombre_unidad = nombre_unidad;
    }

    @NonNull
    public Integer getNro_mesa() {
        return nro_mesa;
    }

    public void setNro_mesa(@NonNull Integer nro_mesa) {
        this.nro_mesa = nro_mesa;
    }

    @NonNull
    public Integer getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(@NonNull Integer id_mesa) {
        this.id_mesa = id_mesa;
    }

    @NonNull
    public String getCodigo_distrito() {
        return codigo_distrito;
    }

    public void setCodigo_distrito(@NonNull String codigo_distrito) {
        this.codigo_distrito = codigo_distrito;
    }

    @NonNull
    public String getCodigo_colegio() {
        return codigo_colegio;
    }

    public void setCodigo_colegio(@NonNull String codigo_colegio) {
        this.codigo_colegio = codigo_colegio;
    }

    @NonNull
    public Integer getEstado() {
        return estado;
    }

    public void setEstado(@NonNull Integer estado) {
        this.estado = estado;
    }

    @NonNull
    public String getId_pendiente() {
        return id_pendiente;
    }

    public void setId_pendiente(@NonNull String id_pendiente) {
        this.id_pendiente = id_pendiente;
    }
}