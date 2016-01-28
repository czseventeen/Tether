package jayxu.com.carassist.UI;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.parse.ParseUser;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import jayxu.com.carassist.ADAPTER.MyStatAdapter;
import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class MyCarFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static BatteryHexagonView topImage;
    private static View rootView;

    private static final String TAG = MyCarFragment.class.getSimpleName();

    public static MyCarFragment newInstance(int sectionNumber) {
        MyCarFragment fragment = new MyCarFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MyCarFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        ParseUser user=ParseUser.getCurrentUser();
        JSONObject mycar_results = null;
        int batteryLeft=100;
        ArrayList<ItemData> itemData_list=new ArrayList<>();
//        try {
//            mycar_results = user.getJSONObject(getString(R.string.JSON_KEY)).getJSONObject(getString(R.string.MyCar_JSON_KEY));
//
//            if(mycar_results!=null){
//                Iterator<String> iterator=mycar_results.keys();
//                while(iterator.hasNext()){
//                    ItemData temp_item=new ItemData();
//                    String key=iterator.next();
//                    temp_item.setDescription(key);
//                    temp_item.setValue(mycar_results.getString(key));
//                    if(key.equals(getString(R.string.BatteryLeft))){
//                        batteryLeft=Math.round(Float.valueOf(temp_item.getValue()));
//                        continue;
//                    }
//                    itemData_list.add(temp_item);
//                }
//
//            }
//        }catch (JSONException e){
//            e.printStackTrace();
//        }catch (NullPointerException e){
//            e.printStackTrace();
//        }

         rootView = inflater.inflate(R.layout.fragment_mycar, container, false);
        //ImageView TopImage=(ImageView)rootView.findViewById(R.id.Top_imageView);
        //TopImage.setImageResource(R.drawable.teslar);

        MyStatAdapter adapter=new MyStatAdapter(itemData_list);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.mycar_recycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

//        HexgonView hexagon=new HexgonView(getActivity(), 39, 100 , 100);
//        Drawable drawable = getResources().getDrawable(R.drawable.ab_solid_future);
//        Bitmap b = HexgonView.getHexagonShape(100, 200, 200);


//        topImage.setImageBitmap(b);
        //1/21/2016 removed battery circle place holder
//        NumberProgressBar batteryBar=(NumberProgressBar)rootView.findViewById(R.id.battery_bar);
//        batteryBar.setProgress(batteryLeft);

/*   Stop using ListView
     ListView listview=(ListView)rootView.findViewById(R.id.mycar_list);
        listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));*/


        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "~~~~~On resume is called");
        topImage= (BatteryHexagonView) rootView.findViewById(R.id.mycar_TopImage);
        topImage.setPercentage(13);

        TextView percentageText = (TextView)rootView.findViewById(R.id.mycar_batteryPercent);
        percentageText.setText("13%");
    }

    public static void startAnimation(){
        topImage.setPercentage(1);
        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(topImage, "Percentage", 34);
        alphaAnimator.setDuration(3000);
        alphaAnimator.start();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "~~~~~On Stop is called");

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d(TAG, "~~~~~On Pause is called");

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d(TAG, "~~~~~On Attach is called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "~~~~~On Destroy is called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d(TAG, "~~~~~On Detach is called");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG, "~~~~~On Start is called");
    }

    public static View getMyCarView(){
        return rootView;
    }
}
