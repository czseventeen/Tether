package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.parse.ParseUser;

import jayxu.com.carassist.R;

/**
 * Created by Yuchen on 12/1/2015.
 */
public class MyCarFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";


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
        String results=user.getString("MYCAR_DATA");
        results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
        String[] array_result=results.split(",");


        View rootView = inflater.inflate(R.layout.fragment_mycar, container, false);

/*   Stop using ListView
     ListView listview=(ListView)rootView.findViewById(R.id.mycar_list);
        listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));*/


        return rootView;
    }


}
