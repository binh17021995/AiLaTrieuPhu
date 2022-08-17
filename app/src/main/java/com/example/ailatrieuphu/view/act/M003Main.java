package com.example.ailatrieuphu.view.act;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ailatrieuphu.App;
import com.example.ailatrieuphu.viewmodel.MediaManager;
import com.example.ailatrieuphu.viewmodel.PlayMedia;
import com.example.ailatrieuphu.R;
import com.example.ailatrieuphu.db.money.ListMoney;
import com.example.ailatrieuphu.db.question.Question;
import com.example.ailatrieuphu.model.User;
import com.example.ailatrieuphu.db.user.UserDatabase;
import com.example.ailatrieuphu.viewmodel.SoundQuestion;
import com.skydoves.progressview.ProgressView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class M003Main extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "List question";
    private CountDownTimer cTimer1 = null;
    private final SQLiteDatabase db = null;
    private List<Question> listDB;
    private Button btnCaseA, btnCaseB, btnCaseC, btnCaseD;
    private Button btnCall, btn5050, btnAudience, btnStop;
    private TextView name, money, tvTime;
    private TextView question, questionNumber;
    private int currentQuestion;
    private ListMoney listMoney;
    private User user;
    private SoundQuestion listSound;
    private PlayMedia mediaPlay = new PlayMedia();
    private Long time;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.m003_main);
        mediaPlay.playMedia(this, R.raw.background_music);
        mediaPlay.playMedia(App.getInstance(),R.raw.ques1);
        intUI();
        startTime();
        loadData();

        Intent intent = getIntent();
        String namePlayer = intent.getStringExtra("PLAYER");
        String score = intent.getStringExtra("SCORE");
        name.setText(namePlayer);
        money.setText(score);


        btnCaseA.setOnClickListener(this);
        btnCaseB.setOnClickListener(this);
        btnCaseC.setOnClickListener(this);
        btnCaseD.setOnClickListener(this);
        btn5050.setOnClickListener(this);
        btnAudience.setOnClickListener(this);
        btnCall.setOnClickListener(this);
        btnStop.setOnClickListener(this);


    }

    private void loadData() {
        new Thread(() -> {
            List<Question> listQuestion = App.getInstance().getDb().getQuestionDAO().getAllQuestion();
            Collections.sort(listQuestion, (q1, q2) -> q1.level - q2.level);

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listDB = listQuestion;
                    setDataQuestions(listQuestion.get(currentQuestion));

                }
            });

        }).start();


    }

    private void setDataQuestions(Question questions) {

        if (questions == null) {
            return;
        }

        resetColorButton();

        String cauHoi = String.valueOf(questions.getQuestion());
        question.setText(cauHoi);
        questionNumber.setText("Question: " + (currentQuestion + 1));
        btnCaseA.setText("A. " + questions.caseA);
        btnCaseB.setText("B. " + questions.caseB);
        btnCaseC.setText("C. " + questions.caseC);
        btnCaseD.setText("D. " + questions.caseD);

    }

    private void resetColorButton() {

        btnCaseA.setBackgroundResource(R.drawable.player_answer_background_normal);
        btnCaseB.setBackgroundResource(R.drawable.player_answer_background_normal);
        btnCaseC.setBackgroundResource(R.drawable.player_answer_background_normal);
        btnCaseD.setBackgroundResource(R.drawable.player_answer_background_normal);
    }


    private void intUI() {
        name = findViewById(R.id.tv_player);
        money = findViewById(R.id.tv_money);
        btnCall = findViewById(R.id.btn_call_help);
        btn5050 = findViewById(R.id.btn_50_50);
        btnAudience = findViewById(R.id.btn_audience);
        questionNumber = findViewById(R.id.tv_questionnumber);
        question = findViewById(R.id.tv_question);
        btnCaseA = findViewById(R.id.btn_caseA);
        btnCaseB = findViewById(R.id.btn_caseB);
        btnCaseC = findViewById(R.id.btn_caseC);
        btnCaseD = findViewById(R.id.btn_caseD);
        tvTime = findViewById(R.id.tv_time);
        btnStop = findViewById(R.id.btn_stop);



    }

    private void continueTime(){

        cTimer1 = new CountDownTimer(time,1000) {
            @Override
            public void onTick(long l) {
                time = (l / 1000);
                tvTime.setText("Time: " + time);
            }

            @Override
            public void onFinish() {

            }
        }.start();

    }

    private void resetTime() {
        tvTime.setText("Time");
        cTimer1.cancel();
    }

    private void startTime() {
        cTimer1 = new CountDownTimer(30000, 1000) {
            @Override


            public void onTick(long l) {
                time = (l / 1000);
                tvTime.setText("Time: " + time);

                if(time==8){
                    mediaPlay.playMedia(App.getInstance(),R.raw.tictac);
                }


            }

            @Override
            public void onFinish() {
                btnCaseA.setEnabled(false);
                btnCaseB.setEnabled(false);
                btnCaseC.setEnabled(false);
                btnCaseD.setEnabled(false);
                mediaPlay.playMedia(App.getInstance(),R.raw.tictac);


                        mediaPlay.playMedia(App.getInstance(),R.raw.out_of_time);
                        resetTime();
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                showAnswerCorrect(listDB.get(currentQuestion));
                                gameOver();
                            }
                        }, 2000);


            }
        }.start();
    }


    private void checkAnswer(Button btnAnswer, Question question1, String answer) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (question1.trueCase.equals(answer)) {
                    if(answer =="1"){
                        mediaPlay.stopMedia();
                        mediaPlay.playMedia(App.getInstance(),R.raw.true_a);
                    }

                    if(answer =="2"){
                        mediaPlay.stopMedia();
                        mediaPlay.playMedia(App.getInstance(),R.raw.true_b);
                    }

                    if(answer =="3"){
                        mediaPlay.stopMedia();
                        mediaPlay.playMedia(App.getInstance(),R.raw.true_c);
                    }
                    if(answer =="4"){
                        mediaPlay.stopMedia();
                        mediaPlay.playMedia(App.getInstance(),R.raw.true_d2);
                    }
                    btnAnswer.setBackgroundResource(R.drawable.player_answer_background_true);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            nextQuestion();

                        }
                    },4000);


                } else {
                    btnAnswer.setBackgroundResource(R.drawable.player_answer_background_wrong);
                    addUser();
                    showAnswerCorrect(question1);
                }
            }
        }, 5000);


    }

    private void nextQuestion() {

        if (currentQuestion == listDB.size() - 1) {
            showDialog("You Win");
        } else {
            mediaPlay.playMedia(this, R.raw.background_music);
            listSound = new SoundQuestion();
            mediaPlay.playMedia(App.getInstance(),listSound.getListSoundQuestion().get(currentQuestion+1));
            listMoney = new ListMoney();
            String vnd = listMoney.getMoneyList().get(currentQuestion);
            money.setText(vnd);

            currentQuestion++;

            if(currentQuestion ==5){
                mediaPlay.playMedia(App.getInstance(),R.raw.chuc_mung_vuot_moc_01_0);
          }
            if(currentQuestion ==10){
                mediaPlay.playMedia(App.getInstance(),R.raw.chuc_mung_vuot_moc_02_0);
            }
            resetTime();
            startTime();
            setDataQuestions(listDB.get(currentQuestion));
        }

    }

    private void showAnswerCorrect(Question ques) {
        if (ques == null || ques.trueCase == null || ques.trueCase.isEmpty()) {
            return;
        }
        if (ques.trueCase.equals("1")) {

            mediaPlay.stopMedia();
            mediaPlay.playMedia(App.getInstance(), R.raw.lose_a);
            btnCaseA.setBackgroundResource(R.drawable.player_answer_background_true);

        } else if (ques.trueCase.equals("2")) {

            mediaPlay.stopMedia();
            mediaPlay.playMedia(App.getInstance(), R.raw.lose_b);
            btnCaseB.setBackgroundResource(R.drawable.player_answer_background_true);

        } else if (ques.trueCase.equals("3")) {

            mediaPlay.stopMedia();
            mediaPlay.playMedia(App.getInstance(), R.raw.lose_c);
            btnCaseC.setBackgroundResource(R.drawable.player_answer_background_true);

        } else if (ques.trueCase.equals("4")) {

            mediaPlay.stopMedia();
            mediaPlay.playMedia(App.getInstance(), R.raw.lose_d);
            btnCaseD.setBackgroundResource(R.drawable.player_answer_background_true);

        }

        gameOver();


    }

    private void gameOver() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showDialog("Game Over");

                mediaPlay.playMedia(App.getInstance(), R.raw.lose2);

            }
        }, 3000);




    }

    private void showDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                currentQuestion = 0;
                dialogInterface.dismiss();
                mediaPlay.stopMedia();
                Intent intent = new Intent(M003Main.this, M00MainAct.class);
                startActivity(intent);
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void addUser() {

        String strUsername = (String) name.getText();
        String diem = (String) money.getText();

        if (TextUtils.isEmpty(strUsername)) {
            return;
        }
        user = new User(strUsername, diem);
        UserDatabase.getInstance(this).userDAO().insertUser(user);

        if (isUserExits(user)) {
            return;
        }

        ArrayList<User> userName = new ArrayList<>();
        userName.add(user);


    }

    private boolean isUserExits(User user) {
        List<User> list = UserDatabase.getInstance(this).userDAO().checkUser(user.getUsername());
        return list != null && !list.isEmpty();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_caseA:
                btnCaseA.setBackgroundResource(R.drawable.player_answer_background_selected);
                cTimer1.cancel();
                mediaPlay.stopMedia();
                mediaPlay.playMedia(App.getInstance(), R.raw.ans_a);
                checkAnswer(btnCaseA, listDB.get(currentQuestion), "1");

                break;
            case R.id.btn_caseB:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                mediaPlay.playMedia(App.getInstance(), R.raw.ans_b);
                btnCaseB.setBackgroundResource(R.drawable.player_answer_background_selected);
                checkAnswer(btnCaseB, listDB.get(currentQuestion), "2");

                break;
            case R.id.btn_caseC:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                mediaPlay.playMedia(App.getInstance(), R.raw.ans_c);

                btnCaseC.setBackgroundResource(R.drawable.player_answer_background_selected);
                checkAnswer(btnCaseC, listDB.get(currentQuestion), "3");

                break;
            case R.id.btn_caseD:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                mediaPlay.playMedia(App.getInstance(), R.raw.ans_d);
                btnCaseD.setBackgroundResource(R.drawable.player_answer_background_selected);
                checkAnswer(btnCaseD, listDB.get(currentQuestion), "4");
                break;

            case R.id.btn_50_50:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                showHelp5050(listDB.get(currentQuestion));
                btn5050.setBackgroundResource(R.drawable.player_button_image_help_5050_x);
                btn5050.setEnabled(false);
                break;

            case R.id.btn_call_help:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                showHelpCall(listDB.get(currentQuestion));
                btnCall.setBackgroundResource(R.drawable.player_button_image_help_call_x);
                btnCall.setEnabled(false);
                break;

            case R.id.btn_audience:
                cTimer1.cancel();
                mediaPlay.stopMedia();
                showHelpAudience(listDB.get(currentQuestion));
                btnAudience.setBackgroundResource(R.drawable.player_button_image_help_audience_x);
                btnAudience.setEnabled(false);
                break;
            case R.id.btn_stop:
                showStop();

                break;
        }

    }

    private void showStop() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.m003_dialog_stop);

        dialog.setCancelable(false);
        Button btnOk = dialog.findViewById(R.id.btn_stopgame);
        Button btnNo = dialog.findViewById(R.id.btn_continues);

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlay.stopMedia();
                addUser();
                Intent intent = new Intent(App.getInstance(), M00MainAct.class);
                startActivity(intent);
            }
        });

        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    private void showHelpAudience(Question questionAudience) {
       mediaPlay.playMedia(App.getInstance(),R.raw.khan_gia);
       new Handler().postDelayed(() -> {
           int tyLeA, tyLeB, tyLeC, tyLeD;

           if (questionAudience.trueCase.equals("1")) {

               if (questionAudience.caseB.equals("B")) {
                   tyLeB = 0;
                   if (questionAudience.caseC.equals("C")) {
                       tyLeC = 0;
                       tyLeA = 80;
                       tyLeD = 20;
                   } else {
                       tyLeC = 10;
                       tyLeA = 90;
                       tyLeD = 0;
                   }
               } else {
                   tyLeB = 15;
                   tyLeA = 85;
                   tyLeC = 0;
                   tyLeD = 0;
               }
               showAudiencen(tyLeA, tyLeB, tyLeC, tyLeD);


           }

           if (questionAudience.trueCase.equals("2")) {
               if (questionAudience.caseB.equals("A")) {
                   tyLeA = 0;
                   if (questionAudience.caseC.equals("C")) {
                       tyLeC = 0;
                       tyLeB = 70;
                       tyLeD = 30;
                   } else {
                       tyLeC = 5;
                       tyLeB = 95;
                       tyLeD = 0;
                   }
               } else {
                   tyLeB = 75;
                   tyLeA = 25;
                   tyLeC = 0;
                   tyLeD = 0;
               }
               showAudiencen(tyLeA, tyLeB, tyLeC, tyLeD);

           }

           if (questionAudience.trueCase.equals("3")) {
               if (questionAudience.caseB.equals("B")) {
                   tyLeB = 0;
                   if (questionAudience.caseC.equals("A")) {
                       tyLeC = 80;
                       tyLeA = 0;
                       tyLeD = 20;
                   } else {
                       tyLeC = 87;
                       tyLeA = 13;
                       tyLeD = 0;
                   }
               } else {
                   tyLeB = 18;
                   tyLeA = 0;
                   tyLeC = 82;
                   tyLeD = 0;
               }
               showAudiencen(tyLeA, tyLeB, tyLeC, tyLeD);

           }

           if (questionAudience.trueCase.equals("4")) {

               if (questionAudience.caseB.equals("B")) {
                   tyLeB = 0;
                   if (questionAudience.caseC.equals("C")) {
                       tyLeC = 0;
                       tyLeA = 10;
                       tyLeD = 90;
                   } else {
                       tyLeC = 22;
                       tyLeA = 0;
                       tyLeD = 78;
                   }
               } else {
                   tyLeB = 20;
                   tyLeA = 0;
                   tyLeC = 0;
                   tyLeD = 80;
               }
               showAudiencen(tyLeA, tyLeB, tyLeC, tyLeD);

           }

       },7000);

    }

    private void showAudiencen(int chooseA, int chooseB, int chooseC, int chooseD) {

        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_custom_dialog_audience);

        Window window = dialog.getWindow();
        if (window == null) {
            return;
        }

        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        dialog.setCancelable(false);

        TextView tvProgress1 = dialog.findViewById(R.id.tv_audience_1);
        TextView tvProgress2 = dialog.findViewById(R.id.tv_audience_2);
        TextView tvProgress3 = dialog.findViewById(R.id.tv_audience_3);
        TextView tvProgress4 = dialog.findViewById(R.id.tv_audience_4);

        ProgressView progressView1 = dialog.findViewById(R.id.progress_1);
        ProgressView progressView2 = dialog.findViewById(R.id.progress_2);
        ProgressView progressView3 = dialog.findViewById(R.id.progress_3);
        ProgressView progressView4 = dialog.findViewById(R.id.progress_4);

        Button btnThanks = dialog.findViewById(R.id.btn_thanks);

        tvProgress1.setText(chooseA + "%");
        tvProgress2.setText(chooseB + "%");
        tvProgress3.setText(chooseC + "%");
        tvProgress4.setText(chooseD + "%");

        progressView1.setProgress(chooseA);
        progressView2.setProgress(chooseB);
        progressView3.setProgress(chooseC);
        progressView4.setProgress(chooseD);

        btnThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    private void showHelpCall(Question ques) {
        mediaPlay.playMedia(App.getInstance(), R.raw.help_call);
        new Handler().postDelayed(() -> {
            mediaPlay.stopMedia();
            mediaPlay.playMedia(App.getInstance(),R.raw.help_callb);

            new Handler().postDelayed(() -> {
                if (ques.trueCase.equals("1")) {
                    showDialogCall("A");

                }

                if (ques.trueCase.equals("2")) {
                    showDialogCall("B");

                }

                if (ques.trueCase.equals("3")) {
                    showDialogCall("C");

                }

                if (ques.trueCase.equals("4")) {

                    showDialogCall("D");

                }
            },8000);
        },3000);


    }

    private void showDialogCall(String answ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Đáp án là: " + answ);
        builder.setCancelable(false);
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    private void showHelp5050(Question ques) {

        mediaPlay.playMedia(App.getInstance(), R.raw.sound5050);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ques.trueCase.equals("1")) {
                    btnCaseB.setText("B");
                    btnCaseC.setText("C");
                }

                if (ques.trueCase.equals("2")) {
                    btnCaseA.setText("A");
                    btnCaseC.setText("C");
                }

                if (ques.trueCase.equals("3")) {
                    btnCaseA.setText("A");
                    btnCaseD.setText("D");
                }

                if (ques.trueCase.equals("4")) {
                    btnCaseB.setText("B");
                    btnCaseC.setText("C");
                }
            }
        },3000);


    }

}
