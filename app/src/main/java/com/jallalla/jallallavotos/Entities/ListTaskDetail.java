package com.jallalla.jallallavotos.Entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTaskDetail {

    @SerializedName("nombre_distrito")
    @Expose
    private String nombreDistrito;
    @SerializedName("nombre_unidad")
    @Expose
    private String nombreUnidad;
    @SerializedName("nro_mesa")
    @Expose
    private Integer nroMesa;
    @SerializedName("id_mesa")
    @Expose
    private Integer idMesa;
    @SerializedName("codigo_distrito")
    @Expose
    private String codigoDistrito;
    @SerializedName("codigo_colegio")
    @Expose
    private Integer codigoColegio;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListTaskDetail() {
    }

    /**
     *
     * @param codigoColegio
     * @param idMesa
     * @param codigoDistrito
     * @param nombreDistrito
     * @param nroMesa
     * @param nombreUnidad
     */
    public ListTaskDetail(String nombreDistrito, String nombreUnidad, Integer nroMesa, Integer idMesa, String codigoDistrito, Integer codigoColegio) {
        super();
        this.nombreDistrito = nombreDistrito;
        this.nombreUnidad = nombreUnidad;
        this.nroMesa = nroMesa;
        this.idMesa = idMesa;
        this.codigoDistrito = codigoDistrito;
        this.codigoColegio = codigoColegio;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public ListTaskDetail withNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
        return this;
    }

    public String getNombreUnidad() {
        return nombreUnidad;
    }

    public void setNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
    }

    public ListTaskDetail withNombreUnidad(String nombreUnidad) {
        this.nombreUnidad = nombreUnidad;
        return this;
    }

    public Integer getNroMesa() {
        return nroMesa;
    }

    public void setNroMesa(Integer nroMesa) {
        this.nroMesa = nroMesa;
    }

    public ListTaskDetail withNroMesa(Integer nroMesa) {
        this.nroMesa = nroMesa;
        return this;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public ListTaskDetail withIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
        return this;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public ListTaskDetail withCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
        return this;
    }

    public Integer getCodigoColegio() {
        return codigoColegio;
    }

    public void setCodigoColegio(Integer codigoColegio) {
        this.codigoColegio = codigoColegio;
    }

    public ListTaskDetail withCodigoColegio(Integer codigoColegio) {
        this.codigoColegio = codigoColegio;
        return this;
    }

}