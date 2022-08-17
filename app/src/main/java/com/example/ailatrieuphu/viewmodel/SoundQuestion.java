package com.example.ailatrieuphu.viewmodel;


import com.example.ailatrieuphu.viewmodel.PlayMedia;
import com.example.ailatrieuphu.R;

import java.util.ArrayList;

public class SoundQuestion {
    private PlayMedia playMedia;
    private ArrayList<Integer> listSoundQuestion;


    public SoundQuestion(){
        listSoundQuestion = new ArrayList<Integer>();
        listSoundQuestion.add(R.raw.ques1);
        listSoundQuestion.add(R.raw.ques2);
        listSoundQuestion.add(R.raw.ques3);
        listSoundQuestion.add(R.raw.ques4);
        listSoundQuestion.add(R.raw.ques5);
        listSoundQuestion.add(R.raw.ques6);
        listSoundQuestion.add(R.raw.ques7);
        listSoundQuestion.add(R.raw.ques8);
        listSoundQuestion.add(R.raw.ques9);
        listSoundQuestion.add(R.raw.ques10);
        listSoundQuestion.add(R.raw.ques11);
        listSoundQuestion.add(R.raw.ques12);
        listSoundQuestion.add(R.raw.ques13);
        listSoundQuestion.add(R.raw.ques14);
        listSoundQuestion.add(R.raw.ques15);
    }

    public ArrayList<Integer> getListSoundQuestion() {
        return listSoundQuestion;
    }

    public void setMoneyList(ArrayList<Integer> listSoundQuestion) {
        this.listSoundQuestion = listSoundQuestion;
    }
}
