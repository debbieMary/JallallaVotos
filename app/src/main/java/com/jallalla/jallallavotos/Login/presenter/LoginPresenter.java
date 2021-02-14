package com.jallalla.jallallavotos.Login.presenter;


import com.jallalla.jallallavotos.Entities.LoginBody;
public interface LoginPresenter {
    void getMilitanteData(LoginBody loginBody);
    void onDestroy();
}
