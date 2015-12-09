package jayxu.com.carassist.MODEL;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

import jayxu.com.carassist.R;


/**
 * Created by Yuchen on 11/29/2015.
 * This Class Contains all the Stats are going to be displayed in the MyStats Page
 */
public class MyStats  {

    private double MilesDrivenThisCharge;
    private double AverageMilesDrivenPerAH;
    private double MaxMilesDrivenPerAH;
    private double FuelEconomy;

    private int DrivingScore;
    private int NumOfSuddenBreak;
    private int NumOfSuddenAccelerate;
    private int NumOfSuddenTurn;
    private int NumOfAccident;
    private double FastestSpeedDriven;

    private double AverageSpeedDriven;
    private double AverageDailyDriveTime;
    private double AverageDrivingTimePerAH;
    private double AverageDailyMilesDriven;
    private double AverageCostPerMile;
    private double AverageTimeSpentInTrafficDaily;
    private double TotalDrivingTime;
    private double TimeDrivenThisCharge;
    private double TotalTimeSpentInTraffic;
    private double TotalMilesDriven;

    public MyStats(){
        DrivingScore = 0;

        AverageMilesDrivenPerAH = 0;
        AverageSpeedDriven = 0;
        AverageDailyDriveTime = 0;
        AverageDrivingTimePerAH = 0;
        AverageDailyMilesDriven = 0;
        AverageCostPerMile = 0;

        MilesDrivenThisCharge = 0;
        MaxMilesDrivenPerAH = 0;
        FuelEconomy = 0;
        FastestSpeedDriven = 0;

        NumOfSuddenBreak = 0;
        NumOfSuddenAccelerate = 0;
        NumOfSuddenTurn = 0;
        NumOfAccident = 0;

        TotalDrivingTime = 0;
        TimeDrivenThisCharge = 0;
        TotalTimeSpentInTraffic = 0;
        TotalMilesDriven = 0;
    }

    public MyStats(int i){
        Random r= new Random();
        DrivingScore = r.nextInt(100);
        MilesDrivenThisCharge = r.nextInt(500);
        AverageMilesDrivenPerAH =r.nextInt(500);
        MaxMilesDrivenPerAH = r.nextInt(500);
        FuelEconomy = r.nextInt(500);
        FastestSpeedDriven = r.nextInt(200);
        NumOfSuddenBreak = r.nextInt(100);
        NumOfSuddenAccelerate = r.nextInt(100);
        NumOfSuddenTurn = r.nextInt(100);
        NumOfAccident = r.nextInt(20);
        AverageSpeedDriven = r.nextInt(100);
        AverageDailyDriveTime = r.nextInt(180);
        AverageDrivingTimePerAH = r.nextInt(100);
        AverageDailyMilesDriven = r.nextInt(500);
        AverageCostPerMile = r.nextInt(10);
        AverageTimeSpentInTrafficDaily=r.nextInt(200);
        TotalDrivingTime = r.nextInt(500000);
        TimeDrivenThisCharge = r.nextInt(500);
        TotalTimeSpentInTraffic = r.nextInt(10000);
        TotalMilesDriven = r.nextInt(200000);
    }


    public JSONObject getJSON(Context context) throws JSONException{
        JSONObject jsonobj=new JSONObject();
        jsonobj.put(context.getString(R.string.AverageTimeSpentInTrafficDaily),this.getAverageTimeSpentInTrafficDaily());//+" min"
        jsonobj.put(context.getString(R.string.FuelEconomy),this.getFuelEconomy()); //+" MPG"
        jsonobj.put(context.getString(R.string.DrivingScore),this.getDrivingScore());
        jsonobj.put(context.getString(R.string.AverageDailyDriveTime),this.getAverageDailyDriveTime());
        jsonobj.put(context.getString(R.string.AverageDailyMilesDriven), this.getAverageDailyMilesDriven());
        jsonobj.put(context.getString(R.string.MilesDrivenThisCharge), this.getMilesDrivenThisCharge());
        jsonobj.put(context.getString(R.string.TimeDrivenThisCharge), this.getTimeDrivenThisCharge());
        jsonobj.put(context.getString(R.string.AverageMilesDrivenPerAH), this.getAverageDrivingTimePerAH());
        jsonobj.put(context.getString(R.string.AverageDrivingTimePerAH), this.getAverageDrivingTimePerAH());
        jsonobj.put(context.getString(R.string.MaxMilesDrivenPerAH), this.getMaxMilesDrivenPerAH());
        jsonobj.put(context.getString(R.string.FastestSpeedDriven), this.getFastestSpeedDriven());
        jsonobj.put(context.getString(R.string.NumOfSuddenBreak), this.getNumOfSuddenBreak());
        jsonobj.put(context.getString(R.string.NumOfSuddenAccelerate), this.getNumOfSuddenAccelerate());
        jsonobj.put(context.getString(R.string.NumOfSuddenTurn), this.getNumOfSuddenTurn());
        jsonobj.put(context.getString(R.string.NumOfAccident), this.getNumOfAccident());
        jsonobj.put(context.getString(R.string.AverageCostPerMile), this.getAverageCostPerMile());
        jsonobj.put(context.getString(R.string.TotalDrivingTime), this.getTotalDrivingTime());
        jsonobj.put(context.getString(R.string.TotalMilesDriven), this.getTotalMilesDriven());
        jsonobj.put(context.getString(R.string.AverageSpeedDriven), this.getAverageSpeedDriven());
        jsonobj.put(context.getString(R.string.TotalTimeSpentInTraffic), this.getTotalTimeSpentInTraffic());



        return jsonobj;
    }


    public double getAverageTimeSpentInTrafficDaily() {
        return AverageTimeSpentInTrafficDaily;
    }

    public void setAverageTimeSpentInTrafficDaily(double averageTimeSpentInTrafficDaily) {
        AverageTimeSpentInTrafficDaily = averageTimeSpentInTrafficDaily;
    }

    public int getDrivingScore() {
        return DrivingScore;
    }

    public void setDrivingScore(int drivingScore) {
        DrivingScore = drivingScore;
    }

    public double getMilesDrivenThisCharge() {
        return MilesDrivenThisCharge;
    }

    public void setMilesDrivenThisCharge(double milesDrivenThisCharge) {
        MilesDrivenThisCharge = milesDrivenThisCharge;
    }

    public double getAverageMilesDrivenPerAH() {
        return AverageMilesDrivenPerAH;
    }

    public void setAverageMilesDrivenPerAH(double averageMilesDrivenPerAH) {
        AverageMilesDrivenPerAH = averageMilesDrivenPerAH;
    }

    public double getMaxMilesDrivenPerAH() {
        return MaxMilesDrivenPerAH;
    }

    public void setMaxMilesDrivenPerAH(double maxMilesDrivenPerAH) {
        MaxMilesDrivenPerAH = maxMilesDrivenPerAH;
    }

    public double getFuelEconomy() {
        return FuelEconomy;
    }

    public void setFuelEconomy(double fuelEconomy) {
        FuelEconomy = fuelEconomy;
    }

    public double getFastestSpeedDriven() {
        return FastestSpeedDriven;
    }

    public void setFastestSpeedDriven(double fastestSpeedDriven) {
        FastestSpeedDriven = fastestSpeedDriven;
    }

    public int getNumOfSuddenBreak() {
        return NumOfSuddenBreak;
    }

    public void setNumOfSuddenBreak(int numOfSuddenBreak) {
        NumOfSuddenBreak = numOfSuddenBreak;
    }

    public int getNumOfSuddenAccelerate() {
        return NumOfSuddenAccelerate;
    }

    public void setNumOfSuddenAccelerate(int numOfSuddenAccelerate) {
        NumOfSuddenAccelerate = numOfSuddenAccelerate;
    }

    public int getNumOfSuddenTurn() {
        return NumOfSuddenTurn;
    }

    public void setNumOfSuddenTurn(int numOfSuddenTurn) {
        NumOfSuddenTurn = numOfSuddenTurn;
    }

    public int getNumOfAccident() {
        return NumOfAccident;
    }

    public void setNumOfAccident(int numOfAccident) {
        NumOfAccident = numOfAccident;
    }

    public double getAverageSpeedDriven() {
        return AverageSpeedDriven;
    }

    public void setAverageSpeedDriven(double averageSpeedDriven) {
        AverageSpeedDriven = averageSpeedDriven;
    }

    public double getAverageDailyDriveTime() {
        return AverageDailyDriveTime;
    }

    public void setAverageDailyDriveTime(double averageDailyDriveTime) {
        AverageDailyDriveTime = averageDailyDriveTime;
    }

    public double getAverageDrivingTimePerAH() {
        return AverageDrivingTimePerAH;
    }

    public void setAverageDrivingTimePerAH(double averageDrivingTimePerAH) {
        AverageDrivingTimePerAH = averageDrivingTimePerAH;
    }

    public double getAverageDailyMilesDriven() {
        return AverageDailyMilesDriven;
    }

    public void setAverageDailyMilesDriven(double averageDailyMilesDriven) {
        AverageDailyMilesDriven = averageDailyMilesDriven;
    }

    public double getAverageCostPerMile() {
        return AverageCostPerMile;
    }

    public void setAverageCostPerMile(double averageCostPerMile) {
        AverageCostPerMile = averageCostPerMile;
    }

    public double getTotalDrivingTime() {
        return TotalDrivingTime;
    }

    public void setTotalDrivingTime(double totalDrivingTime) {
        TotalDrivingTime = totalDrivingTime;
    }

    public double getTimeDrivenThisCharge() {
        return TimeDrivenThisCharge;
    }

    public void setTimeDrivenThisCharge(double timeDrivenThisCharge) {
        TimeDrivenThisCharge = timeDrivenThisCharge;
    }

    public double getTotalTimeSpentInTraffic() {
        return TotalTimeSpentInTraffic;
    }

    public void setTotalTimeSpentInTraffic(double totalTimeSpentInTraffic) {
        TotalTimeSpentInTraffic = totalTimeSpentInTraffic;
    }

    public double getTotalMilesDriven() {
        return TotalMilesDriven;
    }

    public void setTotalMilesDriven(double totalMilesDriven) {
        TotalMilesDriven = totalMilesDriven;
    }


}
