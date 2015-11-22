package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class SendReport extends TemplateActivity {

    Button btn_crowdTraffic;
    Button btn_codeAlert;
    Button btn_closure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_send_report);

        btn_crowdTraffic = (Button) findViewById(R.id.btn_SendCrowdTraffic);
        btn_crowdTraffic.setOnClickListener(goToCrowdTrafficReport);

        btn_codeAlert = (Button) findViewById(R.id.btn_SendCodeAlert);
        btn_codeAlert.setOnClickListener(goToCodeAlertReport);

        btn_closure = (Button) findViewById(R.id.btn_SendClosure);
        btn_closure.setOnClickListener(goToClosureReport);

    }

    View.OnClickListener goToCrowdTrafficReport = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), ReportCrowdTraffic.class);
            startActivity(explicitIntent);
        }
    };

    View.OnClickListener goToCodeAlertReport = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), ReportCodeAlert.class);
            startActivity(explicitIntent);
        }
    };

    View.OnClickListener goToClosureReport = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), ReportClosure.class);
            startActivity(explicitIntent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_report, menu);
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
