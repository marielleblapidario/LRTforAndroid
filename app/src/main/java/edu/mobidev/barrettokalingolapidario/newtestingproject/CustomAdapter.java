package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.app.Service;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

/**
 * Created by mariachristinadalekalingo on 10/8/15.
 */
public class CustomAdapter extends ArrayAdapter <Station> {

    ArrayList<Station> stations;

    public CustomAdapter(Context context, int resource, ArrayList <Station> stations) {
        super(context, resource, stations);
        this.stations = stations;
    }

    private class stationHolder {
        ImageView icon;
        TextView stationName;
        TextView stationCity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_stations, parent, false);
            stationHolder sHolder = new stationHolder();
            sHolder.icon = (ImageView) convertView.findViewById(R.id.iv_icon);
            sHolder.stationName = (TextView) convertView.findViewById(R.id.tv_stationName);
            sHolder.stationCity = (TextView) convertView.findViewById(R.id.tv_stationCity);
            convertView.setTag(sHolder);
        }

        stationHolder sHolder = (stationHolder) convertView.getTag();
        sHolder.icon.setImageResource(stations.get(position).getIcon());
        sHolder.stationName.setText(stations.get(position).getStationName());
        sHolder.stationCity.setText(stations.get(position).getStationCity());

        return convertView;
    }


}
