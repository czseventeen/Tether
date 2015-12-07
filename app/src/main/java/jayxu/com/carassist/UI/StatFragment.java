package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import java.util.ArrayList;

import jayxu.com.carassist.ADAPTER.MyStatAdapter;
import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class StatFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";
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
        String results=user.getString("MYSTAT_DATA");
        results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
        String[] array_result=results.split(",");
        //Converting the results into a Array of ItemData
        ArrayList<ItemData> dataArray=new ArrayList<ItemData>();
        ArrayList<ItemData> dataArray2=new ArrayList<ItemData>();

        for(int i=0; i<array_result.length;i++)
        {
            String [] temp=array_result[i].split(":");
            ItemData tempdata=new ItemData();
            tempdata.setDescription(temp[0]);
            tempdata.setValue(temp[1]);
            if(temp[0].contains("Average")) {
                dataArray.add(tempdata);
            }
            else if(temp[0].contains("Total")){
                dataArray2.add(tempdata);
            }

        }



        View rootView = inflater.inflate(R.layout.fragment_stat, container, false);
        MyStatAdapter adapter=new MyStatAdapter(dataArray);
        RecyclerView recyclerView=(RecyclerView)rootView.findViewById(R.id.stat_recycleView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setHasFixedSize(true);

        MyStatAdapter adapter2=new MyStatAdapter(dataArray2);
        RecyclerView recyclerView2=(RecyclerView)rootView.findViewById(R.id.stat_recycleView2);
        recyclerView2.setAdapter(adapter2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
        recyclerView2.setHasFixedSize(true);



/*Stop using ListView        ListView listview=(ListView)rootView.findViewById(R.id.stat_list);
        listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));*/

        return rootView;
    }
}
