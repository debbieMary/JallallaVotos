package com.jallalla.jallallavotos.CounterData.presenter;


import com.jallalla.jallallavotos.Entities.CounterDataBody;

public interface CounterPresenter {
    void insertCounterData(CounterDataBody counterDataBody);
    void onDestroy();
}
