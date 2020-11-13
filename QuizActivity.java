package com.example.test111;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;



public class QuizActivity extends AppCompatActivity {

    double screen_width, screen_height;

    final int Question = 5;

    Random rnd;
    String[] RandomNames = {"첫번째","두번째","세번째" };
    //배열로 테스트중

    TextView score_status;
    int hit = 0;
    int no_of_clays_left = Question;
    int no_of_hits = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activitiy);

        //ConstraintLayout layout = (ConstraintLayout)findViewById(R.id.layout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screen_width = size.x; // 어플 x축 길이
        screen_height = size.y; // 어플 y축 길이


        //문제 스코어판 생성
        score_status = new TextView(this);
        score_status.setX(150f); // 상태 표시줄 x축 경로
        score_status.setY((float)screen_height - 700f); // 상태 표시줄 y축 경로
        score_status.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT));
        score_status.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
        score_status.setTextColor(Color.parseColor("#FFFFFF")); // 글 색깔
        score_status.setTextSize(16); // 텍스트 크기
        //layout.addView(score_status);  레이아웃 팅김

        ObjectAnimator object = ObjectAnimator.ofFloat()
        //clay_translateX.addListener(new Animator.AnimatorListener() {  오류 부분
            // 애니메이션 시작시 실행
            @Override
            public void onAnimationStart(Animator animator) {
                score_status.setText("게임 시작" + "\n남은 문제 수:" + no_of_clays_left + " / 5\n맞춘 문제 수 : 0");
            }

            // 애니메이션 종료시 실행
            @Override
            public void onAnimationEnd(Animator animator) {
                if(hit == 1)
                    no_of_hits ++;
                no_of_clays_left --;
                score_status.setText("게임 종료!!" + "\n정답 수: " + no_of_clays_left + " / 5\n맞춘 문제 수:" + no_of_hits);
            }

            // 애니메이션 취소 시 실행
            @Override
            public void onAnimationCancel(Animator animator) {

            }

            // 애니메이션 반복될 때 실행
            @Override
            public void onAnimationRepeat(Animator animator) {
                if(hit == 1)
                    no_of_hits ++;
                hit = 0;
                no_of_clays_left --;
                if(no_of_clays_left > 0) {
                    score_status.setText("게임 중" + "\n남은 문제 수:" + no_of_clays_left + " / 5\n맞춘 문제 수: " + no_of_hits);
                }
            }
        });
/* 난수 부분
        rnd = new Random();
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(t);
    }
        Button.OnClickListener t = new Button.OnClickListener() {
        public void onClick(View v) {

            Button btn = (Button) findViewById(v.getId());
            int num = rnd.nextInt(100);
            Log.v("클릭", num + " "); //버튼을 누르면 로그창에 0부터 100까지의 랜덤값이 나타난다.

        }
    };*/
}





