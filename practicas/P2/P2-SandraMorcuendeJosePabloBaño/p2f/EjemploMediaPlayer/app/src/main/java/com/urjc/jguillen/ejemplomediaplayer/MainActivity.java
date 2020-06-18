package com.urjc.jguillen.ejemplomediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private Button buttonPlay, buttonPause, buttonStop;
    private MediaPlayer mediaPlayer;
    private boolean prepared = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = (ImageView) findViewById(R.id.imageView);
        image.setImageResource(R.drawable.turrican2);

        buttonPlay = (Button) findViewById(R.id.button_play);
        buttonPause = (Button) findViewById(R.id.button_pause);
        buttonStop = (Button) findViewById(R.id.button_stop);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.the_final_fight);

        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!prepared) {
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    prepared = true;
                }
                mediaPlayer.start();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.pause();
            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mediaPlayer.seekTo(0);
                //mediaPlayer.pause();
                mediaPlayer.stop();
                prepared = false;
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.release();
        mediaPlayer = null;
    }
}
