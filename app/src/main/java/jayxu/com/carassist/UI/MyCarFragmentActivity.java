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
public class MyCarFragmentActivity extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";


    public static MyCarFragmentActivity newInstance(int sectionNumber) {
        MyCarFragmentActivity fragment = new MyCarFragmentActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public MyCarFragmentActivity() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mycar, container, false);

        return rootView;
    }


}
