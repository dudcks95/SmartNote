package com.example.test111;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Toolbar mToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

        Button btn = (Button) findViewById(R.id.search_button);
        Button move = (Button) findViewById(R.id.quiz_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색 버튼 누를 시 이벤트
                //TextView searchText = (TextView) findViewById(R.id.searchText);
                //String keyword = searchText.getText().toString();

                // 화면 전환을 위한 intent 사용
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                //intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });

        move.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // 화면 전환을 위한 intent 사용
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        /*switch (item.getItemId()){
            case android.R.id.home:{ //toolbar의 back키 눌렀을 때 동작
                finish();
                return true;
            }
        }*/
        return super.onOptionsItemSelected(item);
    }
}
/*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = (Button) findViewById(R.id.searchBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색 버튼 누를 시 이벤트
                TextView searchText = (TextView) findViewById(R.id.searchText);
                String keyword = searchText.getText().toString();

                // 화면 전환을 위한 intent 사용
                Intent intent = new Intent(MainActivity.this, SubActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });

    }

 */
