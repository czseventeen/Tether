package jayxu.com.carassist.MODEL;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to Display Stats on the MyCar page
 */
public class MyCar {
    private double BatteryLeft;
    private double BatteryV;
    private double BatteryI;
    private double BatteryR;
    private double BatteryTemp;
    private double BatteryCapacity;
    private double FuelEconomy;
    private String Brand;
    private int SeatingCapacity;
    private int HorsePower;
    private int MaxRPM;
    private int TopSpeed;
    private double ZeroToSixtyTime;
    private double EstimateValue;
    private double TotalMilesDriven;

    /*
    Below are the JSON index ids.

    private static final int jBatteryLeft=0;
    private static final int jBatteryV=1;
    private static final int jBatteryI=2;
    private static final int jBatteryR=3;
    private static final int jBatteryTemp=4;
    private static final int jBatteryCapacity=5;
    */
    public MyCar(){
        BatteryLeft = 0;
        BatteryV = 0;
        BatteryI = 0;
        BatteryR = 0;
        BatteryTemp = 0;
        BatteryCapacity = 0;
        FuelEconomy = 0;
        Brand = "No Brand";
        SeatingCapacity = 0;
        HorsePower = 0;
        MaxRPM = 0;
        TopSpeed = 0;
        ZeroToSixtyTime = 0;
        EstimateValue = 0;
        TotalMilesDriven = 0;
    }

    public MyCar(int i){
        if(i==-1){
            Random r=new Random();
            BatteryLeft=r.nextDouble()*100;
            BatteryV=r.nextDouble();
            BatteryI=r.nextDouble();
            BatteryR=r.nextDouble();
            BatteryTemp =r.nextDouble()*100;
            BatteryCapacity = r.nextInt(500);
            FuelEconomy = r.nextInt(100);
            Brand = "No Brand";
            SeatingCapacity = r.nextInt(2)+5;
            HorsePower = r.nextInt(500)+200;
            MaxRPM = r.nextInt(3000)+6000;
            TopSpeed = r.nextInt(100)+110;
            ZeroToSixtyTime = r.nextDouble()*6+1.7;
            EstimateValue = r.nextInt(50000)+2000;
            TotalMilesDriven = r.nextInt(200000);
        }

    }

    public JSONObject getJSON(Context context) throws JSONException {
        JSONObject jsonobj=new JSONObject();
        jsonobj.put(context.getString(R.string.BatteryLeft), this.getBatteryLeft()+"%");
        jsonobj.put(context.getString(R.string.BatteryTemp), this.getBatteryTemp()+"C");

        return jsonobj;
    }

    public double getBatteryLeft() {
        return BatteryLeft;
    }

    public void setBatteryLeft(double batteryLeft) {
        BatteryLeft = batteryLeft;
    }

    public double getBatteryV() {
        return BatteryV;
    }

    public void setBatteryV(double batteryV) {
        BatteryV = batteryV;
    }

    public double getBatteryI() {
        return BatteryI;
    }

    public void setBatteryI(double batteryI) {
        BatteryI = batteryI;
    }

    public double getBatteryR() {
        return BatteryR;
    }

    public void setBatteryR(double batteryR) {
        BatteryR = batteryR;
    }

    public double getBatteryTemp() {
        return BatteryTemp;
    }

    public void setBatteryTemp(double batteryTemp) {
        BatteryTemp = batteryTemp;
    }

    public double getBatteryCapacity() {
        return BatteryCapacity;
    }

    public void setBatteryCapacity(double batteryCapacity) {
        BatteryCapacity = batteryCapacity;
    }

    public double getFuelEconomy() {
        return FuelEconomy;
    }

    public void setFuelEconomy(double fuelEconomy) {
        FuelEconomy = fuelEconomy;
    }

    public String getBrand() {
        return Brand;
    }

    public void setBrand(String brand) {
        Brand = brand;
    }

    public int getSeatingCapacity() {
        return SeatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        SeatingCapacity = seatingCapacity;
    }

    public int getHorsePower() {
        return HorsePower;
    }

    public void setHorsePower(int horsePower) {
        HorsePower = horsePower;
    }

    public int getMaxRPM() {
        return MaxRPM;
    }

    public void setMaxRPM(int maxRPM) {
        MaxRPM = maxRPM;
    }

    public int getTopSpeed() {
        return TopSpeed;
    }

    public void setTopSpeed(int topSpeed) {
        TopSpeed = topSpeed;
    }

    public double getZeroToSixtyTime() {
        return ZeroToSixtyTime;
    }

    public void setZeroToSixtyTime(double zeroToSixtyTime) {
        ZeroToSixtyTime = zeroToSixtyTime;
    }

    public double getEstimateValue() {
        return EstimateValue;
    }

    public void setEstimateValue(double estimateValue) {
        EstimateValue = estimateValue;
    }

    public double getTotalMilesDriven() {
        return TotalMilesDriven;
    }

    public void setTotalMilesDriven(double totalMilesDriven) {
        TotalMilesDriven = totalMilesDriven;
    }

/*    public JSONArray generateJSONArray() throws JSONException {
        JSONArray myArray=new JSONArray();
        myArray.put(jBatteryLeft,BatteryLeft);
        myArray.put(jBatteryV,BatteryV);
        myArray.put(jBatteryI,BatteryI);
        myArray.put(jBatteryR,BatteryR);

        return myArray;
    }*/

    @Override
    public String toString(){
        return "My Car has "+BatteryLeft+"% battery left";
    }
}
