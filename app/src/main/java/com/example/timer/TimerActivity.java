package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
     private int progress = 0;
     private ProgressBar progressBar;
     private Boolean isPaused = false;
     private Boolean isCanceled = false;
     private CountDownTimer cdTimer;
     private int secondsUntilFinished;
     private int hoursRemaining;
     private int minutesRemaining;
     private int secondsRemaining;
     private long total;
     private int totalTimePassed = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        progressBar = findViewById(R.id.remain_time_circle);
        progressBar.setProgress(0);

        //Sets time_left_text to a variable
        final TextView time_left_text = (TextView)findViewById(R.id.time_left_view);

        Intent setTimeIntent = getIntent();
        Bundle hoursMinutesTime = setTimeIntent.getExtras();
        Integer hours = hoursMinutesTime.getInt("hours");
        Integer minutes = hoursMinutesTime.getInt("minutes");
        Integer seconds = hoursMinutesTime.getInt("seconds");
        Integer totalSeconds = hours*3600+minutes*60+seconds;

        total = totalSeconds;
        startResumeCountDownTimer(totalSeconds,time_left_text);


    }
    public void pauseTimer(View v){
        isPaused= true;
        Log.d("msg","Total: "+totalTimePassed);

    }
    public void resumeTimer(View v){
        final TextView time_left_text = (TextView)findViewById(R.id.time_left_view);
        isPaused = false;
        total ++;
        startResumeCountDownTimer((int)(total),time_left_text);



    }

    public void startResumeCountDownTimer(int Seconds_Remaining,TextView view_to_update){
        cdTimer = new CountDownTimer((Seconds_Remaining )*1000,1000){
            @Override
            public void onTick(final long millisUntilFinished){
                 //total = millisUntilFinished/1000;

                 secondsUntilFinished = (int) millisUntilFinished/1000;
                 hoursRemaining = secondsUntilFinished/3600;
                 minutesRemaining = secondsUntilFinished % 3600/60;
                 secondsRemaining = secondsUntilFinished % 60;
                 total--;
                 totalTimePassed++;
                 //Log.d("msg",totalTimePassed+"");
                if(isPaused || isCanceled){
                    cancel();
                }else{
                    if(hoursRemaining == 0){
                        if(secondsRemaining < 10){
                            view_to_update.setText(minutesRemaining+":0"+secondsRemaining);

                        }else{
                            view_to_update.setText(minutesRemaining+":"+secondsRemaining);
                        }
                    }else{
                        if(secondsRemaining < 10){
                            view_to_update.setText(hoursRemaining+":"+minutesRemaining+":0"+secondsRemaining);
                        }else{
                            view_to_update.setText(hoursRemaining+":"+minutesRemaining+":"+secondsRemaining);
                        }

                    }
                    double percentage = (double) totalTimePassed/Seconds_Remaining;
                    progressBar.setProgress((int)(percentage*100));
                }
            }
            @Override
            public final void onFinish(){

                view_to_update.setText("Done");
                MediaPlayer alarm = MediaPlayer.create(TimerActivity.this,R.raw.alarm);
                alarm.start();
            }
        }.start();




    }




}