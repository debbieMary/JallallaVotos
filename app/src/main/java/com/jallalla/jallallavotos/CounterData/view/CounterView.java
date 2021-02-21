package com.jallalla.jallallavotos.CounterData.view;

import com.jallalla.jallallavotos.Entities.Militante;

public interface CounterView {
    void showProgressCounter();

    void hideProgressCounter();

    void populateResponse(String successMessage);

    void showErrorMessageCounter(String message);
}
