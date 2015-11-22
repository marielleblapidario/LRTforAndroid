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
import java.util.List;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

/**
 * Created by G301 on 9/24/2015.
 */
public class ReportsAdapter extends ArrayAdapter <Report> {

    ArrayList<Report> reports;

    public ReportsAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
        reports = (ArrayList<Report>) objects;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // return super.getView(position, convertView, parent);

        if(convertView == null){
            // we hhave to inflage/create the XML
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView =  inflater.inflate(R.layout.list_reports,parent,false);
        }

        TextView tvReport = (TextView) convertView.findViewById(R.id.tv_report);
        TextView tvStation = (TextView) convertView.findViewById(R.id.tv_station);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.tv_icon);

        tvReport.setText(reports.get(position).getReportTitle());
        tvStation.setText(reports.get(position).getStation());
        ivIcon.setImageResource(reports.get(position).getIcon());

        return convertView;
    }
}
