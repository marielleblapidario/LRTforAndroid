package edu.mobidev.barrettokalingolapidario.newtestingproject;

/**
 * Created by mariachristinadalekalingo on 10/8/15.
 */
public class Station {

    static final String TABLE_NAME = "station";
    static final String COLUMN_STATION_NAME = "stationName";
    static final String COLUMN_STATION_CITY = "stationCity";

    private String stationName;
    private String stationCity;
    private int icon;

    public Station(){}

    public Station(String stationName, String stationCity, int icon) {
        this.stationName = stationName;
        this.stationCity = stationCity;
        this.icon = icon;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public String getStationCity() {
        return stationCity;
    }

    public void setStationCity(String stationCity) {
        this.stationCity = stationCity;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

}
