package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class TemplateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.template_container);
    }

    public void setUp(int resource){
        LinearLayout template = (LinearLayout) findViewById(R.id.template_container);
        LinearLayout header = (LinearLayout) View.inflate(getBaseContext(), R.layout.header, null);
        LinearLayout content = (LinearLayout) View.inflate(getBaseContext(), resource, null);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        content.setLayoutParams(params);

        template.addView(header);
        template.addView(content);

        Log.i("TA ", template.toString());
        Log.i("TA ", "HEIGHT " + content.getLayoutParams().height);

        Button LRTStations = (Button) findViewById(R.id.button_LRTStations);
        LRTStations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        Button AboutLRT = (Button) findViewById(R.id.button_AboutLRT);
        AboutLRT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), About.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });

        Button SendReport = (Button) findViewById(R.id.button_SendReport);
        SendReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), SendReport.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(i);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_template, menu);
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
}
