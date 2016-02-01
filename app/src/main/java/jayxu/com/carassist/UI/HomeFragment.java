package jayxu.com.carassist.UI;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class HomeFragment extends Fragment {
        private static final String TAG= HomeFragment.class.getSimpleName();
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static HomeHexagonView topImage;
        private static final String ARG_SECTION_NUMBER = "section_number";
        private JSONObject home_results=null;
         private TextView dialogTitle;
         private TextView dialogDesc;

        private static View rootView;
    /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static HomeFragment newInstance(int sectionNumber) {
            HomeFragment fragment = new HomeFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public HomeFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //Everytime on createView, populate the list with data.
            ParseUser user=ParseUser.getCurrentUser();

            ArrayList<ItemData> itemData_list=new ArrayList<>();
            String CarHealth="";
            try {
                home_results = user.getJSONObject(UsefulConstants.ParseAttrNameAllStats).getJSONObject(UsefulConstants.ParseClassNameHome);
            }catch (JSONException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
/*            String results=user.getString("HOME_DATA");
            results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
            String[] array_result=results.split(",");*/

            rootView = inflater.inflate(R.layout.fragment_home, container, false);


            final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            //Set onclick listener for the 4 icons:
//            View DialogView=inflater.inflate(R.layout.dialog_details,null);
//            alert.setView(DialogView);
            alert.setPositiveButton(R.string.Home_dialog_gotit, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
//                    DialogView.
                }
            });
//            dialogTitle=(TextView) DialogView.findViewById(R.id.Dialog_Title);
//            dialogDesc=(TextView) DialogView.findViewById(R.id.Dialog_Description);

            ImageView chargeStation_Img=(ImageView)rootView.findViewById(R.id.home_chargeStation);
            chargeStation_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), MapActivity.class);
                    startActivity(intent);
                }
            });

            ImageView Wallet_Img=(ImageView)rootView.findViewById(R.id.home_money);
            Wallet_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String moneySave="0";
                    if(home_results!=null){
                        try {

                            moneySave=home_results.getString(UsefulConstants.ParseKey_MoneySavedByDrivingElectric)+""+getString(UsefulConstants.UnitMapping.get(UsefulConstants.ParseKey_MoneySavedByDrivingElectric));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        alert.setTitle(getString(R.string.Home_dialog_MoneyTitle));
                        alert.setMessage(moneySave);
                    }
                    alert.show();
                }
            });


            ImageView MPG_Img=(ImageView)rootView.findViewById(R.id.home_mpg);
            MPG_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String MPG_Equ="0";
                    if(home_results!=null){
                        try {
                            MPG_Equ=home_results.getString(UsefulConstants.ParseKey_FuelEconmony)+""+getString(UsefulConstants.UnitMapping.get(UsefulConstants.ParseKey_FuelEconmony));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        alert.setTitle(getString(R.string.Home_dialog_MPGTitle));
                        alert.setMessage(MPG_Equ);
                    }
                    alert.show();
                }
            });

            ImageView Health_Img=(ImageView)rootView.findViewById(R.id.home_health);
            Health_Img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Health_info="0";
                    if(home_results!=null){
                        try {
                            Health_info=home_results.getString(UsefulConstants.ParseKey_Health)+""+getString(UsefulConstants.UnitMapping.get(UsefulConstants.ParseKey_Health));
                        }catch (JSONException e){
                            e.printStackTrace();
                        }catch (NullPointerException e){
                            e.printStackTrace();
                        }
                        alert.setTitle(getString(R.string.Home_dialog_HealthTitle));
                        alert.setMessage(Health_info);
                    }
                    alert.show();
                }
            });

//Removing recyclerView on 1/18/2016 for new layout design
//
//
//
//            MyStatAdapter adapter=new MyStatAdapter(itemData_list);
//
//            RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.home_recycleView);
//            recyclerView.setAdapter(adapter);
//            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
//            recyclerView.setHasFixedSize(true);
            /*
            Removed on 01/18/2016 to use new layout
            Populating the Car health at Top

             */
//            TextView homeText=(TextView)rootView.findViewById(R.id.home_textView);
//            homeText.setText(getString(R.string.YourCarIs)+" "+CarHealth);

            /*
            Removed on 01/18/2016
            Changing Car health Background base on Health
             */
//            FrameLayout health_background=(FrameLayout)rootView.findViewById(R.id.home_TopImage_Layout);
//            if (CarHealth.contains("Need")){
//                health_background.setBackground(getResources().getDrawable(R.drawable.warning_home_background));
//            }

            /*
            Stopped using ListView
             */
//            ListView listview=(ListView)rootView.findViewById(R.id.home_list);
//            listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));


            return rootView;
        }

        public void onResume() {
            super.onResume();
            topImage= (HomeHexagonView) rootView.findViewById(R.id.HomeHexagonView);
            topImage.invalidate();

        }

       /* @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }*/
        public static View getHomeRootView(){
            return rootView;
        }
    }

