package jayxu.com.carassist.MODEL;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to display stats on the Home page
 */
public class Home {


    private double MoneySavedByDrivingElectric;
    private boolean CarMovingWhileUserAway;
    private double CarGPS_X;
    private double CarGPS_Y;
    private double UserGPS_X;
    private double UserGPS_Y;
    private String HealthSummary;
    private double FuelEconomy;



    private static final int MoneySave_max=10000;
    private static String[] HealthStringArray = {"Is Healthy", "Need Engine Check","Tire Need More Air"};
    private Context mContext=null;
    public Home(){
        FuelEconomy = 0;
    }

    /*
    This constructor is used to assign random values to each variable.
     */
    public Home(int i, Context context){
        mContext=context;
        if(i==-1){

            Random r= new Random();
            this.setMoneySavedByDrivingElectric(r.nextInt(MoneySave_max));

            this.setUserGPS_X(r.nextDouble()*2+39);
            this.setUserGPS_Y(r.nextDouble()*2+115);
            this.setCarGPS_X(this.getUserGPS_X());
            this.setCarGPS_Y(this.getUserGPS_Y());
            HealthStringArray = new String[] {context.getString(R.string.Healthy), context.getString(R.string.NeedEngineCheck)
                    ,context.getString(R.string.TireFlat)};
            this.setHealthSummary(HealthStringArray[r.nextInt(HealthStringArray.length)]);
            this.setFuelEconomy(r.nextInt(5)+20);

        }

    }

    public JSONObject getJSON(Context context) throws JSONException {
        JSONObject jsonobj=new JSONObject();
        jsonobj.put("MoneySavedByDrivingElectric",this.getMoneySavedByDrivingElectric()); //+"$"
        jsonobj.put("HealthSummary", this.getHealthSummary());
        jsonobj.put("UserGPS_X", this.getUserGPS_X());
        jsonobj.put("UserGPS_Y", this.getUserGPS_Y());
        jsonobj.put("CarGPS_X", this.getCarGPS_X());
        jsonobj.put("CarGPS_Y", this.getCarGPS_Y());
        jsonobj.put("FuelEconomy",this.getFuelEconomy()); //+" MPG"


        return jsonobj;
    }

    public double getCarGPS_X() {
        return CarGPS_X;
    }

    public void setCarGPS_X(double carGPS_X) {
        CarGPS_X = carGPS_X;
    }

    public double getCarGPS_Y() {
        return CarGPS_Y;
    }

    public void setCarGPS_Y(double carGPS_Y) {
        CarGPS_Y = carGPS_Y;
    }

    public double getUserGPS_X() {
        return UserGPS_X;
    }

    public void setUserGPS_X(double userGPS_X) {
        UserGPS_X = userGPS_X;
    }

    public double getUserGPS_Y() {
        return UserGPS_Y;
    }

    public void setUserGPS_Y(double userGPS_Y) {
        UserGPS_Y = userGPS_Y;
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


    public String getHealthSummary() {
        return HealthSummary;
    }

    public void setHealthSummary(String healthSummary) {
        HealthSummary = healthSummary;
    }

    @Override
    public String toString(){
        return "The car is located at "+this.getCarGPS_X()+","+this.getCarGPS_Y()+", the money you have saved by using electric is "
                +this.getMoneySavedByDrivingElectric()+", and you car is "+HealthSummary+".";
    }

    public double getFuelEconomy() {
        return FuelEconomy;
    }

    public void setFuelEconomy(double fuelEconomy) {
        FuelEconomy = fuelEconomy;
    }
/*    public JSONArray generateJSONArray() throws JSONException {
        JSONArray myArray=new JSONArray();
        myArray.put(jMoneySavedByDrivingElectric,ParseKey_MoneySavedByDrivingElectric);
        //myArray.put(jCarGPS,CarGPS);
        //myArray.put(jUserGPS,UserGPS);
        myArray.put(jHealth,HealthSummary);

        return myArray;
    }*/

}
