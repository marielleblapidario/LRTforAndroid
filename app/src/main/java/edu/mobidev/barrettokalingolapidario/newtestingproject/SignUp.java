package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
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

import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class SignUp extends AppCompatActivity {
    //User user;
    EditText et_lastname;
    EditText et_firstname;
    EditText et_email;
    EditText et_username;
    EditText et_password;
    Button signUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //when keyboard is typed
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        et_lastname = (EditText) findViewById(R.id.editText_lastname);
        et_firstname = (EditText) findViewById(R.id.editText_firstname);
        et_email = (EditText) findViewById(R.id.editText_email);
        et_username = (EditText) findViewById(R.id.editText_username);
        et_password = (EditText) findViewById(R.id.editText_password);

        signUp = (Button) findViewById(R.id.button_SignUp);

        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new UrlHelper2().execute(et_lastname.getText().toString(), et_firstname.getText().toString(),
                        et_email.getText().toString(), et_username.getText().toString(), et_password.getText().toString());
                Intent explicitIntent = new Intent();
                explicitIntent.setClass(getBaseContext(), SignIn.class);
                startActivity(explicitIntent);
                finish();

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        // if (id == R.id.action_logout) {
        //   Intent i = new Intent(getBaseContext(),SignIn.class);
        // startActivity(i);
        //  return true;
        //}

        return super.onOptionsItemSelected(item);
    }

    /*urlHelper Starts here*/
    private class UrlHelper2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            //sample
            OkHttpClient client = new OkHttpClient();
            Response response = null;
            //   ResponseBody rb = response.body();
            RequestBody responseBody = new FormEncodingBuilder()
                    .add("lastname", params[0]).add("firstname", params[1]).add("email",params[2]).add("username",params[3])
                    .add("password",params[4])
                    .build();


            client.setConnectTimeout(100, TimeUnit.SECONDS);

            Request r = new Request.Builder().url(ipconfig.ip + "/signup").post(responseBody).build();
            try {
                response = client.newCall(r).execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
        }


    }
    /*urlHelper ends here*/

}

/*
    View.OnClickListener goToSignIn = new View.OnClickListener(){
        @Override
        public void onClick (View v){

            new UrlHelper.execute(et_lastname.getText().toString(), et_firstname.getText().toString(),
                   et_email.getText().toString(), et_username.getText().toString(), et_password.getText().toString());
            /*
            User user = new User ();
            user.setLastName(et_lastname.getText().toString());
            user.setFirstName(et_firstname.getText().toString());
            user.setEmail(et_email.getText().toString());
            user.setUsername(et_username.getText().toString());
            user.setPassword(et_password.getText().toString());
            UserDatabaseHelper udHelper = new UserDatabaseHelper(getBaseContext(),UserDatabaseHelper.SCHEMA, null, 1);
            udHelper.addUser(user);
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), SignIn.class);
            startActivity(explicitIntent);
            finish();

        }
    }; */

