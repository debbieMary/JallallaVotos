package com.jallalla.jallallavotos.Network;

import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.Entities.CounterDataResponse;
import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDataResponse;
import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ApiService {




    @POST("militante/login")
    Call<LoginResponse> loginMilitante(@Header("Authorization") String authHeader,@Body LoginBody user);

    @POST("/delegadosmesa/listarPendientes")
    Call<ListTaskDataResponse> getListTask(@Header("Authorization") String authHeader,@Body ListTaskBody listTaskBody);

    @PUT("/militante/registrarVoto")
    Call<CounterDataResponse> insertCounterData(@Header("Authorization") String authHeader,@Body CounterDataBody counterDataBody);

}
