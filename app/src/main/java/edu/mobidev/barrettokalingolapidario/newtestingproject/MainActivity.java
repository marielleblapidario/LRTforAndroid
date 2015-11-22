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

public class MainActivity extends TemplateActivity {

    ListView lv_Stations;
    CustomAdapter customAdapter;
    StationDatabaseHelper sdHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setUp(R.layout.activity_main);

        sdHelper = new StationDatabaseHelper(getBaseContext(), UserDatabaseHelper.SCHEMA, null, 1);

        ArrayList <Station> stations = new ArrayList<Station>();
        stations.add(new Station("Baclaran Station", "Pasay City", (R.drawable.train_station)));
        stations.add(new Station("EDSA Station", "Pasay City", (R.drawable.train_station)));
        stations.add(new Station("Libertad Station", "Pasay City", (R.drawable.train_station)));
        stations.add(new Station("Gil Puyat Station", "Pasay City", (R.drawable.train_station)));
        stations.add(new Station("Vito Cruz Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Quirino Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Pedro Gil Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("United Nations Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Central Terminal Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Carriedo Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Doroteo Jose Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Bambang Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Tayuman Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Blumentritt Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("Abad Santos", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("R. Papa Station", "Manila City", (R.drawable.train_station)));
        stations.add(new Station("5th Avenue Station", "Caloocan City", (R.drawable.train_station)));
        stations.add(new Station("Monumento Station", "Caloocan City", (R.drawable.train_station)));
        stations.add(new Station("Balintawak Station", "Quezon City", (R.drawable.train_station)));
        stations.add(new Station("Roosevelt Station", "Quezon City", (R.drawable.train_station)));

        for (int i = 0; i < stations.size(); i++){
            sdHelper.addStation(stations.get(i));
        }

        lv_Stations = (ListView) findViewById(R.id.list_stations);
        customAdapter = new CustomAdapter(getBaseContext(), R.layout.list_stations, stations);
        lv_Stations.setAdapter(customAdapter);
        lv_Stations.setOnItemClickListener(onStationSelect);

    }

    AdapterView.OnItemClickListener onStationSelect = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Station stationSelected = (Station) parent.getItemAtPosition(position);
            String stationName = stationSelected.getStationName();
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), StationReport.class);
            intent.putExtra("STATION NAME", stationName);
            intent.putExtra("stationposition", position);
            startActivity(intent);
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

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
            Intent i = new Intent(getBaseContext(), SignIn.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
