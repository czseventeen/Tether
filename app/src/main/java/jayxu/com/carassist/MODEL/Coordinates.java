package app.my_car_assist.model;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to store GPS coordinates info
 */
public class Coordinates {
    private double latitude;
    private double longitute;

    public Coordinates(){
        this.setLatitude(0.0);
        this.setLongitute(0.0);
    }
    public Coordinates(double lat, double lon) {
        this.setLatitude(lat);
        this.setLongitute(lon);
    }
    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitute() {
        return longitute;
    }

    public void setLongitute(double longitute) {
        this.longitute = longitute;
    }
}
