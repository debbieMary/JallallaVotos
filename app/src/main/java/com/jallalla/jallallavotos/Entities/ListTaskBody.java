package com.jallalla.jallallavotos.Entities;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTaskBody {

    @SerializedName("id")
    @Expose
    private Integer id;

    /**
     * No args constructor for use in serialization
     *
     */
    public ListTaskBody() {
    }

    /**
     *
     * @param id
     */
    public ListTaskBody(Integer id) {
        super();
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ListTaskBody withId(Integer id) {
        this.id = id;
        return this;
    }

}