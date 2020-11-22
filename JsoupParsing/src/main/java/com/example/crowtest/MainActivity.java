package com.example.crowtest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;


public class MainActivity extends AppCompatActivity {

    private String htmlPageUrl = "http://www.yonhapnews.co.kr/";  // 연합뉴스 페이지
    //private String htmlPageUrl = "https://en.dict.naver.com/#/search?range=all&query=studying"; 네이버 영어사전 studying 검색
    //private String htmlPageUrl = "https://en.dict.naver.com/#/main"; 네이버 영어사전 메인
    //private String htmlPageUrl = "https://en.dict.naver.com/#/entry/enko/f48b66babcd1481a960ad334b28ed778"; 네이버 영어사전 studying 검색
    private TextView textviewHtmlDocument;
    private String htmlContentInStringFormat="";

    int cnt=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textviewHtmlDocument = (TextView)findViewById(R.id.textView);
        textviewHtmlDocument.setMovementMethod(new ScrollingMovementMethod()); // 스크롤 가능한 텍스트뷰 제작

        Button htmlTitleButton = (Button)findViewById(R.id.button);
        htmlTitleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println((cnt+1) +"번째 파싱");
                JsoupAsyncTask jsoupAsyncTask = new JsoupAsyncTask();
                jsoupAsyncTask.execute();
                cnt++;
            }
        });

    }

    private class JsoupAsyncTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params){
            try {
                Document doc = Jsoup.connect(htmlPageUrl).get();

                Elements titles= doc.select("div.news-type01 h2.tit-wrap");
                //Elements titles= doc.select("div.suggestion p.text");
                //Elements titles= doc.select("div.entry_mean");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println(e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
/*
//테스트2
                titles= doc.select("div.news-con h2.tit-news");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }

//테스트3
                titles= doc.select("li.section02 div.con h2.news-tl");

                System.out.println("-------------------------------------------------------------");
                for(Element e: titles){
                    System.out.println("title: " + e.text());
                    htmlContentInStringFormat += e.text().trim() + "\n";
                }
                System.out.println("-------------------------------------------------------------");
*/

            }catch (IOException e){
                e.printStackTrace();
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result){
            textviewHtmlDocument.setText(htmlContentInStringFormat);
        }
    }
}