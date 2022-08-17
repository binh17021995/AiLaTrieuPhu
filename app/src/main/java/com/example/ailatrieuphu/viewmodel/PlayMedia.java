package com.example.ailatrieuphu.viewmodel;

import android.content.Context;
import android.media.MediaPlayer;

public class PlayMedia {
    private MediaPlayer mediaPlayer;

    public PlayMedia() {
    }

    public void playMedia(Context context, int song){
        if(mediaPlayer != null){
            mediaPlayer.reset();


        }

        mediaPlayer = MediaPlayer.create(context, song);
        mediaPlayer.setLooping(false);
        mediaPlayer.start();
    }

    public void stopMedia(){
        mediaPlayer.stop();
        mediaPlayer.reset();
    }
}
