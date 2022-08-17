package com.example.ailatrieuphu.viewmodel;

import android.view.View;

import com.example.ailatrieuphu.R;

public class M00MainVM extends BaseVM{
    public void mediaPlay(){
        MediaManager.getInstance().playMediaBG(R.raw.bgmusic, true);
    }

    @Override
    public void onClick(View view) {

    }
}
