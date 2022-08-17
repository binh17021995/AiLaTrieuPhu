package com.example.ailatrieuphu.view.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ailatrieuphu.App;

import com.example.ailatrieuphu.databinding.M00MainActBinding;
import com.example.ailatrieuphu.view.base.BaseAct;
import com.example.ailatrieuphu.viewmodel.M00MainVM;
import com.example.ailatrieuphu.viewmodel.MediaManager;
import com.example.ailatrieuphu.viewmodel.PlayMedia;
import com.example.ailatrieuphu.R;


public class M00MainAct extends BaseAct<M00MainActBinding, M00MainVM> implements View.OnClickListener{


    @Override
    protected Class<M00MainVM> initClassVM() {
        return M00MainVM.class;
    }

    @Override
    protected void initView() {

        viewModel.mediaPlay();
        binding.btnPlay.setOnClickListener(this);
        binding.btnHightscore.setOnClickListener(this);
        binding.btnIntrus.setOnClickListener(this);

    }

    @Override
    protected M00MainActBinding initViewBinding() {
        return M00MainActBinding.inflate(getLayoutInflater());
    }

    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.btn_play:
                Intent intent = new Intent(App.getInstance(), M001Intrus.class);
                startActivity(intent);
                break;
            case R.id.btn_hightscore:
                Intent intent2 = new Intent(App.getInstance(), M004HightScore.class);
                startActivity(intent2);
                break;
            case R.id.btn_intrus:

                break;

        }
    }




}