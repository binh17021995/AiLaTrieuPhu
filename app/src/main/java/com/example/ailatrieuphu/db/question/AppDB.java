package com.example.ailatrieuphu.db.question;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Question.class}, version = 1)
public abstract class AppDB extends RoomDatabase {

    public abstract QuestionDAO getQuestionDAO();

}
