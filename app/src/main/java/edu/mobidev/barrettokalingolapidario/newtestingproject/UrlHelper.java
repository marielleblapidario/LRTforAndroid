package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.os.AsyncTask;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;

/**
 * Created by vetkin123 on 11/1/2015.
 */
public class UrlHelper extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... params) {

        //sample
        OkHttpClient client = new OkHttpClient();
        Response response = null;

        RequestBody responseBody = new FormEncodingBuilder()
                .add("report", params[0])
                .build();

        Request r = new Request.Builder().url("http://192.168.1.5:8084/LRTforAndroid/SignIn").post(responseBody).build();
        try {
            response = client.newCall(r).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // tvResponse.setText(s);

        String todisplay = "";

        try {
            JSONArray array = new JSONArray(s);
            for(int i = 0; i < array.length(); i++){
                todisplay += array.getJSONObject(i).getString("name") + "\n";
            }
        } catch (JSONException e) {


        }


        //    tvResponse.setText(todisplay);
    }}
