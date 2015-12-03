package jayxu.com.carassist.UI;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
        View rootView = inflater.inflate(R.layout.fragment_stat, container, false);


        return rootView;
    }
}
