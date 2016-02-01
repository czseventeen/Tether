package jayxu.com.carassist.ADAPTER;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.security.Key;
import java.util.ArrayList;

import jayxu.com.carassist.MODEL.ItemData;
import jayxu.com.carassist.MODEL.UsefulConstants;
import jayxu.com.carassist.R;
import jayxu.com.carassist.UI.ParseApplication;

/**
 * Created by Yuchen on 12/7/2015.
 */
public class MyStatAdapter extends RecyclerView.Adapter<MyStatAdapter.MyStatViewHolder> {
    private ArrayList<ItemData> mItemDatas;


    public MyStatAdapter(ArrayList<ItemData> itemData){

        this.mItemDatas =itemData;
    }

    @Override
    public MyStatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.list_text, viewGroup, false);
        MyStatViewHolder viewHolder=new MyStatViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MyStatViewHolder myStatViewHolder, int i) {
        myStatViewHolder.bindData(mItemDatas.get(i));
    }

    @Override
    public int getItemCount() {
        return mItemDatas.size();
    }

    public class MyStatViewHolder extends RecyclerView.ViewHolder{
        public TextView mDescription;
        public TextView mValue;
        public MyStatViewHolder(View itemView) {
            super(itemView);
            mDescription=(TextView)itemView.findViewById(R.id.item_description);
            mValue=(TextView)itemView.findViewById(R.id.item_value);
        }


    public void bindData(ItemData itemdata){
        String KeyString=itemdata.getDescriptionKey();
        String unit= ParseApplication.resources.getString(UsefulConstants.UnitMapping.get(KeyString));
        mDescription.setText("< "+ParseApplication.resources.getString(UsefulConstants.Description_Stringmapping.get(KeyString))+" >");
        mValue.setText(itemdata.getValue()+" "+unit);
    }
    }
}
