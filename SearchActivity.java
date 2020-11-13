package com.example.test111;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_activity);


        //Button btn = (Button) findViewById(R.id.searchBtn);
        /*btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 검색 버튼 누를 시 이벤트
                TextView searchText = (TextView) findViewById(R.id.searchText);
                String keyword = searchText.getText().toString();

                // 화면 전환을 위한 intent 사용
                Intent intent = new Intent(SearchActivity.this, SubActivity.class);
                intent.putExtra("keyword", keyword);
                startActivity(intent);
            }
        });*/

    }
}