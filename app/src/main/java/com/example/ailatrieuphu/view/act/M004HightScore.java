package com.example.ailatrieuphu.view.act;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.model.User;
import com.example.ailatrieuphu.view.adapter.UserAdapter;
import com.example.ailatrieuphu.db.user.UserDatabase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M004HightScore extends AppCompatActivity {

    private Button btBack;
    private List<User> mListUser;
    private ArrayList<User> arrayList;
    private UserAdapter userAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m004_hight_score);

        initView();
        loadData();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(userAdapter);



    }

    private void initView() {
        recyclerView = findViewById(R.id.rcv_user);


    }

    private void loadData() {
        userAdapter = new UserAdapter();
        mListUser = UserDatabase.getInstance(this).userDAO().getListUser();
        sortScoreHight();
        loadTo10();

    }

    private void loadTo10() {


       if (mListUser.size()<9){
           userAdapter.setData(mListUser); ;
       }else {
           arrayList = new ArrayList<>();
           for (int i =0 ; i<9; i++){
               arrayList.add(mListUser.get(i));

           }
           userAdapter.setData(arrayList);
       }
    }

    private void deleteUser() {
        if (mListUser.isEmpty()){
            return;
        }else {
            for (int i=0; i<mListUser.size(); i++){
                if (mListUser.get(i).getScore() == "0"){
                    UserDatabase.getInstance(this).userDAO().deleteUser(mListUser.get(i));

                }
            }

            loadData();
        }
    }

    private void sortScoreHight() {
        Collections.sort(mListUser, (s1, s2) -> {
            String score1 = s1.getScore().replaceAll(",", "");
            String score2 = s2.getScore().replaceAll(",", "");
            return -(Integer.parseInt(score1) - Integer.parseInt(score2));
        });
    }


}
