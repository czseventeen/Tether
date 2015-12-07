package jayxu.com.carassist.ADAPTER;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Yuchen on 12/7/2015.
 */
public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.MyHomeViewHolder> {

    @Override
    public MyHomeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyHomeViewHolder myHomeViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyHomeViewHolder extends RecyclerView.ViewHolder{

        public MyHomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
