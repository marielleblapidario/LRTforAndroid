package edu.mobidev.barrettokalingolapidario.newtestingproject;

/**
 * Created by vetkin123 on 10/27/2015.
 */


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class StationDatabaseHelper extends SQLiteOpenHelper {

    static final String SCHEMA = "lrtforandroid";

    public StationDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, SCHEMA, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + Station.TABLE_NAME +
                " ( " + Station.COLUMN_STATION_NAME + " TEXT PRIMARY KEY , "
                + " " + Station.COLUMN_STATION_CITY + " TEXT );";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addStation(Station station){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Station.COLUMN_STATION_NAME, station.getStationName());
        cv.put(Station.COLUMN_STATION_CITY, station.getStationCity());
        long result = db.insert(Station.TABLE_NAME, null, cv);
        db.close();
        if(result == -1)
            return false;
        return true;
    }

    public void updateStation(Station station){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Station.COLUMN_STATION_NAME, station.getStationName());
        cv.put(Station.COLUMN_STATION_CITY, station.getStationCity());
        db.update(Station.TABLE_NAME,
                cv,
                Station.COLUMN_STATION_NAME + " = ? ",
                new String[]{String.valueOf(station.getStationName())} );
    }

    public boolean deleteStation(String stationName){
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(Station.TABLE_NAME, Station.COLUMN_STATION_NAME + " = ? ", new String[]{String.valueOf(stationName)});
        if(count == 0)
            return false;
        return true;
    }

    public Station getStation(String stationName){
        Station station = new Station();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Station.TABLE_NAME, null, Station.COLUMN_STATION_NAME + " = ? ", new String[]{String.valueOf(stationName)}, null, null, null);
        if(c.moveToFirst()){
            station.setStationName(c.getString(c.getColumnIndex(Station.COLUMN_STATION_NAME)));
            station.setStationCity(c.getString(c.getColumnIndex(Station.COLUMN_STATION_CITY)));
        }
        c.close();
        db.close();
        return station;
    }

    public ArrayList <Station> getAllStations (){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Station.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Station> stationList = new ArrayList<>();
        if(c.moveToFirst()){
            do{

                Station station = new Station();
                station.setStationName(c.getString(c.getColumnIndex(Station.COLUMN_STATION_NAME)));
                station.setStationCity(c.getString(c.getColumnIndex(Station.COLUMN_STATION_CITY)));
                stationList.add(station);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return stationList;
    }

}
