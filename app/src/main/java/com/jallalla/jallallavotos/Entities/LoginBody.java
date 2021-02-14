package com.jallalla.jallallavotos.Entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginBody {

    @SerializedName("ci")
    @Expose
    private String ci;
    @SerializedName("pass")
    @Expose
    private String pass;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoginBody() {
    }

    /**
     *
     * @param pass
     * @param ci
     */
    public LoginBody(String ci, String pass) {
        super();
        this.ci = ci;
        this.pass = pass;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

    public LoginBody withCi(String ci) {
        this.ci = ci;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public LoginBody withPass(String pass) {
        this.pass = pass;
        return this;
    }

}