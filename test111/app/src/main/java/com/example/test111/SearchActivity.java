package com.example.test111;
/*
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
        btn.setOnClickListener(new View.OnClickListener() {
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
        });

    }
}*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {


    ImageView imView;
    TextView txtView;
    String imgUrl = "http://dnllab.incheon.ac.kr/appimg/";
    Bitmap bmImg;
    //back task;
    phpDown task;
    ArrayList<ListItem> listItem= new ArrayList<ListItem>();
    ListItem Item;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        task = new phpDown();
        txtView = (TextView)findViewById(R.id.txtView);

        //imView = (ImageView) findViewById(R.id.imageView1);

        task.execute("http://dnllab.incheon.ac.kr/appdata1.php");





    }


    private class back extends AsyncTask<String, Integer,Bitmap>{



        @Override
        protected Bitmap doInBackground(String... urls) {
            // TODO Auto-generated method stub
            try{
                URL myFileUrl = new URL(urls[0]);
                HttpURLConnection conn = (HttpURLConnection)myFileUrl.openConnection();
                conn.setDoInput(true);
                conn.connect();
                //String json = DownloadHtml("http://117.16.243.116/appdata.php");
                InputStream is = conn.getInputStream();

                bmImg = BitmapFactory.decodeStream(is);


            }catch(IOException e){
                e.printStackTrace();
            }
            return bmImg;
        }

        protected void onPostExecute(Bitmap img){
            imView.setImageBitmap(bmImg);
        }

    }
    private class phpDown extends AsyncTask<String, Integer,String>{



        @Override
        protected String doInBackground(String... urls) {
            StringBuilder jsonHtml = new StringBuilder();
            try{
                // 연결 url 설정
                URL url = new URL(urls[0]);
                // 커넥션 객체 생성
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                // 연결되었으면.
                if(conn != null){
                    conn.setConnectTimeout(10000);
                    conn.setUseCaches(false);
                    // 연결되었음 코드가 리턴되면.
                    if(conn.getResponseCode() == HttpURLConnection.HTTP_OK){
                        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
                        for(;;){
                            // 웹상에 보여지는 텍스트를 라인단위로 읽어 저장.
                            String line = br.readLine();
                            if(line == null) break;
                            // 저장된 텍스트 라인을 jsonHtml에 붙여넣음
                            jsonHtml.append(line + "\n");
                        }
                        br.close();
                    }
                    conn.disconnect();
                }
            } catch(Exception ex){
                ex.printStackTrace();
            }
            return jsonHtml.toString();

        }

        protected void onPostExecute(String str){

            String imgurl;
            String txt1;
            String txt2;
            try{

                JSONObject root = new JSONObject(str);
                JSONArray ja = root.getJSONArray("results");
                for(int i=0; i<ja.length(); i++){
                    JSONObject jo = ja.getJSONObject(i);
                    imgurl = jo.getString("imgurl");
                    txt1 = jo.getString("txt1");
                    txt2 = jo.getString("txt2");
                    listItem.add(new ListItem(imgurl,txt1,txt2));
                }
            }catch(JSONException e){
                e.printStackTrace();
            }
            txtView.setText("urlimg :"+listItem.get(0).getData(0)+"\ntxt1:"+ listItem.get(0).getData(1)+"\ntxt2:"+listItem.get(0).getData(2));
        }


    }



}

