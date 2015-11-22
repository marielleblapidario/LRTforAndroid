package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import edu.mobidev.barrettokalingolapidario.testingproject.R;
import okio.Timeout;

public class SignIn extends AppCompatActivity {
    EditText et_username;
    EditText et_password;
    TextView tv_test;
    Button signIn;
    Button signUp;
    User user;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //when keyboard is typed
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        et_username = (EditText) findViewById(R.id.editText_Username);
        et_password = (EditText) findViewById(R.id.editText_Password);
        signIn = (Button) findViewById(R.id.button_SignIn);
        signUp = (Button) findViewById(R.id.button_SignUp);
        tv_test = (TextView) findViewById(R.id.tv_test);
        signUp.setOnClickListener(goToSignUp);
        signIn.setOnClickListener(goSignIn);
        SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);


    }

    protected void onResume() {
        super.onResume();
    }

    View.OnClickListener goSignIn = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            /*Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), MainActivity.class);
            startActivity(explicitIntent);
            */
            ServerSignIn uhelp2 = new ServerSignIn();
            uhelp2.execute(et_username.getText().toString(), et_password.getText().toString());
        }
    };

    View.OnClickListener goToSignUp = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), SignUp.class);
            startActivity(explicitIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_in, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        //      if (id == R.id.action_logout) {
        //    Intent i = new Intent(getBaseContext(),SignIn.class);
        //   startActivity(i);
        //    return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    /*urlHelper start here*/
    private class ServerSignIn extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {

            //sample
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            client.setConnectTimeout(100, TimeUnit.SECONDS);

            RequestBody requestBody = new FormEncodingBuilder()
                    .add("username", params[0]).add("password", params[1])
                    .build();

            Request r = new Request.Builder().url(ipconfig.ip + "/SignIn").post(requestBody).build();
            try {
                response = client.newCall(r).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Log.i("urlconnect", "example");


            String result = "";
            try {
                result = response.body().string();
            } catch (IOException e) {

            }

            return result;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            try {
                JSONObject jo = new JSONObject(s);

                user = new User(jo.getInt("userID"), jo.getString("firstName"), jo.getString("lastName"),
                        jo.getString("username"), jo.getString("email"), jo.getString("password"));
                Log.i("user: ", user.getUserId() + "");
                Log.i("user: ", user.getFirstName() + "");
                Log.i("user: ", user.getLastName() + "");
                Log.i("user: ", user.getEmail() + "");
                Log.i("user: ", user.getUsername() + "");
                Log.i("user: ", user.getPassword() + "");

            } catch (JSONException e) {
            }
            if (user != null) {
                SharedPreferences sp = getSharedPreferences("login", MODE_PRIVATE);

                /*marielle's experiment*/
                SharedPreferences.Editor editor = sp.edit();
                Gson gson = new Gson();
                String json = gson.toJson(user);
                editor.putString("user", json);
                editor.commit();

                /*experiement end*/


                /*
                String userID = sp.getString("userID", "");
                SharedPreferences.Editor spEditor = sp.edit();
                spEditor.putString("id", userID);
                spEditor.commit();
                */
                Intent i = new Intent();
                i.setClass(getBaseContext(), MainActivity.class);

                startActivity(i);
                finish();
            } else {
                Toast.makeText(getBaseContext(), "Invalid Username/Password!", Toast.LENGTH_LONG).show();
            }
        }
    }
    /*urlHelper ends here*/
}
