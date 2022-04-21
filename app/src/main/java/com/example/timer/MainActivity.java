package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTimer(View v){

        //Establishes next activity on click
        Intent setTimeIntent = new Intent(this, TimerActivity.class);

        //Pulls user input from text views but is still in textview object form
        TextView view_hours = findViewById(R.id.hours_input);
        TextView view_minutes = findViewById(R.id.minutes_input);
        TextView view_seconds = findViewById(R.id.seconds_input);

        ArrayList<TextView> viewList = new ArrayList<>();
        viewList.add(view_hours);
        viewList.add(view_minutes);
        viewList.add(view_seconds);


        //Converts Text View object values in Integer values
        Integer hours = Integer.parseInt(view_hours.getText().toString());
        Integer minutes = Integer.parseInt(view_minutes.getText().toString());
        Integer seconds = Integer.parseInt(view_seconds.getText().toString());

//        try{
//            Integer hours = Integer.parseInt(view_hours.getText().toString());
//        }catch(Exception e){
//            Integer hours = 0;
//        }





//        ArrayList<Integer> timeTypesList = new ArrayList();
//        timeTypesList.add(hours);
//        timeTypesList.add(minutes);
//        timeTypesList.add(seconds);
//
//        for(int i = 0; i < timeTypesList.size();i ++){
//            if(timeTypesList.get(i) == null){
//                timeTypesList.set(i,0);
//            }
//        }




        //Creates a bundle for the extras to pass to the new activity
        Bundle hoursMinutesTime = new Bundle();
        hoursMinutesTime.putInt("hours",hours);
        hoursMinutesTime.putInt("minutes",minutes);
        hoursMinutesTime.putInt("seconds",seconds);

        //Passes the bundle and starts new activity
        setTimeIntent.putExtras(hoursMinutesTime);
        startActivity(setTimeIntent);

    }
}