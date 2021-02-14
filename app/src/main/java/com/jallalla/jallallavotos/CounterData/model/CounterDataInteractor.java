package com.jallalla.jallallavotos.CounterData.model;

import com.jallalla.jallallavotos.Entities.CounterDataBody;

public interface CounterDataInteractor {
    interface onCounterFinishedListener {
        void onSuccess(String successMessage);

        void onFailed(String message);
    }

    void counterRegister(CounterDataBody counterDataBody, onCounterFinishedListener listener);
}
