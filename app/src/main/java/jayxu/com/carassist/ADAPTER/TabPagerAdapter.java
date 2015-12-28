package jayxu.com.carassist.ADAPTER;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import jayxu.com.carassist.R;
import jayxu.com.carassist.UI.HomeFragment;
import jayxu.com.carassist.UI.MyCarFragment;
import jayxu.com.carassist.UI.StatFragment;

/**
 * Created by Yuchen on 12/7/2015.
 */
public class TabPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT=3;

    private Context context;
    //private String[] tabTitles={"Home","My Car","Statistics"};
    private String[] tabTitles=null;
    public TabPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context=context;
        tabTitles=new String[]{context.getString(R.string.tab_title_home),context.getString(R.string.tab_title_mycar),context.getString(R.string.tab_title_stat)};
    }

    @Override
    public CharSequence getPageTitle(int position){
        return tabTitles[position];
    }
    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch(position){
            default:
            case 0:
                fragment= HomeFragment.newInstance(position);
                break;
            case 1:
                fragment= MyCarFragment.newInstance(position);
                break;
            case 2:
                fragment= StatFragment.newInstance(position);
                break;
        }

        return fragment;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
