package com.jallalla.jallallavotos.CounterData.presenter;


import com.jallalla.jallallavotos.CounterData.model.CounterDataInteractor;
import com.jallalla.jallallavotos.CounterData.view.CounterView;
import com.jallalla.jallallavotos.Entities.CounterData;
import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.Militante;
import com.jallalla.jallallavotos.Login.model.LoginInteractor;
import com.jallalla.jallallavotos.Login.presenter.LoginPresenter;
import com.jallalla.jallallavotos.Login.view.LoginView;

public class CounterPresenterImpl implements CounterPresenter, CounterDataInteractor.onCounterFinishedListener {
    private CounterView counterView;
    private CounterDataInteractor counterInteractor;

    public CounterPresenterImpl(CounterView counterView, CounterDataInteractor counterInteractor) {
        this.counterView = counterView;
        this.counterInteractor = counterInteractor;
    }

    @Override
    public void onSuccess(String successMessage) {
        if (counterView != null) {
            counterView.hideProgress();
            counterView.populateResponse(successMessage);
        }
    }

    @Override
    public void onFailed(String message) {
        if (counterView != null) {
            counterView.hideProgress();
            counterView.showErrorMessage(message);
        }
    }



    @Override
    public void insertCounterData(CounterDataBody counterDataBody) {
        counterInteractor.counterRegister(counterDataBody, this);
        if (counterView != null) {
            counterView.showProgress();
        }
    }

    @Override
    public void onDestroy() {
        counterView = null;
    }
}
