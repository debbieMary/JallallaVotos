package com.jallalla.jallallavotos.CounterData.view;

import com.jallalla.jallallavotos.Entities.Militante;

public interface CounterView {
    void showProgress();

    void hideProgress();

    void populateResponse(String successMessage);

    void showErrorMessage(String message);
}
