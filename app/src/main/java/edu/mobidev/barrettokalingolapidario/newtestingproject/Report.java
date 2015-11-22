package edu.mobidev.barrettokalingolapidario.newtestingproject;

import org.json.JSONArray;

/**
 * Created by G301 on 10/8/2015.
 */
public class Report {

    static final String TABLE_NAME = "report";
    static final String COLUMN_REPORT_ID = "reportId";
    static final String COLUMN_REPORT_TITLE = "reportTitle";
    static final String COLUMN_TYPE = "type";
    static final String COLUMN_STATUS = "status";
    static final String COLUMN_STATION = "station";
    static final String COLUMN_USER = "user";
    static final String COLUMN_ICON = "icon";

    private int reportId;
    private String type;
    private String typeName;
    private int station;
    private String stationName;
    private String status;
    private String details;
    private int user;
    private String userName;
    private int icon;




    public Report(int reportId, String type, String typeName, String stationName, int station, String status, String details, int user, String userName, int icon) {
        this.reportId = reportId;
        this.type = type;
        this.typeName = typeName;
        this.stationName = stationName;
        this.station = station;
        this.status = status;
        this.details = details;
        this.user = user;
        this.userName = userName;
        this.icon = icon;
    }

    public Report(int reportId, String typeName, String stationName, String details, String userName, int icon) {
        this.reportId = reportId;
        this.typeName = typeName;
        this.stationName = stationName;
        this.details = details;
        this.userName = userName;
        this.icon = icon;
    }

    public Report(int reportId, String type, int station, int user, String status, String details, int icon) {
        this.reportId = reportId;
        this.type = type;
        this.station = station;
        this.user = user;
        this.status = status;
        this.details = details;
        this.icon = icon;
    }

    public Report() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStation() {
        return station;
    }

    public void setStation(int station) {
        this.station = station;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
