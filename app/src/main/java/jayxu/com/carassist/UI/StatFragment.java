package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.ParseUser;

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


        View rootView = inflater.inflate(R.layout.fragment_stat, container, false);
        ListView listview=(ListView)rootView.findViewById(R.id.stat_list);
        listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));

        return rootView;
    }
}
