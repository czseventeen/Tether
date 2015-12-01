package app.my_car_assist.model;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to display stats on the Home page
 */
public class Home {


    private double MoneySavedByDrivingElectric;
    private boolean CarMovingWhileUserAway;
    private Coordinates UserGPS;
    private Coordinates CarGPS;
    private String HealthSummary;

    public double getMoneySavedByDrivingElectric() {
        return MoneySavedByDrivingElectric;
    }

    public void setMoneySavedByDrivingElectric(double moneySavedByDrivingElectric) {
        MoneySavedByDrivingElectric = moneySavedByDrivingElectric;
    }

    public boolean isCarMovingWhileUserAway() {
        return CarMovingWhileUserAway;
    }

    public void setCarMovingWhileUserAway(boolean carMovingWhileUserAway) {
        CarMovingWhileUserAway = carMovingWhileUserAway;
    }

    public Coordinates getUserGPS() {
        return UserGPS;
    }

    public void setUserGPS(Coordinates userGPS) {
        UserGPS = userGPS;
    }

    public Coordinates getCarGPS() {
        return CarGPS;
    }

    public void setCarGPS(Coordinates carGPS) {
        CarGPS = carGPS;
    }

}
