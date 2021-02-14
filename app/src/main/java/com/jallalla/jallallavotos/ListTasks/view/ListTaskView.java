package com.jallalla.jallallavotos.ListTasks.view;

import com.jallalla.jallallavotos.Entities.ListTaskDetail;
import java.util.List;

public interface ListTaskView {
    void showProgress();

    void hideProgress();

    void populateListTask(List<ListTaskDetail> listTasks);

    void showErrorMessage(String message);
}
