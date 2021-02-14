package com.jallalla.jallallavotos.Login.view;

import com.jallalla.jallallavotos.Entities.Militante;

public interface LoginView {
    void showProgress();

    void hideProgress();

    void populateMilitante(Militante militante);

    void showErrorMessage(String message);
}
