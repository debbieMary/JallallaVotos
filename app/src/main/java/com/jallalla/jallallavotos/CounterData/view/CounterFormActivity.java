package com.jallalla.jallallavotos.CounterData.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.jallalla.jallallavotos.CounterData.model.CounterInteractorImpl;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenter;
import com.jallalla.jallallavotos.CounterData.presenter.CounterPresenterImpl;
import com.jallalla.jallallavotos.Database.MyDataBase;
import com.jallalla.jallallavotos.Database.entities.Register;
import com.jallalla.jallallavotos.Entities.CounterData;
import com.jallalla.jallallavotos.Entities.CounterDataBody;
import com.jallalla.jallallavotos.ListTasks.view.ListTaskActivity;
import com.jallalla.jallallavotos.R;
import com.jallalla.jallallavotos.Utils.FileUtils;
import com.jallalla.jallallavotos.Utils.GeneralUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CounterFormActivity extends AppCompatActivity implements CounterView {

    GeneralUtils generalUtils = new GeneralUtils();

    CounterDataBody counterDataBody = new CounterDataBody();
    CounterPresenter counterPresenter;
    ProgressDialog progressDialog;
    Bundle bundle;

    FrameLayout ly_image;

    Integer id_colegio, id_mesa, id_militante, numero_de_mesa;
    String codigo_distrito, nombre_unidad, id_listado_pendiente;

    TextView lbl_info;
    EditText et_jallalla_alcalde, et_jallalla_concejal, et_mas_alcalde, et_mas_concejal, et_comments;
    ImageView btn_camera, img_zoom;

    private Calendar fechaYhora = Calendar.getInstance();
    SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    ListTaskActivity listTaskActivity = new ListTaskActivity();

    private static final String DATABASE_NAME_JALLALLA = "jallallaVotosDB";
    public static MyDataBase myDataBase;

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
    public static String path = "";
    String base64image;

    public static final String TAG = "[COUNTER_ACTIVITY]";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_counter_form);

        myDataBase = Room.databaseBuilder(getApplicationContext(), MyDataBase.class, DATABASE_NAME_JALLALLA).allowMainThreadQueries().build();

        bundle = getIntent().getExtras();
        id_colegio = bundle.getInt("codigo_colegio");
        codigo_distrito = bundle.getString("codigo_distrito");
        id_mesa = bundle.getInt("id_mesa");
        nombre_unidad = bundle.getString("nombre_unidad");
        id_militante = bundle.getInt("id_militante");
        id_listado_pendiente = bundle.getString("id_listado_pendiente");
        numero_de_mesa = bundle.getInt("numero_de_mesa");

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
        ly_image= (FrameLayout) findViewById(R.id.ly_image);
        img_zoom = (ImageView) findViewById(R.id.img_zoom);
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
        lbl_info.setText(getString(R.string.item_colegio) + " " + nombre_unidad + " " + getString(R.string.item_mesa) + " " + numero_de_mesa);
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
        if (!et_jallalla_alcalde.getText().toString().equals("")) {
            if (!et_jallalla_concejal.getText().toString().equals("")) {
                if (!path.equals("")) {
                    prepareArrayAndSaveToLocal();
                    counterPresenter.insertCounterData(counterDataBody);
                } else {
                    Toast.makeText(this, getString(R.string.counter_error_empty_data), Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(this, getString(R.string.counter_error_empty_data), Toast.LENGTH_LONG).show();
            }

        } else {
            Toast.makeText(this, getString(R.string.counter_error_empty_data), Toast.LENGTH_LONG).show();
        }
    }

    //alistar los datos a ser guardados
    public void prepareArrayAndSaveToLocal() {

        Register newRegister = new Register();
        newRegister.setId_register(id_listado_pendiente);
        newRegister.setFecha_alta(fecha.format(fechaYhora.getTime()));
        newRegister.setFoto(path);
        newRegister.setId_mesa(String.valueOf(id_mesa));
        newRegister.setObservaciones(et_comments.getText().toString());
        newRegister.setVotos_jallalla_alcalde(et_jallalla_alcalde.getText().toString());
        newRegister.setVotos_jallalla_concejal(et_jallalla_concejal.getText().toString());
        newRegister.setVotos_mas_alcalde(et_mas_alcalde.getText().toString());
        newRegister.setVotos_mas_concejal(et_mas_concejal.getText().toString());
        myDataBase.registerDao().insertRegister(newRegister);
        myDataBase.listTaskDetailsDao().updateListTaskEstado(1, id_listado_pendiente);

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
        counterData2.setVotosConcejal(et_mas_concejal.getText().toString());

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
    public void showProgressCounter() {
        progressDialog.show();
    }

    @Override
    public void hideProgressCounter() {
        progressDialog.hide();
    }

    @Override
    public void populateResponse(String successMessage) {
        Toast.makeText(this, successMessage, Toast.LENGTH_LONG).show();
        myDataBase.listTaskDetailsDao().updateListTaskEstado(2, id_listado_pendiente);
        refreshList();
    }

    @Override
    public void showErrorMessageCounter(String message) {
        Log.e(TAG, message);
        Toast.makeText(this, getString(R.string.counter_progress_dialog_error_message), Toast.LENGTH_LONG).show();
        refreshList();
    }

    public void refreshList() {
        Intent intent = new Intent(this, ListTaskActivity.class);
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
        boolean isLanscape = true;
        if (requestCode == GET_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri selectedImageUri = data.getData();
            path = FileUtils.getPath(this, selectedImageUri);
            btn_camera.setImageURI(selectedImageUri);
            isLanscape= false;

        } else if (requestCode == RC_TAKE_PHOTO && resultCode == RESULT_OK) {
            File imgFile = new File(path);
            if (imgFile.exists()) {
                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                btn_camera.setImageBitmap(myBitmap);
               isLanscape=true;
            }
        }
        img_zoom.setVisibility(View.VISIBLE);
        base64image = generalUtils.getBase64FromPath(path);
        setImageViewSize(isLanscape);
    }

    public void setImageViewSize(boolean isLandscape){
        int frameLayoutWidth=ly_image.getMeasuredWidth();
        int frameLayoutHeight=ly_image.getMeasuredHeight();
        if (isLandscape){
            btn_camera.getLayoutParams().height = (int) (frameLayoutHeight *0.80);
            btn_camera.getLayoutParams().width = (int) (frameLayoutWidth *0.80);
        }else{
            btn_camera.getLayoutParams().height = (int) (frameLayoutWidth *0.80);
            btn_camera.getLayoutParams().width = (int) (frameLayoutHeight *0.80);
        }
    }


    public void showDialogImage(View view) {
        final Dialog dialog = new Dialog(this, R.style.Theme_Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.activity_zoom);
        int width = (int) (generalUtils.get_width(this) * 0.90);
        int height = (int) (generalUtils.get_height(this) * 0.90);
        dialog.getWindow().setLayout(width, height);

        ImageView dialogBtn_close = (ImageView) dialog.findViewById(R.id.img_close);
        dialogBtn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        Bitmap myBitmap = BitmapFactory.decodeFile(path);
        ImageView myImage = (ImageView) dialog.findViewById(R.id.img_picture_complete_123);
        myImage.setImageBitmap(myBitmap);
           /* Button dialogBtn_okay = (Button) dialog.findViewById(R.id.btn_okay);
            dialogBtn_okay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(getApplicationContext(),"Okay" ,Toast.LENGTH_SHORT).show();
                    dialog.cancel();
                }
            });*/

        dialog.show();
    }

}

