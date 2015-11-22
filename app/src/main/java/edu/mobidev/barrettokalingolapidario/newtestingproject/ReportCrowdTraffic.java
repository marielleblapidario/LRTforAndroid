package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class ReportCrowdTraffic extends TemplateActivity {

    Spinner spinner;
    Button btn_SendCrowdTraffic;
    Button btn_CancelCrowdTraffic;
    Button btn_Light;
    Button btn_Moderate;
    Button btn_Heavy;
    EditText etDetails;
    TextView tvCharLimit;
    Report report;
    static String status = "";
    static String type = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_report_crowd_traffic);

        //when keyboard is typed
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        ArrayList<String> stations = new ArrayList<>();

        stations.add("Baclaran Station");
        stations.add("EDSA Station");
        stations.add("Libertad Station");
        stations.add("Gil Puyat Station");
        stations.add("Vito Cruz Station");
        stations.add("Qurino Station");
        stations.add("Pedro Gil Station");
        stations.add("United Nations Stations");
        stations.add("Central Terminal Station");
        stations.add("Carriedo Station");
        stations.add("Doroteo Jose Station");
        stations.add("Bambang Station");
        stations.add("Tayuman Station");
        stations.add("Blumentritt Station");
        stations.add("Abad Santos Station");
        stations.add("R.Papa Station");
        stations.add("5th Avenue Station");
        stations.add("Monumento Station");
        stations.add("Balintawak Station");
        stations.add("Roosevelt Station");

        spinner = (Spinner) findViewById(R.id.spinner_stationsCrowdTraffic);
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getBaseContext(), R.layout.spinner_list_item, stations);
        spinner.setAdapter(spinnerAdapter);

        btn_SendCrowdTraffic = (Button) findViewById(R.id.btn_SendCrowdTraffic);
        btn_SendCrowdTraffic.setOnClickListener(sendButton);

        btn_CancelCrowdTraffic = (Button) findViewById(R.id.btn_CancelCrowdTraffic);
        btn_CancelCrowdTraffic.setOnClickListener(goBackToSendReports);

        btn_Light = (Button) findViewById(R.id.btn_light);
        btn_Light.setOnClickListener(lightbutton);
        btn_Moderate = (Button)findViewById(R.id.btn_moderate);
        btn_Moderate.setOnClickListener(moderatebutton);
        btn_Heavy = (Button) findViewById(R.id.btn_heavvy);
        btn_Heavy.setOnClickListener(heavybutton);

        tvCharLimit = (TextView) findViewById(R.id.tv_charLimitCrowdTraffic);

        etDetails = (EditText) findViewById(R.id.et_shareDetailsCrowdTraffic);
        etDetails.addTextChangedListener(mTextEditorWatcher);



    }

    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //This sets a textview to the current length
            int result = 140 - s.length();
            if(result > 1) {
                tvCharLimit.setText(String.valueOf(result) + " characters");
            } else {
                tvCharLimit.setText(String.valueOf(result) + " character");
            }
        }

        public void afterTextChanged(Editable s) {
        }
    };

    View.OnClickListener lightbutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            status = "Light";
            //light
            btn_Light.setBackgroundColor(Color.GREEN);
            btn_Moderate.setBackgroundResource(android.R.drawable.btn_default);
            btn_Heavy.setBackgroundResource(android.R.drawable.btn_default);
        }
    };
    View.OnClickListener moderatebutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            status = "Moderate";
            //moderate
            btn_Moderate.setBackgroundColor(Color.YELLOW);
            btn_Light.setBackgroundResource(android.R.drawable.btn_default);
            btn_Heavy.setBackgroundResource(android.R.drawable.btn_default);
        }
    };
    View.OnClickListener heavybutton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            status = "Heavy";
            //heavy
            btn_Heavy.setBackgroundColor(Color.RED);
            btn_Light.setBackgroundResource(android.R.drawable.btn_default);
            btn_Moderate.setBackgroundResource(android.R.drawable.btn_default);
        }
    };

    View.OnClickListener goBackToSendReports = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), SendReport.class);
            startActivity(explicitIntent);
        }
    };

    View.OnClickListener sendButton = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            ServerSendReport uhelp2 = new ServerSendReport();

            //Sharepref experiment

            SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);
            String jsonuser = sp.getString("user", "");
            String userid = "";
            try {
                JSONObject jo = new JSONObject(jsonuser);
                userid = jo.getString("userId");
            } catch (JSONException e) {
                e.printStackTrace();
            }

            DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            dateFormatter.setLenient(false);
            Date today = new Date();
            String s = dateFormatter.format(today);

            uhelp2.execute(type,
                    status,
                    etDetails.getText().toString(),
                    String.valueOf(spinner.getSelectedItemPosition()),
                    userid,
                    s);


            //1 - crowd traffic 2 - code alert 3 - closure

            //Intent explicitIntent = new Intent();
            //explicitIntent.setClass(getBaseContext(), SendReport.class);


            // Toast.makeText(ReportCrowdTraffic.this, "Sucess! Your report has been sent", Toast.LENGTH_LONG).show();
            // startActivity(explicitIntent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_report_crowd_traffic, menu);
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

    private class ServerSendReport extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            //sample
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            client.setConnectTimeout(100, TimeUnit.SECONDS);

            RequestBody requestBody = new FormEncodingBuilder()
                    .add("type", params[0]).add("status", params[1]).add("details", params[2]).add("station", params[3])
                    .add("user", params[4]).add("date", params[5])
                    .build();
            Request r = new Request.Builder().url(ipconfig.ip + "/SendCrowdTraffic").post(requestBody).build();
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

            /*
            try {
                return response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }*/

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

//            Toast.makeText(getBaseContext(), s, Toast.LENGTH_LONG).show();
            if(s.equals("okay")) {
                Intent i = new Intent();
                i.setClass(getBaseContext(), MainActivity.class);
                Toast.makeText(getBaseContext(), "Report Sent!", Toast.LENGTH_LONG).show();
                startActivity(i);
                finish();
            }else{
                Toast.makeText(getBaseContext(), "Error! Report Not Sent!", Toast.LENGTH_LONG).show();
            }
//
//
//            try {
//                JSONArray array = new JSONArray(s);
//
//                String username = array.getJSONObject(0).getString("username");
//                String password = array.getJSONObject(0).getString("password");
//
//            } catch (JSONException e) {
//
//
//
//
//            }


        }
    }
}
