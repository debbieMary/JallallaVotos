package com.jallalla.jallallavotos.ListTasks.model;


import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDetail;

import java.util.List;


public interface ListTaskInteractor {
    interface onListTaskFinishedListener {
        void onSuccess(List<ListTaskDetail> listTask);

        void onFailed(String message);
    }

    void getTasks(ListTaskBody listTaskBody, onListTaskFinishedListener listener);
}
