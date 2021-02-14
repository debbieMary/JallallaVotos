package com.jallalla.jallallavotos.Login.model;

import android.util.Log;

import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.LoginResponse;
import com.jallalla.jallallavotos.Network.ApiService;
import com.jallalla.jallallavotos.Network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginInteractorImpl implements LoginInteractor {
    ApiService apiService;
    private final String TAG="[LOGIN_SERVICE]";
    private final String INCORRECT_USER="[INCORRECT_USER]";
    private final String AUTHORIZATION="cgk0uueutwai";

    @Override
    public void getMilitante(LoginBody loginBody, final onLoginFinishedListener listener) {
        apiService = ApiUtils.getJallallaService();
        Call<LoginResponse> call = apiService.loginMilitante(AUTHORIZATION,loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"SUCCESS!!! : "+  response.body().getData());
                    if(response.body().getData().size()>0){
                        listener.onSuccess(response.body().getData().get(0));
                    }else{
                        listener.onFailed(INCORRECT_USER);
                    }
                }
            }
            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.e(TAG,"ERROR!!!  "+  t.getMessage());
                listener.onFailed(t.getMessage());
            }
        });
    }

}
