package jayxu.com.carassist.UI;

import android.app.Activity;
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
            ParseUser user=ParseUser.getCurrentUser();
            String results=user.getString("HOME_DATA");
            results=results.replaceAll("\\{","").replaceAll("\\}", "").replaceAll("\"", "");
            String[] array_result=results.split(",");


            View rootView = inflater.inflate(R.layout.fragment_home, container, false);
            ListView listview=(ListView)rootView.findViewById(R.id.home_list);
            listview.setAdapter(new ArrayAdapter<String>(getContext(), R.layout.list_text,R.id.list_text, array_result));


            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((HomeActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

