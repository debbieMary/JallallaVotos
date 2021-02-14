package com.jallalla.jallallavotos.Entities;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTaskDataResponse {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("mensaje")
    @Expose
    private String mensaje;
    @SerializedName("data")
    @Expose
    private List<ListTaskDetail> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListTaskDataResponse() {
    }

    /**
     *
     * @param data
     * @param mensaje
     * @param status
     */
    public ListTaskDataResponse(String status, String mensaje, List<ListTaskDetail> data) {
        super();
        this.status = status;
        this.mensaje = mensaje;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ListTaskDataResponse withStatus(String status) {
        this.status = status;
        return this;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public ListTaskDataResponse withMensaje(String mensaje) {
        this.mensaje = mensaje;
        return this;
    }

    public List<ListTaskDetail> getData() {
        return data;
    }

    public void setData(List<ListTaskDetail> data) {
        this.data = data;
    }

    public ListTaskDataResponse withData(List<ListTaskDetail> data) {
        this.data = data;
        return this;
    }

}
