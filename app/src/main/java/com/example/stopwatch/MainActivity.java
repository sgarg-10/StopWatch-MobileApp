package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
     TextView textView;
    SeekBar seekbar;
    Boolean active=false;
    Button button;
    CountDownTimer countDownTimer;

    public void resetTimer(){
        textView.setText("0:30");
        seekbar.setProgress(30);
        seekbar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("Go");
        active=false;

    }


    public void timer(View view) {
        if(active){
           resetTimer();




        }
        else{
        active=true;
        seekbar.setEnabled(false);
        button.setText("STOP");

         countDownTimer = new CountDownTimer(seekbar.getProgress()*1000+100, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                update((int) millisUntilFinished/1000);

            }

            @Override
            public void onFinish() {
                MediaPlayer mp= MediaPlayer.create(getApplicationContext(),R.raw.airhorn);
                        mp.start();

                resetTimer();

            }
        }.start();
    }}
    public void update(int secleft){
        int min=secleft/60;
        int sec=secleft-(min*60);

        String st=Integer.toString(sec);
        if(sec<=9){
            st="0" + st;
        }
        textView.setText(Integer.toString(min) + ":" + st);


    }

        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


         seekbar= findViewById(R.id.seekBar);
         textView = findViewById(R.id.textView);
         button = findViewById(R.id.button);

        seekbar.setMax(600);
        seekbar.setProgress(30);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {



            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                update(progress);






            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
