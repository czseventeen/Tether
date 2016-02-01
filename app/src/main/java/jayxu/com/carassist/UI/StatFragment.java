package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import jayxu.com.carassist.ADAPTER.MyStatAdapter;
import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class StatFragment extends Fragment {
    private static final String TAG =StatFragment.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static ScoreHexagonView topImage;
    private static View rootView;
    private static float drivingScore=0;


    public static StatFragment newInstance(int sectionNumber) {
        StatFragment fragment = new StatFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;

    }

    public StatFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        /*
        * Use the following section of code to grab the current user's Mystat page data
        */
        ParseUser user=ParseUser.getCurrentUser();
        JSONObject mystat_results;
        ArrayList<ItemData> itemData_list=new ArrayList<>();

        try {
            mystat_results=user.getJSONObject(UsefulConstants.ParseAttrNameAllStats).getJSONObject(UsefulConstants.ParseClassNameMyStats);

        if(mystat_results!=null){
            Iterator<String> iterator=mystat_results.keys();
            while(iterator.hasNext()){
                ItemData temp_item=new ItemData();

                String key=iterator.next();

                temp_item.setDescriptionKey(key);
                String value=mystat_results.getString(key);
                try {
                    //need to round to the nearest 100th.
                    double roundedValue= Double.valueOf(mystat_results.getString(key));
                    roundedValue = Math.round(roundedValue * 100.0) / 100.0;
                    value=roundedValue+"";
                }catch (NumberFormatException e){
                    Log.d(TAG, "This "+key+"wasn't a double!");
                }
                temp_item.setValue(value);
                if(UsefulConstants.Description_Stringmapping.get(key).equals(R.string.DrivingScore)){
                    drivingScore=Float.valueOf(temp_item.getValue());
                    continue;
                }
                itemData_list.add(temp_item);

            }

        }
        }catch (JSONException e){
            e.printStackTrace();
        }catch (NullPointerException e){
            e.printStackTrace();
        }catch (IllegalStateException e){
            Log.e(TAG, "The particular attribute might not exist.");
            e.printStackTrace();
        }



        //Below were the old way of parsing the String data returned from Parse.com
//        String results=user.getString("MYSTAT_DATA");
//        results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
//        String[] array_result=results.split(",");
//        //Converting the results into a Array of ItemData
//        ArrayList<ItemData> dataArray=new ArrayList<ItemData>();
//        ArrayList<ItemData> dataArray2=new ArrayList<ItemData>();
//
//        for(int i=0; i<array_result.length;i++)
//        {
//            String [] temp=array_result[i].split(":");
//            ItemData tempdata=new ItemData();
//            tempdata.setDescription(temp[0]);
//            tempdata.setValue(temp[1]);
//            if(temp[0].contains("Average")) {
//                dataArray.add(tempdata);
//            }
//            else if(temp[0].contains("Total")){
//                dataArray2.add(tempdata);
//            }
//
//        }



        rootView = inflater.inflate(R.layout.fragment_stat, container, false);
        MyStatAdapter adapter=new MyStatAdapter(itemData_list);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.Stat_recycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        //01/21/2016 removed driving score image placeholder
//       Modify the TopView Progress Circle based on Driving Score
//        DonutProgress dount=(DonutProgress)rootView.findViewById(R.id.donut_progress);
//        dount.setProgress((int) drivingScore);
//        if(drivingScore >= 70){
//            dount.setFinishedStrokeColor(getResources().getColor(R.color.apptheme_color));
//        }


//        MyStatAdapter adapter2=new MyStatAdapter(dataArray2);
//        RecyclerView recyclerView2=(RecyclerView)rootView.findViewById(R.id.stat_recycleView2);
//        recyclerView2.setAdapter(adapter2);
//        recyclerView2.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
//        recyclerView2.setHasFixedSize(true);



/*Stop using ListView        ListView listview=(ListView)rootView.findViewById(R.id.stat_list);
        listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));*/

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        topImage= (ScoreHexagonView) rootView.findViewById(R.id.mystat_TopImage);
        topImage.setPercentage(drivingScore);
//        topImage.setPercentage(100);
        int drivesocre=(int)drivingScore;
        TextView percentageText = (TextView)rootView.findViewById(R.id.mystat_ScorePercent);
        percentageText.setText(""+drivesocre);
    }

    public static View getMyCarView(){
        return rootView;
    }
}
