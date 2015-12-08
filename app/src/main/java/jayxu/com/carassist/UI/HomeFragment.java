package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import jayxu.com.carassist.ADAPTER.MyStatAdapter;
import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class HomeFragment extends Fragment {

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

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
            //retrieve all the data and parse to get data for home only.
            JSONObject home_results=null;
            ArrayList<ItemData> itemData_list=new ArrayList<>();
            try {
                home_results = user.getJSONObject(getString(R.string.JSON_KEY)).getJSONObject(getString(R.string.Home_Title));
                if(home_results!=null) {
                    Iterator<String> iterator = home_results.keys();
                    while (iterator.hasNext()) {
                        ItemData temp_item = new ItemData();
                        String key = iterator.next();
                        temp_item.setDescription(key);
                        temp_item.setValue(home_results.getString(key));
                        itemData_list.add(temp_item);
                    }
                }
            }catch (JSONException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
/*            String results=user.getString("HOME_DATA");
            results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
            String[] array_result=results.split(",");*/


            View rootView = inflater.inflate(R.layout.fragment_all, container, false);


            MyStatAdapter adapter=new MyStatAdapter(itemData_list);

            RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.Stat_recycleView);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setHasFixedSize(true);
            /*
            Stopped using ListView
             */
//            ListView listview=(ListView)rootView.findViewById(R.id.home_list);
//            listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));


            return rootView;
        }

       /* @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }*/
    }

