package com.jallalla.jallallavotos.Splash.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.jallalla.jallallavotos.Database.MyDataBase;
import com.jallalla.jallallavotos.ListTasks.view.ListTaskActivity;
import com.jallalla.jallallavotos.Login.view.LoginActivity;
import com.jallalla.jallallavotos.R;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    Timer timer;
    private static final String TAG="[SPLASH ACTIVITY]";

    private static final String DATABASE_NAME_JALLALLA="jallallaVotosDB";
    public static MyDataBase myDataBase;

    private static final Integer timerMilliseconds=3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        myDataBase= Room.databaseBuilder(getApplicationContext(),  MyDataBase.class, DATABASE_NAME_JALLALLA).allowMainThreadQueries().build();
        runTimer();
    }

    public void runTimer() {
        TimerTask timerTask = new TimerTask() {
            public void run() {
                askForUserRegistered();
                timer.cancel();
            }
        };
        timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, timerMilliseconds, timerMilliseconds);
    }

    private void askForUserRegistered() {
        try {
            if (SplashActivity.myDataBase.militantesDao().getMilitante().getId() != null) {
                goToActivity(ListTaskActivity.class);
            }
        }catch (NullPointerException e){
            goToActivity(LoginActivity.class);
        }
    }

    private void goToActivity(Class<? extends Activity> activityClass) {
        Intent newActivity = new Intent(this, activityClass);
        startActivity(newActivity);
        finish();
    }
}
