package com.jallalla.jallallavotos.ListTasks.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.jallalla.jallallavotos.CounterData.model.CounterInteractorImpl;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenter;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenterImpl;
import com.jallalla.jallallavotos.CounterData.view.CounterFormActivity;
import com.jallalla.jallallavotos.CounterData.view.CounterView;
import com.jallalla.jallallavotos.Database.MyDataBase;
import com.jallalla.jallallavotos.Database.entities.ListTaskDetails;
import com.jallalla.jallallavotos.Database.entities.Register;
import com.jallalla.jallallavotos.Entities.CounterData;
import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.Entities.ListTaskBody;
import com.jallalla.jallallavotos.Entities.ListTaskDetail;
import com.jallalla.jallallavotos.ListTasks.model.ListTaskInteractorImpl;
import com.jallalla.jallallavotos.ListTasks.presenter.ListTaskPresenter;
import com.jallalla.jallallavotos.ListTasks.presenter.ListTaskPresenterImpl;
import com.jallalla.jallallavotos.ListTasks.view.adapters.ListTaskAdapter;
import com.jallalla.jallallavotos.ListTasks.view.adapters.RecyclerItemClickListener;
import com.jallalla.jallallavotos.R;
import com.jallalla.jallallavotos.Utils.GeneralUtils;

import java.util.ArrayList;
import java.util.List;

public class ListTaskActivity extends AppCompatActivity implements ListTaskView, CounterView {

    ListTaskBody listTaskBody = new ListTaskBody();
    ListTaskPresenter listTaskPresenter;
    CounterPresenter counterPresenter;
    ProgressDialog progressDialogList, progressDialogCounter;

    TextView nombre_militante;

    ListTaskAdapter adapter;
    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    ArrayList<ListTaskDetail> listTaskDetailsArray = new ArrayList<ListTaskDetail>();

    Integer int_id_militante;
    String string_nombres, string_apellidos;
    String string_current_id;

    private static final String TAG = "[LIST_TASK_ACTIVITY]";

    private static final String DATABASE_NAME_JALLALLA = "jallallaVotosDB";
    public static MyDataBase myDataBase;

    public static Activity listTaskActivityClass;

    GeneralUtils generalUtils=  new GeneralUtils();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listTaskActivityClass = ListTaskActivity.this;

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, DATABASE_NAME_JALLALLA).allowMainThreadQueries().build();

        setMilitanteValues();
        listTaskBody.setId(int_id_militante);

        listTaskPresenter = new ListTaskPresenterImpl(this, new ListTaskInteractorImpl());
        counterPresenter = new CounterPresenterImpl(this, new CounterInteractorImpl());

        progressDialogList = new ProgressDialog(ListTaskActivity.this);
        progressDialogList.setMessage(getResources().getString(R.string.list_progress_dialog_message));
        progressDialogList.setCancelable(false);

        progressDialogCounter= new ProgressDialog(ListTaskActivity.this);
        progressDialogCounter.setMessage(getResources().getString(R.string.counter_progress_dialog_message));
        progressDialogCounter.setCancelable(false);

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
                        if(listTaskDetailsArray.get(position).getEstado() == 1){
                            syncConteoData(position);
                        }
                    }
                })
        );

    }


    public void  syncConteoData(Integer position){
        Register register = new Register();
        register= myDataBase.registerDao().getRegisterWhereId(int_id_militante + "_" + listTaskDetailsArray.get(position).getCodigoDistrito() + "_" + listTaskDetailsArray.get(position).getCodigoColegio() + "_" + listTaskDetailsArray.get(position).getNroMesa());
        prepareArray(register);

    }


    public void prepareArray(Register register){

        string_current_id= register.getId_register();

        CounterDataBody counterDataBody = new CounterDataBody();
        ArrayList<CounterData> counterData = new ArrayList<CounterData>();

        CounterData counterData1 = new CounterData();
        counterData1.setFechaAlta(register.getFecha_alta());
        counterData1.setIdMesa(register.getId_mesa());
        counterData1.setIdMilitante(String.valueOf(int_id_militante));
        counterData1.setSigla("JALLALLA");
        counterData1.setIdPartidoPolitico("1");
        counterData1.setVotosAlcalde(register.getVotos_jallalla_alcalde());
        counterData1.setVotosConcejal(register.getVotos_jallalla_concejal());


        CounterData counterData2 = new CounterData();
        counterData2.setFechaAlta(register.getFecha_alta());
        counterData2.setIdMesa(register.getId_mesa());
        counterData2.setIdMilitante(String.valueOf(int_id_militante));
        counterData2.setSigla("MAS");
        counterData2.setIdPartidoPolitico("2");
        counterData2.setVotosAlcalde(register.getVotos_mas_alcalde());
        counterData2.setVotosConcejal(register.getVotos_mas_concejal());

        counterData.add(counterData1);
        counterData.add(counterData2);

        counterDataBody.setObservaciones(register.getObservaciones());
        counterDataBody.setDatos(counterData);
        counterDataBody.setFoto(("data:image/jpeg;base64," + generalUtils.getBase64FromPath(register.getFoto())).replaceAll("\n", ""));
        Log.e("json", new Gson().toJson(counterDataBody).toString());
        counterPresenter.insertCounterData(counterDataBody);
    }

    public void checkListData() {
       if (myDataBase.listTaskDetailsDao().getListTaksDetails(2).size() == 0) {
            listTaskPresenter.getListTask(listTaskBody);
        } else {
           fillFromDataBaseAndRefreshArray(myDataBase.listTaskDetailsDao().getListTaksDetails(2));
        }
    }

    public void fillFromDataBaseAndRefreshArray(List<ListTaskDetails> listTasks) {
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
        String id_listado_pendiente= int_id_militante + "_" + listTaskDetailsArray.get(position).getCodigoDistrito() + "_" + listTaskDetailsArray.get(position).getCodigoColegio() + "_" + listTaskDetailsArray.get(position).getNroMesa();
        if(myDataBase.registerDao().getRegisterWhereId(id_listado_pendiente) == null){
            Intent intent = new Intent(this, CounterFormActivity.class);
            intent.putExtra("codigo_colegio", listTaskDetailsArray.get(position).getCodigoColegio());
            intent.putExtra("codigo_distrito", listTaskDetailsArray.get(position).getCodigoDistrito());
            intent.putExtra("id_mesa", listTaskDetailsArray.get(position).getIdMesa());
            intent.putExtra("numero_de_mesa", listTaskDetailsArray.get(position).getNroMesa());
            intent.putExtra("id_listado_pendiente",id_listado_pendiente);
            intent.putExtra("nombre_unidad", listTaskDetailsArray.get(position).getNombreUnidad());
            intent.putExtra("id_militante", int_id_militante);
            startActivity(intent);
        }

    }

    public void refreshAction(View v){
        listTaskPresenter.getListTask(listTaskBody);
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
        progressDialogList.show();
    }

    @Override
    public void hideProgress() {
        progressDialogList.hide();
    }

    @Override
    public void showProgressCounter() {
        progressDialogCounter.show();
    }

    @Override
    public void hideProgressCounter() {
        progressDialogCounter.show();
    }

    @Override
    public void populateResponse(String successMessage) {
        myDataBase.listTaskDetailsDao().updateListTaskEstado(2, string_current_id);
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();
        refreshActivity();
    }

    @Override
    public void showErrorMessageCounter(String message) {
        Log.e(TAG+"_C", message);
        Toast.makeText(this,  getString(R.string.counter_progress_dialog_error_message), Toast.LENGTH_LONG).show();
    }

    public void refreshActivity(){
        Intent intent= new Intent(this, ListTaskActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void populateListTask(List<ListTaskDetail> listTasks) {
        fillDataFromServer(listTasks);
        fillFromDataBaseAndRefreshArray(myDataBase.listTaskDetailsDao().getListTaksDetails(2));

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
                        myDataBase.registerDao().deleteAllRegisterRows();
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
        Log.e(TAG+"_L", message);
        Toast.makeText(this, getString(R.string.list_progress_dialog_error_message), Toast.LENGTH_LONG).show();
    }

}
