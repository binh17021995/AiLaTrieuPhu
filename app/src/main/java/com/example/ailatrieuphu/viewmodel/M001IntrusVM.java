package com.example.ailatrieuphu.viewmodel;

import android.media.MediaPlayer;
import android.view.View;

import com.example.ailatrieuphu.R;

public class M001IntrusVM extends BaseVM{

    public void media2(){
        MediaManager.getInstance().playGame(R.raw.song_rule, new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                MediaManager.getInstance().playGame(R.raw.song_ready, new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer1) {
                        showDialogReady();
                    }
                });
            }
        });
    }

    protected void showDialogReady() {
    }


    @Override
    public void onClick(View view) {

    }
}
