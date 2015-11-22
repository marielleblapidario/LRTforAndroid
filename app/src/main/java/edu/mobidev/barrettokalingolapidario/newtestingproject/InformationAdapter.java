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
 * Created by mariachristinadalekalingo on 10/11/15.
 */
public class InformationAdapter extends ArrayAdapter <Information> {

    ArrayList <Information> information;

    public InformationAdapter(Context context, int resource, ArrayList <Information> information) {
        super(context, resource, information);
        this.information = information;
    }

    private class informationHolder {
        ImageView informationIcon;
        TextView informationTitle;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Service.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_information, parent, false);
            informationHolder iHolder = new informationHolder();
            iHolder.informationIcon = (ImageView) convertView.findViewById(R.id.iv_informationIcon);
            iHolder.informationTitle = (TextView) convertView.findViewById(R.id.tv_informationTitle);
            convertView.setTag(iHolder);
        }

        informationHolder iHolder = (informationHolder) convertView.getTag();
        iHolder.informationIcon.setImageResource(information.get(position).getIcon());
        iHolder.informationTitle.setText(information.get(position).getInformationTitle());

        return convertView;
    }

}
