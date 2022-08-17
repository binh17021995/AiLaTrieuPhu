package com.example.ailatrieuphu.view.act;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.viewmodel.MediaManager;
import com.example.ailatrieuphu.R;

public class M002NamePlayer extends AppCompatActivity {

    private EditText edtName;
    private Button btnComfirm, btnBack;
    private String name, score;
    private MediaManager media;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m002_nameplayer);
        initview();

        btnComfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = (edtName.getText().toString().trim());
                score ="0";
                hideSoftKeyBoard();
                Intent intent = new Intent(M002NamePlayer.this, M003Main.class);
                intent.putExtra("PLAYER", name);
                intent.putExtra("SCORE", score);
                startActivity(intent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MediaManager.getInstance().stopSound();
                Intent intent = new Intent(App.getInstance(), M001Intrus.class);
                startActivity(intent);
            }
        });

    }

    private void initview() {
        edtName = findViewById(R.id.edt_name);
        btnComfirm = findViewById(R.id.btn_confirm);
        btnBack = findViewById(R.id.btn_back);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }



    private void hideSoftKeyBoard() {

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }
    }






}
