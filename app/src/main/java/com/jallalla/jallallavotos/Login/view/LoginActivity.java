package com.jallalla.jallallavotos.Login.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.jallalla.jallallavotos.Database.MyDataBase;
import com.jallalla.jallallavotos.Database.entities.Militantes;
import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.Entities.Militante;
import com.jallalla.jallallavotos.ListTasks.view.ListTaskActivity;
import com.jallalla.jallallavotos.Login.model.LoginInteractorImpl;
import com.jallalla.jallallavotos.Login.presenter.LoginPresenter;
import com.jallalla.jallallavotos.Login.presenter.LoginPresenterImpl;
import com.jallalla.jallallavotos.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    LoginBody loginBody= new LoginBody();
    LoginPresenter loginPresenter;
    ProgressDialog progressDialog;
    EditText etCi, etPassword;
    private final String INCORRECT_USER="[INCORRECT_USER]";

    private static final String DATABASE_NAME_JALLALLA="jallallaVotosDB";
    public static MyDataBase myDataBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDataBase= Room.databaseBuilder(getApplicationContext(),  MyDataBase.class, DATABASE_NAME_JALLALLA).allowMainThreadQueries().build();

        loginPresenter = new LoginPresenterImpl(this, new LoginInteractorImpl());

        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.login_progress_dialog_message));
        progressDialog.setCancelable(false);

        initializeView();

    }

    public void initializeView(){
        etCi=(EditText)this.findViewById(R.id.et_ci);
        etPassword=(EditText)this.findViewById(R.id.et_password);
    }

    public void login(View v){
        if(!etCi.getText().toString().equals("")||!etPassword.getText().toString().equals("")){
            loginBody.setCi(etCi.getText().toString());
            loginBody.setPass(etPassword.getText().toString());
            loginPresenter.getMilitanteData(loginBody);
        }else{

            Toast.makeText(this, getString(R.string.login_validation), Toast.LENGTH_LONG).show();
        }
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
    public void populateMilitante(Militante militante) {
        Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_LONG).show();
        insertMilitanteValues(militante);
    }

    public void insertMilitanteValues(Militante militante){
        Militantes militantesDatabase= new Militantes();
        militantesDatabase.setId(militante.getId());
        militantesDatabase.setCi(militante.getCi());
        militantesDatabase.setNombres(militante.getNombres());
        militantesDatabase.setApellidos(militante.getApellidos());
        militantesDatabase.setUsuario(militante.getUsuario());
        myDataBase.militantesDao().insertMilitante(militantesDatabase);
        goToListTask();
    }

    public void goToListTask(){
        Intent intent= new Intent(this, ListTaskActivity.class );
        startActivity(intent);
        finish();

    }

    public void insertUserValues(){

    }

    @Override
    public void showErrorMessage(String message) {
        if(message.equals(INCORRECT_USER)){
            Toast.makeText(this, getString(R.string.login_error_user), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(this, getString(R.string.login_progress_dialog_error_message), Toast.LENGTH_LONG).show();
        }

    }
}
