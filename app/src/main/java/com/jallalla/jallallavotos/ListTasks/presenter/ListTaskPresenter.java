package com.jallalla.jallallavotos.ListTasks.presenter;


import com.jallalla.jallallavotos.Entities.ListTaskBody;
public interface ListTaskPresenter {
    void getListTask(ListTaskBody listTaskBody);
    void onDestroy();
}
