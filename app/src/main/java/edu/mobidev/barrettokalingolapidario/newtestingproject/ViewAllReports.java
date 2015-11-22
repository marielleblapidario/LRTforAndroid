package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class ViewAllReports extends TemplateActivity {

    Button btn_allReports;
    Button btn_ViewCrowdTraffic;
    Button btn_ViewCodeAlert;
    Button btn_ViewClosure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_view_all_reports);

        btn_allReports = (Button) findViewById(R.id.btn_allReports);
        btn_allReports.setOnClickListener(goToAllReports);

        btn_ViewCrowdTraffic = (Button) findViewById(R.id.btn_ViewCrowdTraffic);
        btn_ViewCrowdTraffic.setOnClickListener(goToCrowdTraffic);

        btn_ViewCodeAlert = (Button) findViewById(R.id.btn_ViewCodeAlert);
        btn_ViewCodeAlert.setOnClickListener(goToCodeAlert);

        btn_ViewClosure = (Button) findViewById(R.id.btn_ViewClosure);
        btn_ViewClosure.setOnClickListener(goToClosure);

        
    }

    View.OnClickListener goToAllReports = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), Template_Report.class);
            startActivity(explicitIntent);
        }
    };

    View.OnClickListener goToCrowdTraffic= new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), Template_Report.class);
            startActivity(explicitIntent);
        }
    };
    View.OnClickListener goToCodeAlert = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), Template_Report.class);
            startActivity(explicitIntent);
        }
    };
    View.OnClickListener goToClosure = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent explicitIntent = new Intent();
            explicitIntent.setClass(getBaseContext(), Template_Report.class);
            startActivity(explicitIntent);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_all_reports, menu);
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
