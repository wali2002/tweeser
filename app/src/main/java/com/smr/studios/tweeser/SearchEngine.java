package com.smr.studios.tweeser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SearchEngine extends AppCompatActivity {

    private Button button;
    private TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_engine);
        button = (Button) findViewById(R.id.buttonTest);
        textView = (TextView) findViewById(R.id.outputHttp);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncTaskRunner runner = new AsyncTaskRunner();
                runner.execute();
            }
        });


    }

    private class AsyncTaskRunner extends AsyncTask<String, String, String> {

        private String resp;
        ProgressDialog progressDialog;

        @Override
        protected String doInBackground(String... params)  {
            Document document = null;
            Map<String,String> hrefLinks = new HashMap<>();

            try {
                document = Jsoup.connect("https://flixable.com/?min-rating=0&min-year=1920&max-year=2019&order=date&page=2").get();

                Elements table = document.getElementsByTag("a");
                for (Element link : table)
                {
                    hrefLinks.put(link.attr("href"),link.text());
                }
            }
            catch (Exception e)
            {
                    return e.getMessage();
            }
            Map<String, String> filteredData = hrefLinks.entrySet().stream().filter(key -> key.getKey().contains("/title/")).collect(Collectors.toMap(o -> o.getKey(), o -> o.getValue()));
            try {
                    File file = new File(getBaseContext().getFilesDir(),"NetFlixData.txt");
                    FileWriter fr = new FileWriter(file, true);
                    BufferedWriter br = new BufferedWriter(fr);
                    PrintWriter pr = new PrintWriter(br);
                    pr.println("code,title");
                    for (Map.Entry<String,String> sabir :filteredData.entrySet()) {
                        String code = sabir.getKey().replace("\\","").replace("titile","");
                        pr.println(code + "," + sabir.getValue());
                    }
                pr.close();
                br.close();
                fr.close();
            }
            catch (Exception e)
            {
                return e.getMessage();
            }

            return hrefLinks.toString();

        }


        @Override
        protected void onPostExecute(String result) {
            // execution of result of Long time consuming operation
            progressDialog.dismiss();
            textView.setText(result);
        }


        @Override
        protected void onPreExecute() {
            progressDialog = ProgressDialog.show(SearchEngine.this,
                    "ProgressDialog",
                    "Wait for "+textView.getText().toString()+ " seconds");
        }


        @Override
        protected void onProgressUpdate(String... text) {
            textView.setText(text[0]);

        }
    }
}
