package jayxu.com.carassist.MODEL;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Random;

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



    private static final int MoneySave_max=10000;
    private static final String[] HealthStringArray = {"Is Healthy", "Need Engine Check", "Need Oil Change","Tire Need More Air"};

    /*
    These are placeholder IDs for the array Index.
     */
    private static final int jMoneySavedByDrivingElectric =0;
    private static final int jUserGPS=1;
    private static final int jCarGPS=2;
    private static final int jHealth=3;



    public Home(){

    }

    /*
    This constructor is used to assign random values to each variable.
     */
    public Home(int i){
        if(i==-1){
            Random r= new Random();
            this.setMoneySavedByDrivingElectric(r.nextInt(MoneySave_max));
            this.setCarGPS(new Coordinates(-1));
            this.setUserGPS(new Coordinates(-1));
            this.setHealthSummary(HealthStringArray[r.nextInt(HealthStringArray.length)]);
        }

    }
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

    public String getHealthSummary() {
        return HealthSummary;
    }

    public void setHealthSummary(String healthSummary) {
        HealthSummary = healthSummary;
    }

    @Override
    public String toString(){
        return "The car is located at "+this.getCarGPS().toString()+", you are located at "+this.getUserGPS().toString()+", the money you have saved by using electric is "
                +this.getMoneySavedByDrivingElectric()+", and you car is "+HealthSummary+".";
    }

    public JSONArray generateJSONArray() throws JSONException {
        JSONArray myArray=new JSONArray();
        myArray.put(jMoneySavedByDrivingElectric,MoneySavedByDrivingElectric);
        //myArray.put(jCarGPS,CarGPS);
        //myArray.put(jUserGPS,UserGPS);
        myArray.put(jHealth,HealthSummary);

        return myArray;
    }

}
