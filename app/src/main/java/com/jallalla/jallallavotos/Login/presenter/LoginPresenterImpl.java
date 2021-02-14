package com.jallalla.jallallavotos.Login.presenter;


import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.Militante;
import com.jallalla.jallallavotos.Login.model.LoginInteractor;
import com.jallalla.jallallavotos.Login.view.LoginView;

public class LoginPresenterImpl implements LoginPresenter, LoginInteractor.onLoginFinishedListener {
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView, LoginInteractor loginInteractor) {
        this.loginView = loginView;
        this.loginInteractor = loginInteractor;
    }


    @Override
    public void onSuccess(Militante militante) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.populateMilitante(militante);
        }
    }

    @Override
    public void onFailed(String message) {
        if (loginView != null) {
            loginView.hideProgress();
            loginView.showErrorMessage(message);
        }
    }


    @Override
    public void getMilitanteData(LoginBody loginBody) {
        loginInteractor.getMilitante(loginBody, this);
        if (loginView != null) {
            loginView.showProgress();
        }
    }

    @Override
    public void onDestroy() {
        loginView = null;
    }
}
