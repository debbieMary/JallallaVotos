package com.jallalla.jallallavotos.ListTasks.presenter;


import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDetail;
import com.jallalla.jallallavotos.ListTasks.model.ListTaskInteractor;
import com.jallalla.jallallavotos.ListTasks.view.ListTaskView;

import java.util.List;

public class ListTaskPresenterImpl implements ListTaskPresenter, ListTaskInteractor.onListTaskFinishedListener {
    private ListTaskView listTaskView;
    private ListTaskInteractor listTaskInteractor;

    public ListTaskPresenterImpl(ListTaskView listTaskView, ListTaskInteractor listTaskInteractor) {
        this.listTaskView = listTaskView;
        this.listTaskInteractor = listTaskInteractor;
    }



    @Override
    public void onSuccess(List<ListTaskDetail> listTask) {
        if (listTaskView != null) {
            listTaskView.hideProgress();
            listTaskView.populateListTask(listTask);
        }
    }

    @Override
    public void onFailed(String message) {
        if (listTaskView != null) {
            listTaskView.hideProgress();
            listTaskView.showErrorMessage(message);
        }
    }


    @Override
    public void getListTask(ListTaskBody listTaskBody) {
        listTaskInteractor.getTasks(listTaskBody, this);
        if (listTaskView != null) {
            listTaskView.showProgress();
        }
    }

    @Override
    public void onDestroy() {
        listTaskView = null;
    }
}
