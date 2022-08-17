package com.example.ailatrieuphu.view.act;


import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.databinding.M001IntrusBinding;
import com.example.ailatrieuphu.view.base.BaseAct;
import com.example.ailatrieuphu.viewmodel.M001IntrusVM;
import com.example.ailatrieuphu.viewmodel.MediaManager;
import com.example.ailatrieuphu.viewmodel.PlayMedia;
import com.example.ailatrieuphu.R;

public class M001Intrus extends BaseAct<M001IntrusBinding, M001IntrusVM> {
    private PlayMedia mediaPlay;

    @Override
    protected Class<M001IntrusVM> initClassVM() {
        return M001IntrusVM.class;
    }

    @Override
    protected void initView() {
        viewModel.media2();

        binding.btnHide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().pauseGame();
                Intent intent = new Intent(App.getInstance(), M002NamePlayer.class);
                startActivity(intent);
            }
        });

        binding.btnBackhome1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(App.getInstance(), M00MainAct.class);
                startActivity(intent);
            }
        });

    }
    private void showDialogReady(){
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.m001_dialog_ready);
        Button btnOk = dialog.findViewById(R.id.bt_ok);
        Button btnNo = dialog.findViewById(R.id.bt_no);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().pauseGame();
                Intent intent = new Intent(App.getInstance(), M002NamePlayer.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaManager.getInstance().pauseGame();
                Intent intent = new Intent(App.getInstance(), M001Intrus.class);
                startActivity(intent);
            }
        });

        dialog.show();

    }

    @Override
    protected M001IntrusBinding initViewBinding() {
        return M001IntrusBinding.inflate(getLayoutInflater());
    }

    @Override
    protected void onStart() {
        super.onStart();
        MediaManager.getInstance().playContinueSound();
    }

    @Override
    protected void onStop() {
        super.onStop();
        MediaManager.getInstance().pauseSound();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MediaManager.getInstance().stopSound();
    }

    @Override
    public void onBackPressed() {

    }



}
