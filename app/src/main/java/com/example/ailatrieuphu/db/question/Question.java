package com.example.ailatrieuphu.db.question;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Question {


    @ColumnInfo (name = "_id")
    @PrimaryKey
    @NonNull
    public String id;

    @ColumnInfo(name = "question")
    public String question;

    @ColumnInfo(name = "level")
    public int level;

    @ColumnInfo(name = "casea")
    public String caseA;

    @ColumnInfo(name = "caseb")
    public String caseB;

    @ColumnInfo(name = "casec")
    public String caseC;

    @ColumnInfo(name = "cased")
    public String caseD;

    @ColumnInfo(name = "truecase")
    public String trueCase;


    public String getQuestion(){
        return question;
    }
    public String answerA(){
        return caseA;
    }
    public String answeB(){
        return caseB;
    }
    public String answeC(){
        return caseC;
    }
    public String answeD(){
        return caseD;
    }
    public String getTrueCase(){
        return trueCase;
    }
}
