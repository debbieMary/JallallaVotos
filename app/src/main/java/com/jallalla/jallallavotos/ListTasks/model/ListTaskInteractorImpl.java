package com.jallalla.jallallavotos.ListTasks.model;

import android.util.Log;

import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDataResponse;
import com.jallalla.jallallavotos.Network.ApiService;
import com.jallalla.jallallavotos.Network.ApiUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListTaskInteractorImpl implements ListTaskInteractor {
    ApiService apiService;
    private final String TAG="[LIST_TASK_SERVICE]";
    private final String AUTHORIZATION="cgk0uueutwai";

    @Override
    public void getTasks(ListTaskBody listTaskBody, final onListTaskFinishedListener listener) {
        apiService = ApiUtils.getJallallaService();
        Call<ListTaskDataResponse> call = apiService.getListTask(AUTHORIZATION,listTaskBody);
        call.enqueue(new Callback<ListTaskDataResponse>() {
            @Override
            public void onResponse(Call<ListTaskDataResponse> call, Response<ListTaskDataResponse> response) {
                if(response.isSuccessful()){
                    Log.d(TAG,"SUCCESS!!! : "+  response.body().getData());
                    listener.onSuccess(response.body().getData());
                }
            }
            @Override
            public void onFailure(Call<ListTaskDataResponse> call, Throwable t) {
                Log.e(TAG,"ERROR!!!  "+  t.getMessage());
                listener.onFailed(t.getMessage());
            }
        });
    }

}
