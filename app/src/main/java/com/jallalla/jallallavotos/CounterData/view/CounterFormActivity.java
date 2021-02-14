package com.jallalla.jallallavotos.CounterData.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jallalla.jallallavotos.CounterData.model.CounterInteractorImpl;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenter;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenterImpl;
import com.jallalla.jallallavotos.Entities.CounterData;
import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.Entities.LoginBody;
import com.jallalla.jallallavotos.ListTasks.view.ListTaskActivity;
import com.jallalla.jallallavotos.Login.model.LoginInteractorImpl;
import com.jallalla.jallallavotos.Login.presenter.LoginPresenter;
import com.jallalla.jallallavotos.Login.presenter.LoginPresenterImpl;
import com.jallalla.jallallavotos.Login.view.LoginActivity;
import com.jallalla.jallallavotos.R;
import com.jallalla.jallallavotos.Utils.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CounterFormActivity extends AppCompatActivity implements CounterView {

    CounterDataBody counterDataBody = new CounterDataBody();
    CounterPresenter counterPresenter;
    ProgressDialog progressDialog;
    Bundle bundle;

    Integer id_colegio, id_mesa, id_militante;
    String codigo_distrito, nombre_unidad, sigla;

    TextView lbl_info;
    EditText et_jallalla_alcalde, et_jallalla_concejal, et_mas_alcalde, et_mas_concejal, et_comments;
    ImageView btn_camera;

    private Calendar fechaYhora = Calendar.getInstance();
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    ListTaskActivity listTaskActivity = new ListTaskActivity();

    private final static int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    Uri filePath;
    File file;
    final int RC_TAKE_PHOTO = 2;
    public static final int GET_IMAGE = 3;
    public static String path;
    String base64image;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_form);

        bundle = getIntent().getExtras();
        id_colegio = bundle.getInt("codigo_colegio");
        codigo_distrito = bundle.getString("codigo_distrito");
        id_mesa = bundle.getInt("id_mesa");
        nombre_unidad = bundle.getString("nombre_unidad");
        id_militante = bundle.getInt("id_militante");

        counterPresenter = new CounterPresenterImpl(this, new CounterInteractorImpl());

        progressDialog = new ProgressDialog(CounterFormActivity.this);
        progressDialog.setMessage(getResources().getString(R.string.counter_progress_dialog_message));
        progressDialog.setCancelable(false);

        initializeView();
        setViewValues();

        checkPermission();

    }

    public void goBack(View v) {
        finish();
    }

    //inicializar los valores de la vista
    private void initializeView() {
        lbl_info = (TextView) findViewById(R.id.lbl_info);
        et_jallalla_alcalde = (EditText) findViewById(R.id.et_alcalde_jallalla);
        et_jallalla_concejal = (EditText) findViewById(R.id.et_concejal_jallalla);
        et_mas_alcalde = (EditText) findViewById(R.id.et_alcalde_mas);
        et_mas_concejal = (EditText) findViewById(R.id.et_concejal_mas);
        et_comments = (EditText) findViewById(R.id.et_comments);
        btn_camera = (ImageView) findViewById(R.id.img_picture);
    }

    //asignar valores a la lista
    public void setViewValues() {
        lbl_info.setText(getString(R.string.item_colegio) + " " + nombre_unidad + " " + getString(R.string.item_mesa) + " " + id_mesa);
    }

    //Verificar si existem los permisos de la cámara y el storage
    private void checkPermission() {
        int permissionCheck = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) { //No tiene el permiso
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        PERMISSIONS,
                        MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);
            }
        }
    }

    //Una vez que aceptan o no los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        String mensaje = "";
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            mensaje = "Permiso Concedido";
        } else {
            mensaje = "Permiso no concedido";
        }
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();

    }


    public void saveCounter(View v) {
        if (!et_jallalla_alcalde.getText().toString().equals("") || !et_jallalla_concejal.getText().toString().equals("")) {
            prepareArray();
            counterPresenter.insertCounterData(counterDataBody);
        } else {

            Toast.makeText(this, getString(R.string.counter_error_empty_data), Toast.LENGTH_LONG).show();
        }
    }

    //alistar los datos a ser guardados
    public void prepareArray() {

        ArrayList<CounterData> counterData = new ArrayList<CounterData>();

        CounterData counterData1 = new CounterData();
        counterData1.setFechaAlta(fecha.format(fechaYhora.getTime()));
        counterData1.setIdMesa(String.valueOf(id_mesa));
        counterData1.setIdMilitante(String.valueOf(id_militante));
        counterData1.setSigla("JALLALLA");
        counterData1.setIdPartidoPolitico("1");
        counterData1.setVotosAlcalde(et_jallalla_alcalde.getText().toString());
        counterData1.setVotosConcejal(et_jallalla_concejal.getText().toString());


        CounterData counterData2 = new CounterData();
        counterData2.setFechaAlta(fecha.format(fechaYhora.getTime()));
        counterData2.setIdMesa(String.valueOf(id_mesa));
        counterData2.setIdMilitante(String.valueOf(id_militante));
        counterData2.setSigla("MAS");
        counterData2.setIdPartidoPolitico("2");
        counterData2.setVotosAlcalde(et_mas_alcalde.getText().toString());
        counterData2.setVotosConcejal(et_mas_alcalde.getText().toString());

        counterData.add(counterData1);
        counterData.add(counterData2);

        counterDataBody.setObservaciones(et_comments.getText().toString());
        counterDataBody.setDatos(counterData);
        counterDataBody.setFoto(("data:image/jpeg;base64," + base64image).replaceAll("\n", ""));
        Log.e("json", new Gson().toJson(counterDataBody).toString());
    }


    private void takePhoto() {
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        path = getFilename();
        file = new File(path);
        filePath = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, filePath);
        startActivityForResult(intent, RC_TAKE_PHOTO);

    }

    private void showSelectFileIntent() {

        Intent intent = new Intent();
        intent.setType("image/jpeg");
        intent.setAction(Intent.ACTION_GET_CONTENT);

        try {
            startActivityForResult(Intent.createChooser(intent, "Select image"), GET_IMAGE);

        } catch (ActivityNotFoundException e) {
            Toast.makeText(getBaseContext(), "Error importado imagen", Toast.LENGTH_SHORT).show();
            Log.e("Error", "Error uploading pdf file");
        }
    }

    public String getFilename() {
        File file = new File(Environment.getExternalStorageDirectory().getPath(), "jallallaVotos/images");
        if (!file.exists()) {
            file.mkdirs();
        }
        return (file.getAbsolutePath() + "/" + id_militante + "_" + id_mesa + "_" + id_colegio + "_" + System.currentTimeMillis() + ".jpg");
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
    public void populateResponse(String successMessage) {
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();
        refreshList();
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void refreshList(){
        Intent intent= new Intent(this, ListTaskActivity.class);
        listTaskActivity.listTaskActivityClass.finish();
        finish();
        startActivity(intent);
    }

    public void imageOptionsShow(View V) {
        final AlertDialog.Builder alertDialogPicture = new AlertDialog.Builder(
                CounterFormActivity.this);

        alertDialogPicture.setTitle(getString(R.string.counter_dialog_captura_title));
        alertDialogPicture.setMessage(getString(R.string.counter_dialog_captura_message));
        alertDialogPicture.setCancelable(true);
        alertDialogPicture.setPositiveButton(getString(R.string.counter_dialog_captura_option_fotografia),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        takePhoto();
                    }
                });
        alertDialogPicture.setNegativeButton(getString(R.string.counter_dialog_captura_option_file),
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        showSelectFileIntent();
                    }
                });
        alertDialogPicture.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GET_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            path = FileUtils.getPath(this, selectedImageUri);
            btn_camera.setImageURI(selectedImageUri);

        } else if (requestCode == RC_TAKE_PHOTO && resultCode == RESULT_OK) {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                btn_camera.setImageBitmap(myBitmap);
            }
        }
        base64image = getBase64FromPath(path);
    }

    public static String getBase64FromPath(String path) {
        String base64 = "";
        try {/*from   w w w .  ja  va  2s  .  c om*/
            File file = new File(path);
            byte[] buffer = new byte[(int) file.length() + 100];
            @SuppressWarnings("resource")
            int length = new FileInputStream(file).read(buffer);
            base64 = Base64.encodeToString(buffer, 0, length,
                    Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64;
    }
}

