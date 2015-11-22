package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class Template_Report extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_template_report);

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
        ListView lvReports = (ListView) findViewById(R.id.list_reports);
        ReportsAdapter reportsAdapter = new ReportsAdapter(getBaseContext(), R.layout.list_reports, array_reports);
        lvReports.setAdapter(reportsAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_template__report, menu);
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
