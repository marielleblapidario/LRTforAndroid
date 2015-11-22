package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class StationReport extends AppCompatActivity {

    Button stationBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_report);

        ServerGetReports uhelp2 = new ServerGetReports();
        uhelp2.execute(String.valueOf(getIntent().getExtras().getInt("stationposition")));

        ArrayList<Report> array_reports = new ArrayList<>();
/*
        array_reports.add(new Report("Light Crowd", "Roosevelt Station","Traffic", R.drawable.ic_eye));
        array_reports.add(new Report("Code Red", "Monumento Station", "Code", R.drawable.ic_eye));
        array_reports.add(new Report("Heavy Crowd", "Central Terminal Station", "Traffic", R.drawable.ic_eye));
        array_reports.add(new Report("Heavy Crowd", "Roosevelt Station","Traffic", R.drawable.ic_eye));
        array_reports.add(new Report("Code Red", "Monumento Station","Code", R.drawable.ic_eye));
        array_reports.add(new Report("Heavy Crowd", "Central Terminal Station","Traffic", R.drawable.ic_eye));
        array_reports.add(new Report("Heavy Crowd", "Roosevelt Station","Traffic", R.drawable.ic_eye));
        array_reports.add(new Report("Code Red", "Monumento Station", "Code", R.drawable.ic_eye));
        array_reports.add(new Report("Heavy Crowd", "Central Terminal Station", "Traffic", R.drawable.ic_eye));
*/
        ListView lvReports = (ListView) findViewById(R.id.lv_StationReports);
        ReportsAdapter reportsAdapter = new ReportsAdapter(getBaseContext(), R.layout.list_reports, array_reports);
        lvReports.setAdapter(reportsAdapter);

        String stationName = getIntent().getExtras().getString("STATION NAME");
        TextView tv_StationName = (TextView) findViewById(R.id.tv_stationName);
        tv_StationName.setText(stationName);

        stationBack = (Button) findViewById(R.id.btn_tationSend);
        stationBack.setOnClickListener(goToMain);
    }

    View.OnClickListener goToMain = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), MainActivity.class);
            startActivity(explicitIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_station_report, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent i = new Intent(getBaseContext(),SignIn.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class ServerGetReports extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            //sample
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            client.setConnectTimeout(100, TimeUnit.SECONDS);

            RequestBody requestBody = new FormEncodingBuilder()
                    .add("stationposition", params[0])
                    .build();

            Request r = new Request.Builder().url(ipconfig.ip + "/StationGetter").post(requestBody).build();
            try {
                response = client.newCall(r).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("urlconnect", "example");


            String result = "";
            try {
                //Log.i("signin", response.body().string());
                result = response.body().string();
            } catch (IOException e) {


            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            ArrayList<Report> reportslist = new ArrayList<>()
                    Report r = new Report();
;            try {
                JSONArray joa = new JSONArray(s);
                for(int i = 0; i < joa.length(); i++){
                    JSONObject object = joa.getJSONObject(i);
                    r = new Report(object.getInt("reportId"), object.getString("type"), object.getString("status")
                    , object.getString("details"), object.getString("station"));


                    //reportslist.add(object);

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
    }
}
