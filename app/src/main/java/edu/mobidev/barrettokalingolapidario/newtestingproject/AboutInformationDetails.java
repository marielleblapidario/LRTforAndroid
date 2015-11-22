package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.mobidev.barrettokalingolapidario.testingproject.R;

public class AboutInformationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_about_information_details);

        ArrayList<Information> information = new ArrayList<Information>();

        information.add(new Information("Company Profile",
                "Background",
                "The Light Rail Transit Authority is recognized as the premiere rail transit in the country providing reliable, efficient, dependable, and environment-friendly mass rail services to all residents of Metro Manila. It is primarily responsible for the construction, operation, maintenance and/or lease of light rail transit systems in the Philippines.",
                "Pledge",
                "We, at LRTA, commit to comply with the requirements of the Quality Management System and continually improve our effectiveness in providing quality urban mass rail transport and related services in order to achieve our goal of SERBISYONG AYOS!",
                R.drawable.lrt_first));

        information.add(new Information("Vision and Mission",
                "Vision",
                "The recognized leader and expert in providing integrated urban rail transport systems of the country by 2020.",
                "Mission",
                "To enhance people mobility and provide world class light rail transport systems with continued commitment to excellence in service.",
                R.drawable.lrt_second));

        information.add(new Information("Operating Hours",
                "LRT Line 1 System",
                "Weekdays - 05:00AM to 10:00PM \nWeekends and Holidays - 05:00AM to 09:30PM",
                "LRT Line 2 System",
                "Weekdays - 05:00AM to 10:00PM \nWeekends and Holidays - 05:00AM to 09:00PM",
                R.drawable.lrt_third));

        information.add(new Information("Contact Us",
                "Website",
                "http://www.lrta.gov.ph",
                "E-mail Address",
                "lrtamain@lrta.gov.ph",
                R.drawable.lrt_fourth));

        int position = getIntent().getExtras().getInt("ITEM_POSITION");

        ImageView iv_InformationIcon = (ImageView) findViewById(R.id.iv_informationIcon);
        iv_InformationIcon.setImageResource(information.get(position).getIcon());

        TextView tv_informationTitle = (TextView) findViewById(R.id.tv_informationTitle);
        tv_informationTitle.setText(information.get(position).getInformationTitle());

        TextView tv_subtitleOne = (TextView) findViewById(R.id.tv_subtitleOne);
        tv_subtitleOne.setText(information.get(position).getSubtitleOne());

        TextView tv_descriptionOne = (TextView) findViewById(R.id.tv_descriptionOne);
        tv_descriptionOne.setText(information.get(position).getDescriptionOne());

        TextView tv_subtitleTwo = (TextView) findViewById(R.id.tv_subtitleTwo);
        tv_subtitleTwo.setText(information.get(position).getSubtitleTwo());

        TextView tv_descriptionTwo = (TextView) findViewById(R.id.tv_descriptionTwo);
        tv_descriptionTwo.setText(information.get(position).getDescriptionTwo());

        Button btn_back = (Button) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(goBack);

    }

    View.OnClickListener goBack = new View.OnClickListener(){
        @Override
        public void onClick (View v){
            Intent intent = new Intent();
            intent.setClass(getBaseContext(), About.class);
            startActivity(intent);
            finish();
        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_about__company_profile, menu);
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
