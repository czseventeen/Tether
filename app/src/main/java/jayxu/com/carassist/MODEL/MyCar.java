package app.my_car_assist.model;

/**
 * Created by Yuchen on 11/29/2015.
 * This class is used to Display Stats on the MyCar page
 */
public class MyCar {
    private Coordinates CarGPS;
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

    public Coordinates getCarGPS() {
        return CarGPS;
    }

    public void setCarGPS(Coordinates carGPS) {
        CarGPS = carGPS;
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
}
