package com.example.ailatrieuphu.viewmodel;

import android.media.MediaPlayer;
import android.view.View;

import com.example.ailatrieuphu.App;

public class MediaManager extends BaseVM{
    private static MediaManager instance;
    private MediaPlayer playerBG;
    private MediaPlayer playerGame;

    private MediaManager() {
        //for singleton
    }

    public static MediaManager getInstance() {
        if (instance == null) {
            instance = new MediaManager();
        }
        return instance;
    }

    public void playGame(int songId, MediaPlayer.OnCompletionListener event) {
        playerGame = play(playerGame, songId, false);
        playerGame.setOnCompletionListener(event);
    }

    public void playMediaBG(int songId, boolean isLooping) {
        playerBG = play(playerBG, songId, isLooping);
    }

    public void pauseMediaBG() {
        pause(playerBG);
    }

    public void pauseGame() {
        pause(playerGame);
    }

    public void playContinueGame() {
        playContinue(playerGame);
    }

    public void playContinueBG() {
        playContinue(playerBG);
    }

    private void playContinue(MediaPlayer player) {
        if (player != null && !player.isPlaying()) {
            player.start();
        }
    }

    private void pause(MediaPlayer player) {
        if (player != null && player.isPlaying()) {
            player.pause();
        }
    }

    private void stop(MediaPlayer player) {
        if (player != null) {
            player.reset();
        }
    }

    private MediaPlayer play(MediaPlayer player, int songId, boolean isLooping) {
        if (player != null) {
            player.reset();
        }
        player = MediaPlayer.create(App.getInstance(), songId);
        player.setLooping(isLooping);
        player.start();
        return player;
    }

    public void pauseSound() {
        pauseMediaBG();
        pauseGame();
    }

    public void playContinueSound() {
        playContinueGame();
        playContinueBG();
    }

    public void stopSound() {
        stop(playerBG);
        stop(playerGame);
        playerBG = null;
        playerGame = null;
    }

    @Override
    public void onClick(View view) {

    }
}
