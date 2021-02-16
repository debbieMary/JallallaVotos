package com.jallalla.jallallavotos.ListTasks.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jallalla.jallallavotos.CounterData.view.CounterFormActivity;
import com.jallalla.jallallavotos.Database.MyDataBase;
import com.jallalla.jallallavotos.Database.entities.ListTaskDetails;
import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDetail;
import com.jallalla.jallallavotos.ListTasks.model.ListTaskInteractorImpl;
import com.jallalla.jallallavotos.ListTasks.presenter.ListTaskPresenter;
import com.jallalla.jallallavotos.ListTasks.presenter.ListTaskPresenterImpl;
import com.jallalla.jallallavotos.ListTasks.view.adapters.ListTaskAdapter;
import com.jallalla.jallallavotos.ListTasks.view.adapters.RecyclerItemClickListener;
import com.jallalla.jallallavotos.R;

import java.util.ArrayList;
import java.util.List;

public class ListTaskActivity extends AppCompatActivity implements ListTaskView {

    ListTaskBody listTaskBody = new ListTaskBody();
    ListTaskPresenter listTaskPresenter;
    ProgressDialog progressDialog;

    TextView nombre_militante;

    ListTaskAdapter adapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<ListTaskDetail> listTaskDetailsArray = new ArrayList<ListTaskDetail>();

    Integer int_id_militante;
    String string_nombres, string_apellidos;

    private static final String DATABASE_NAME_JALLALLA = "jallallaVotosDB";
    public static MyDataBase myDataBase;

    public static Activity listTaskActivityClass;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listTaskActivityClass = ListTaskActivity.this;

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, DATABASE_NAME_JALLALLA).allowMainThreadQueries().build();
        setMilitanteValues();

        listTaskPresenter = new ListTaskPresenterImpl(this, new ListTaskInteractorImpl());
        progressDialog = new ProgressDialog(ListTaskActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.list_progress_dialog_message));
        progressDialog.setCancelable(false);

        initListElements();
        checkListData();
        nombre_militante.setText(getString(R.string.list_nombre_militante) + " " + string_nombres + " " + string_apellidos);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(ListTaskActivity.this, recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        goToPage(position);
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                })
        );
    }


    public void checkListData() {
        if (myDataBase.listTaskDetailsDao().getListTaksDetails(0).size() == 0) {
            listTaskBody.setId(int_id_militante);
            listTaskPresenter.getListTask(listTaskBody);
        } else {
            fillFromDataBase(myDataBase.listTaskDetailsDao().getListTaksDetails(0));
        }
    }

    public void fillFromDataBase(List<ListTaskDetails> listTasks) {
        ArrayList<ListTaskDetail> dataBaseList = new ArrayList<>();
        for (int i = 0; i < listTasks.size(); i++) {
            ListTaskDetail listTaskDetailElement = new ListTaskDetail();
            listTaskDetailElement.setCodigoColegio(listTasks.get(i).getCodigo_colegio());
            listTaskDetailElement.setCodigoDistrito(listTasks.get(i).getCodigo_distrito());
            listTaskDetailElement.setIdMesa(listTasks.get(i).getId_mesa());
            listTaskDetailElement.setNombreDistrito(listTasks.get(i).getNombre_distrito());
            listTaskDetailElement.setNombreUnidad(listTasks.get(i).getNombre_unidad());
            listTaskDetailElement.setNroMesa(listTasks.get(i).getNro_mesa());
            listTaskDetailElement.setEstado(listTasks.get(i).getEstado());
            dataBaseList.add(listTaskDetailElement);
        }

        Log.e("$$$$$","here");
        refreshList(dataBaseList);
    }

    public void refreshList(List<ListTaskDetail> listTasks) {
        listTaskDetailsArray.clear();
        listTaskDetailsArray.addAll(listTasks);
        adapter.notifyDataSetChanged();
    }

    public void setMilitanteValues() {
        int_id_militante = myDataBase.militantesDao().getMilitante().getId();
        string_nombres = myDataBase.militantesDao().getMilitante().getNombres();
        string_apellidos = myDataBase.militantesDao().getMilitante().getApellidos();
    }

    public void goToPage(Integer position) {
        Intent intent = new Intent(this, CounterFormActivity.class);
        intent.putExtra("codigo_colegio", listTaskDetailsArray.get(position).getCodigoColegio());
        intent.putExtra("codigo_distrito", listTaskDetailsArray.get(position).getCodigoDistrito());
        intent.putExtra("id_mesa", listTaskDetailsArray.get(position).getIdMesa());
        intent.putExtra("nombre_unidad", listTaskDetailsArray.get(position).getNombreUnidad());
        intent.putExtra("id_militante", int_id_militante);
        startActivity(intent);
    }

    public void initListElements() {
        nombre_militante = (TextView) findViewById(R.id.lbl_nombre_militante);
        recyclerView = (RecyclerView) findViewById(R.id.rv_task_list);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ListTaskAdapter(listTaskDetailsArray, getApplicationContext());
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void populateListTask(List<ListTaskDetail> listTasks) {
        refreshList(listTasks);
        fillDataFromServer(listTasks);
    }


    public void fillDataFromServer(List<ListTaskDetail> listTasks) {
        List<ListTaskDetails> dataBaseList = new ArrayList<>();
        for (int i = 0; i < listTasks.size(); i++) {
            ListTaskDetails dataBaseListElement = new ListTaskDetails();
            dataBaseListElement.setId_pendiente(int_id_militante + "_" + listTasks.get(i).getCodigoDistrito() + "_" + listTasks.get(i).getCodigoColegio() + "_" + listTasks.get(i).getNroMesa());
            dataBaseListElement.setCodigo_colegio(listTasks.get(i).getCodigoColegio());
            dataBaseListElement.setCodigo_distrito(listTasks.get(i).getCodigoDistrito());
            dataBaseListElement.setId_mesa(listTasks.get(i).getIdMesa());
            dataBaseListElement.setNombre_distrito(listTasks.get(i).getNombreDistrito());
            dataBaseListElement.setNombre_unidad(listTasks.get(i).getNombreUnidad());
            dataBaseListElement.setNro_mesa(listTasks.get(i).getNroMesa());
            dataBaseListElement.setEstado(listTasks.get(i).getEstado());
            dataBaseList.add(dataBaseListElement);
        }
        myDataBase.listTaskDetailsDao().insertListTasksDetails(dataBaseList);
    }


    public void logout(View v) {
        final AlertDialog.Builder alertDialogLogout = new AlertDialog.Builder(
                ListTaskActivity.this);

        alertDialogLogout.setTitle(getString(R.string.list_menu_logout_title));
        alertDialogLogout.setMessage(getString(R.string.list_menu_logout_message));
        alertDialogLogout.setCancelable(false);
        alertDialogLogout.setPositiveButton(getString(R.string.list_menu_logout_yes),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        myDataBase.militantesDao().deleteAllMilitante();
                        myDataBase.listTaskDetailsDao().deleteAllListTakDetails();
                        finish();
                    }
                });
        alertDialogLogout.setNegativeButton(getString(R.string.list_menu_logout_no),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        alertDialogLogout.show();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
