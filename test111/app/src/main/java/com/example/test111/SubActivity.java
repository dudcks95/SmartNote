package com.example.test111;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class SubActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = getIntent();
                    String keyword = intent.getStringExtra("keyword");
                    String str = getNaverSearch(keyword);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView searchResult2 = (TextView) findViewById(R.id.searchResult2);
                            searchResult2.setText(str);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });thread.start();
    }



    public String getNaverSearch(String keyword) {

        String clientID = "Cn6rQIwqZlTayF99c8j7"; // 네이버 검색 클라이언트 api id
        String clientSecret = "vqSLm62jtT";       // 네이버 검색 클라이언트 secret code
        StringBuffer sb = new StringBuffer();

        try {
            String text = URLEncoder.encode(keyword, "UTF-8");
            //String apiURL = "https://openapi.naver.com/v1/search/encyc.xml?query=" + text + "&display=10" + "&start=1";
            //https://openapi.naver.com/v1/search/encyc.xml
            String apiURL = "https://openapi.naver.com/v1/search/encyc.xml?query=%EC%A3%BC%EC%8B%9D&display=10&start=1&sort=sim" + text + "&display=10" + "&start=1";

            URL url = new URL(apiURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("X-Naver-Client-Id", clientID);
            conn.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xpp = factory.newPullParser();
            String tag;

            //inputStream으로부터 xml값 받기
            xpp.setInput(new InputStreamReader(conn.getInputStream(), "UTF-8"));

            xpp.next();
            int eventType = xpp.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        tag = xpp.getName(); //태그 이름 얻어오기

                        if (tag.equals("item")) ; //첫번째 검색 결과
                        else if (tag.equals("title")) {
                            sb.append("제목 : ");
                            xpp.next();
                            sb.append(xpp.getText().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
                            sb.append("\n");
                        } else if (tag.equals("description")) {
                            sb.append("내용 : ");
                            xpp.next();
                            sb.append(xpp.getText().replaceAll("<(/)?([a-zA-Z]*)(\\\\s[a-zA-Z]*=[^>]*)?(\\\\s)*(/)?>", ""));
                            sb.append("\n");
                        }
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            return e.toString();
        }
        return sb.toString();
    }
}
