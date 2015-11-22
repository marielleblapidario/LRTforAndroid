package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by vetkin123 on 10/27/2015.
 */
public class ReportDatabaseHelper extends SQLiteOpenHelper {

    static final String SCHEMA = "lrtforandroid";

    public ReportDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, SCHEMA, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //here is where you create your tables
        String sql = "CREATE TABLE " + Report.TABLE_NAME +
                " ( " + Report.COLUMN_REPORT_ID + " INTEGER PRIMARY KEY, "
                + " " + Report.COLUMN_REPORT_TITLE + " TEXT , "
                + " " + Report.COLUMN_TYPE + " TEXT , "
                + " " + Report.COLUMN_STATUS + " TEXT , "
                + " " + Report.COLUMN_STATION + " TEXT , "
                + " " + Report.COLUMN_USER + " INTEGER , "
                + " " + Report.COLUMN_ICON + " INTEGER );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addReport(Report report){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Report.COLUMN_REPORT_TITLE, report.getReportTitle());
        //cv.put(Report.COLUMN_TYPE, report.getType());
        cv.put(Report.COLUMN_STATUS, report.getStatus());
        cv.put(Report.COLUMN_STATION, report.getStation());
        cv.put(Report.COLUMN_USER, report.getUser());
        cv.put(Report.COLUMN_ICON,report.getIcon());
        long result = db.insert(Report.TABLE_NAME, null, cv);
        db.close();

        if(result == -1)
            return false;
        return true;

    }

    public ArrayList<Report> getAllReport (){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Report.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<Report> reportList = new ArrayList<>();
        if(c.moveToFirst()){
            do{

                Report report = new Report();
                report.setReportId(c.getInt(c.getColumnIndex(Report.COLUMN_REPORT_ID)));
                report.setReportTitle(c.getString(c.getColumnIndex(Report.COLUMN_REPORT_TITLE)));
                // report.setType(c.getString(c.getColumnIndex(Report.COLUMN_TYPE)));
                report.setStatus(c.getString(c.getColumnIndex(Report.COLUMN_STATUS)));
                report.setStation(c.getString(c.getColumnIndex(Report.COLUMN_STATUS)));
                report.setUser(c.getInt(c.getColumnIndex(Report.COLUMN_USER)));
                report.setIcon(c.getInt(c.getColumnIndex(Report.COLUMN_ICON)));
                reportList.add(report);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return reportList;
    }

    public Report getReport(int id){
        Report report = new Report();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(Report.TABLE_NAME, null, Report.COLUMN_REPORT_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()) {
            report.setReportId(c.getInt(c.getColumnIndex(Report.COLUMN_REPORT_ID)));
            report.setReportTitle(c.getString(c.getColumnIndex(Report.COLUMN_REPORT_TITLE)));
             // report.setType(c.getString(c.getColumnIndex(Report.COLUMN_TYPE)));
            report.setStatus(c.getString(c.getColumnIndex(Report.COLUMN_STATUS)));
            report.setStation(c.getString(c.getColumnIndex(Report.COLUMN_STATUS)));
            report.setUser(c.getInt(c.getColumnIndex(Report.COLUMN_USER)));
            report.setIcon(c.getInt(c.getColumnIndex(Report.COLUMN_ICON)));
        }
        c.close();
        db.close();
        return report;
    }

    public boolean deleteReport(int id){
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(Report.TABLE_NAME, Report.COLUMN_REPORT_ID + " = ? ", new String[]{String.valueOf(id)});

        if(count == 0)
            return false;
        return true;
    }
}


