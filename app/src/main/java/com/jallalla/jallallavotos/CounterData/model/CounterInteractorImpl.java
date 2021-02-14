package com.jallalla.jallallavotos.CounterData.model;

import android.util.Log;

import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.Entities.CounterDataResponse;
import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Network.ApiService;
import com.jallalla.jallallavotos.Network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CounterInteractorImpl implements CounterDataInteractor {
    ApiService apiService;
    private final String TAG="[COUNTER_SERVICE]";
    private final String AUTHORIZATION="cgk0uueutwai";

    @Override
    public void counterRegister(CounterDataBody counterDataBody, final onCounterFinishedListener listener) {
        apiService = ApiUtils.getJallallaService();
        Call<CounterDataResponse> call = apiService.insertCounterData(AUTHORIZATION,counterDataBody);
        call.enqueue(new Callback<CounterDataResponse>() {
            @Override
            public void onResponse(Call<CounterDataResponse> call, Response<CounterDataResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"SUCCESS!!! : "+  response.body().getResultaldo());
                    listener.onSuccess(response.body().getMensaje());
                }
            }
            @Override
            public void onFailure(Call<CounterDataResponse> call, Throwable t) {
                Log.e(TAG,"ERROR!!!  "+  t.getMessage());
                listener.onFailed(t.getMessage());
            }
        });
    }
}