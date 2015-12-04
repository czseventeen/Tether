package jayxu.com.carassist.MODEL;

import java.util.Random;

/**
 * Created by Yuchen on 11/29/2015.
 * This Class Contains all the Stats are going to be displayed in the MyStats Page
 */
public class MyStats {
    private int DrivingScore;
    private double MilesDrivenSinceLastCharge;
    private double AverageMilesDrivenPerCharge;
    private double MaxMilesDrivenPerCharge;
    private double FuelEconomy;
    private double FastestSpeedDriven;
    private int NumOfSuddenBreak;
    private int NumOfSuddenAccelerate;
    private int NumOfSuddenTurn;
    private int NumOfAccident;
    private double AverageSpeedDriven;
    private double DailyAverageDriveTime;
    private double AverageDrivingTimePerCharge;
    private double DailyAverageMilesDriven;
    private double MyAverageCostPerMile;
    private double TotalDrivingTime;
    private double TotalDrivingTimeThisCharge;
    private double TotalTimeSpentInTraffic;
    private double TotalMilesDriven;
    private double TotalMilesDrivenThisCharge;

public MyStats(){
        DrivingScore = 0;
        MilesDrivenSinceLastCharge = 0;
        AverageMilesDrivenPerCharge = 0;
        MaxMilesDrivenPerCharge = 0;
        FuelEconomy = 0;
        FastestSpeedDriven = 0;
        NumOfSuddenBreak = 0;
        NumOfSuddenAccelerate = 0;
        NumOfSuddenTurn = 0;
        NumOfAccident = 0;
        AverageSpeedDriven = 0;
        DailyAverageDriveTime = 0;
        AverageDrivingTimePerCharge = 0;
        DailyAverageMilesDriven = 0;
        MyAverageCostPerMile = 0;
        TotalDrivingTime = 0;
        TotalDrivingTimeThisCharge = 0;
        TotalTimeSpentInTraffic = 0;
        TotalMilesDriven = 0;
        TotalMilesDrivenThisCharge = 0;
    }

    public MyStats(int i){
        Random r= new Random();
        DrivingScore = r.nextInt(100);
        MilesDrivenSinceLastCharge = r.nextInt(500);
        AverageMilesDrivenPerCharge =r.nextInt(500);
        MaxMilesDrivenPerCharge = r.nextInt(500);
        FuelEconomy = r.nextInt(500);
        FastestSpeedDriven = r.nextInt(200);
        NumOfSuddenBreak = r.nextInt(100);
        NumOfSuddenAccelerate = r.nextInt(100);
        NumOfSuddenTurn = r.nextInt(100);
        NumOfAccident = r.nextInt(20);
        AverageSpeedDriven = r.nextInt(100);
        DailyAverageDriveTime = r.nextInt(180);
        AverageDrivingTimePerCharge = r.nextInt(100);
        DailyAverageMilesDriven = r.nextInt(500);
        MyAverageCostPerMile = r.nextInt(10);
        TotalDrivingTime = r.nextInt(500000);
        TotalDrivingTimeThisCharge = r.nextInt(500);
        TotalTimeSpentInTraffic = r.nextInt(200);
        TotalMilesDriven = r.nextInt(200000);
        TotalMilesDrivenThisCharge = r.nextInt(500);

    }

    public int getDrivingScore() {
        return DrivingScore;
    }

    public void setDrivingScore(int drivingScore) {
        DrivingScore = drivingScore;
    }

    public double getMilesDrivenSinceLastCharge() {
        return MilesDrivenSinceLastCharge;
    }

    public void setMilesDrivenSinceLastCharge(double milesDrivenSinceLastCharge) {
        MilesDrivenSinceLastCharge = milesDrivenSinceLastCharge;
    }

    public double getAverageMilesDrivenPerCharge() {
        return AverageMilesDrivenPerCharge;
    }

    public void setAverageMilesDrivenPerCharge(double averageMilesDrivenPerCharge) {
        AverageMilesDrivenPerCharge = averageMilesDrivenPerCharge;
    }

    public double getMaxMilesDrivenPerCharge() {
        return MaxMilesDrivenPerCharge;
    }

    public void setMaxMilesDrivenPerCharge(double maxMilesDrivenPerCharge) {
        MaxMilesDrivenPerCharge = maxMilesDrivenPerCharge;
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

    public double getDailyAverageDriveTime() {
        return DailyAverageDriveTime;
    }

    public void setDailyAverageDriveTime(double dailyAverageDriveTime) {
        DailyAverageDriveTime = dailyAverageDriveTime;
    }

    public double getAverageDrivingTimePerCharge() {
        return AverageDrivingTimePerCharge;
    }

    public void setAverageDrivingTimePerCharge(double averageDrivingTimePerCharge) {
        AverageDrivingTimePerCharge = averageDrivingTimePerCharge;
    }

    public double getDailyAverageMilesDriven() {
        return DailyAverageMilesDriven;
    }

    public void setDailyAverageMilesDriven(double dailyAverageMilesDriven) {
        DailyAverageMilesDriven = dailyAverageMilesDriven;
    }

    public double getMyAverageCostPerMile() {
        return MyAverageCostPerMile;
    }

    public void setMyAverageCostPerMile(double myAverageCostPerMile) {
        MyAverageCostPerMile = myAverageCostPerMile;
    }

    public double getTotalDrivingTime() {
        return TotalDrivingTime;
    }

    public void setTotalDrivingTime(double totalDrivingTime) {
        TotalDrivingTime = totalDrivingTime;
    }

    public double getTotalDrivingTimeThisCharge() {
        return TotalDrivingTimeThisCharge;
    }

    public void setTotalDrivingTimeThisCharge(double totalDrivingTimeThisCharge) {
        TotalDrivingTimeThisCharge = totalDrivingTimeThisCharge;
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

    public double getTotalMilesDrivenThisCharge() {
        return TotalMilesDrivenThisCharge;
    }

    public void setTotalMilesDrivenThisCharge(double totalMilesDrivenThisCharge) {
        TotalMilesDrivenThisCharge = totalMilesDrivenThisCharge;
    }


}
