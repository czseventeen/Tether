package jayxu.com.carassist.MODEL;

import android.graphics.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 1/17/2016.
 */
public class UsefulConstants {
    public static final String ParseAttrNameUsername ="Username";
    public static final String ParseAttrNameEmail ="Email";
    public static final String ParseAttrNameAllStats="AllStats";
    public static final String ParseAttrNameParseUser="ParseUser";

    public static final String ParseClassNameHome="Home";
    public static final String ParseClassNameMyCar="MyCar";
    public static final String ParseClassNameMyStats="MyStats";

    public static final float DefaultInitCode = -999;
    public static final int UnpaintedHexagonColor= Color.GRAY;


    public static final int PageIndex_Home= 0;
    public static final int PageIndex_MyCar= 1;
    public static final int PageIndex_MyStat= 2;

//keys for retrieving/storing data descriptions:
    public static final HashMap<String, Integer> Description_Stringmapping =new HashMap<String, Integer>(){{
            put("MoneySavedByDrivingElectric",R.string.MoneySavedByDrivingElectric);
            put("HealthSummary",R.string.HealthSummary);
            put("ParseKey_UserGPS_X",R.string.UserGPS_X);
            put("ParseKey_UserGPS_Y",R.string.UserGPS_Y);
            put("ParseKey_CarGPS_X",R.string.CarGPS_X);
            put("ParseKey_CarGPS_Y",R.string.CarGPS_Y);
            put("FuelEconomy",R.string.FuelEconomy);

            put("BatteryLeft", R.string.BatteryLeft);
            put("BatteryTemp", R.string.BatteryTemp);
            put("BatteryCapacity", R.string.BatteryCapacity);
            put("Brand", R.string.Brand);
            put("SeatingCapacity", R.string.SeatingCapacity);
            put("HorsePower", R.string.HorsePower);
            put("MaxRPM", R.string.MaxRPM);
            put("TopSpeed", R.string.TopSpeed);
            put("ZeroToSixtyTime", R.string.ZeroToSixtyTime);
            put("EstimateValue", R.string.EstimateValue);
            put("TotalMilesDriven", R.string.TotalMilesDriven);

             put("AverageTimeSpentInTrafficDaily",R.string.AverageTimeSpentInTrafficDaily);
             put("DrivingScore",    R.string.DrivingScore);
             put("AverageDailyDriveTime",R.string.AverageDailyDriveTime);
             put("AverageDailyMilesDriven",R.string.AverageDailyMilesDriven);
             put("MilesDrivenThisCharge",R.string.MilesDrivenThisCharge);
             put("TimeDrivenThisCharge", R.string.TimeDrivenThisCharge);
             put("AverageMilesDrivenPerAH",R.string.AverageMilesDrivenPerAH);
             put("AverageDrivingTimePerAH",R.string.AverageDrivingTimePerAH);
             put("MaxMilesDrivenPerAH",R.string.MaxMilesDrivenPerAH);
             put("FastestSpeedDriven", R.string.FastestSpeedDriven);
             put("NumOfSuddenBreak",R.string.NumOfSuddenBreak);
             put("NumOfSuddenAccelerate",R.string.NumOfSuddenAccelerate);
             put("NumOfSuddenTurn",R.string.NumOfSuddenTurn);
             put("NumOfAccident",R.string.NumOfAccident);
             put("AverageCostPerMile",R.string.AverageCostPerMile);
             put("TotalDrivingTime", R.string.TotalDrivingTime);
             put("AverageSpeedDriven",R.string.AverageSpeedDriven);
             put("TotalTimeSpentInTraffic",R.string.TotalTimeSpentInTraffic);
    }};


//Home keys for retrieving/storing
public static final String ParseKey_UserGPS_X ="UserGPS_X";
    public static final String ParseKey_UserGPS_Y ="UserGPS_Y";
    public static final String ParseKey_CarGPS_X ="CarGPS_X";
    public static final String ParseKey_CarGPS_Y ="CarGPS_Y";
    public static final String ParseKey_MoneySavedByDrivingElectric ="MoneySavedByDrivingElectric";
    public static final String ParseKey_FuelEconmony ="FuelEconomy";
    public static final String ParseKey_Health ="HealthSummary";






    // The map that contains the Mapping for Units based on the KeyString.
   public static final HashMap<String, Integer> UnitMapping=new HashMap<String, Integer>(){{
    //Home units
        put("MoneySavedByDrivingElectric",R.string.Unit_MoneySavedByDrivingElectric);
        put("HealthSummary",R.string.Unit_HealthSummary);
        put("ParseKey_UserGPS_X",R.string.Unit_UserGPS_X);
        put("ParseKey_UserGPS_Y",R.string.Unit_UserGPS_Y);
        put("ParseKey_CarGPS_X",R.string.Unit_CarGPS_X);
        put("ParseKey_CarGPS_Y",R.string.Unit_CarGPS_Y);
        put("FuelEconomy", R.string.Unit_FuelEconomy);

    //mycar units
        put("BatteryLeft", R.string.Unit_BatteryLeft);
        put("BatteryTemp", R.string.Unit_BatteryTemp);
        put("BatteryCapacity", R.string.Unit_BatteryCapacity);
        put("Brand", R.string.Unit_Brand);
        put("SeatingCapacity", R.string.Unit_SeatingCapacity);
        put("HorsePower", R.string.Unit_HorsePower);
        put("MaxRPM", R.string.Unit_MaxRPM);
        put("TopSpeed", R.string.Unit_TopSpeed);
        put("ZeroToSixtyTime", R.string.Unit_ZeroToSixtyTime);
        put("EstimateValue", R.string.Unit_EstimateValue);
        put("TotalMilesDriven", R.string.Unit_TotalMilesDriven);
    //mystat units
        put("AverageTimeSpentInTrafficDaily",R.string.Unit_AverageTimeSpentInTrafficDaily);
        put("DrivingScore",    R.string.Unit_DrivingScore);
        put("AverageDailyDriveTime",R.string.Unit_AverageDailyDriveTime);
        put("AverageDailyMilesDriven",R.string.Unit_AverageDailyMilesDriven);
        put("MilesDrivenThisCharge",R.string.Unit_MilesDrivenThisCharge);
        put("TimeDrivenThisCharge", R.string.Unit_TimeDrivenThisCharge);
        put("AverageMilesDrivenPerAH",R.string.Unit_AverageMilesDrivenPerAH);
        put("AverageDrivingTimePerAH",R.string.Unit_AverageDrivingTimePerAH);
        put("MaxMilesDrivenPerAH",R.string.Unit_MaxMilesDrivenPerAH);
        put("FastestSpeedDriven", R.string.Unit_FastestSpeedDriven);
        put("NumOfSuddenBreak",R.string.Unit_NumOfSuddenBreak);
        put("NumOfSuddenAccelerate",R.string.Unit_NumOfSuddenAccelerate);
        put("NumOfSuddenTurn",R.string.Unit_NumOfSuddenTurn);
        put("NumOfAccident",R.string.Unit_NumOfAccident);
        put("AverageCostPerMile",R.string.Unit_AverageCostPerMile);
        put("TotalDrivingTime", R.string.Unit_TotalDrivingTime);
        put("AverageSpeedDriven",R.string.Unit_AverageSpeedDriven);
        put("TotalTimeSpentInTraffic",R.string.Unit_TotalTimeSpentInTraffic);

    }};

}
