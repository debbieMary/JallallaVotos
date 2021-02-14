package com.jallalla.jallallavotos.Login.model;

import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.Militante;


public interface LoginInteractor {
    interface onLoginFinishedListener {
        void onSuccess(Militante Militante);

        void onFailed(String message);
    }

    void getMilitante(LoginBody loginBody, onLoginFinishedListener listener);
}
