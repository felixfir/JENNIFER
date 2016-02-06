package com.jennifer.controller;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.jennifer.R;
import com.jennifer.model.ApPublicas;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

public class ApPublicasController extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apuestas_publicas);

        if(isNetworkAvailable()){
            new ListLoad().execute((Void) null);
        }
    }

    public class ListLoad extends AsyncTask<Void, Void, Boolean> {
        private ApPublicas[] jsonList;
        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder().url("http://excellentprogrammers.esy.es/Script/apPublicas/apPublic.php").build();
                Response response = client.newCall(request).execute();
                String result = response.body().string();

                Gson gson = new Gson();

                Type collectionType = new TypeToken<Collection<ApPublicas>>() {
                }.getType();
                Collection<ApPublicas> enums = gson.fromJson(result, collectionType);
                jsonList = enums.toArray(new ApPublicas[enums.size()]);
            } catch (IOException e) {
                e.getStackTrace();
            }
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            TextView title = (TextView) findViewById(R.id.nombre_apuesta);
            title.setText(this.jsonList[0].getName());
        }
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
