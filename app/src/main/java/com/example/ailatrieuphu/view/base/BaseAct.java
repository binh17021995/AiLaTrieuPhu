package com.example.ailatrieuphu.view.base;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.ailatrieuphu.viewmodel.BaseVM;

public abstract class BaseAct< T extends ViewBinding, M extends BaseVM> extends AppCompatActivity implements View.OnClickListener {
    protected T binding;
    protected M viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = initViewBinding();
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(initClassVM());
        initView();
    }

    protected abstract Class<M> initClassVM();

    protected abstract void initView();

    protected abstract T initViewBinding();

    @Override
    public void onClick(View view) {
        view.startAnimation(AnimationUtils.loadAnimation(this, androidx.appcompat.R.anim.abc_fade_in));
        clickView(view);

    }

    protected void clickView(View view) {
    }
}
