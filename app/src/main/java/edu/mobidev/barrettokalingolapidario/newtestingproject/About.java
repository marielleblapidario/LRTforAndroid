package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class About extends TemplateActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_about);

        ArrayList<Information> information = new ArrayList<Information>();

        information.add(new Information("Company Profile", R.drawable.lrt_first));
        information.add(new Information("Vision and Mission", R.drawable.lrt_second));
        information.add(new Information("Operating Hours", R.drawable.lrt_third));
        information.add(new Information("Contact Us", R.drawable.lrt_fourth));

        ListView lvInformation = (ListView) findViewById(R.id.list_informationItem);
        InformationAdapter informationAdapter = new InformationAdapter(getBaseContext(), R.layout.list_information, information);
        lvInformation.setAdapter(informationAdapter);

        lvInformation.setOnItemClickListener(onItemSelect);

    }

    AdapterView.OnItemClickListener onItemSelect = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Intent i = new Intent();
            i.setClass(getBaseContext(), AboutInformationDetails.class);
            i.putExtra("ITEM_POSITION", position);
            startActivity(i);
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about, menu);
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
            return true;        }

        return super.onOptionsItemSelected(item);
    }
}
