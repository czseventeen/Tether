package jayxu.com.carassist.MODEL;

import android.util.Log;

import java.util.Random;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to store GPS coordinates info
 */
public class Coordinates {
    private double latitude;
    private double longitute;
    private static final String TAG=Coordinates.class.getSimpleName();

    private double long_random_max=180;
    private double long_random_min=0;
    private double lati_random_max=90;
    private double lati_random_min=0;
    /*
    This constructor is used to set all parameters to 0
     */
    public Coordinates(){
        this.setLatitude(0.0);
        this.setLongitute(0.0);
    }
    /*
    This constructor will randomly generate a coordinate if the parameter passed is -1
     */
    public Coordinates(int i){
        if(i==-1){
            Random r = new Random();
            double longitute_random=long_random_min+(long_random_max-long_random_min)*r.nextDouble();
            double latitute_random=lati_random_min+(lati_random_max-lati_random_min)*r.nextDouble();
            Log.w(TAG, "The longitute was"+longitute_random+"......And latitute was"+latitute_random);
            this.setLatitude(longitute_random);
            this.setLongitute(latitute_random);
        }

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

    @Override
    public String toString() {
        return ""+this.getLatitude()+","+this.getLongitute();
    }

}
