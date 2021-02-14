package com.jallalla.jallallavotos.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Militante {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("usuario")
    @Expose
    private String usuario;
    @SerializedName("ci")
    @Expose
    private Integer ci;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("apellidos")
    @Expose
    private String apellidos;

    /**
     * No args constructor for use in serialization
     *
     */
    public Militante() {
    }

    /**
     *
     * @param apellidos
     * @param ci
     * @param usuario
     * @param id
     * @param nombres
     */
    public Militante(Integer id, String usuario, Integer ci, String nombres, String apellidos) {
        super();
        this.id = id;
        this.usuario = usuario;
        this.ci = ci;
        this.nombres = nombres;
        this.apellidos = apellidos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Militante withId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Militante withUsuario(String usuario) {
        this.usuario = usuario;
        return this;
    }

    public Integer getCi() {
        return ci;
    }

    public void setCi(Integer ci) {
        this.ci = ci;
    }

    public Militante withCi(Integer ci) {
        this.ci = ci;
        return this;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public Militante withNombres(String nombres) {
        this.nombres = nombres;
        return this;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Militante withApellidos(String apellidos) {
        this.apellidos = apellidos;
        return this;
    }

}