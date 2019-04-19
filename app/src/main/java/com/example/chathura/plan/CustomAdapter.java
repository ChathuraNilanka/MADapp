package com.example.chathura.plan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chathura on 9/21/2018.
 */

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<ModelPlan> planModelArrayList;

    public CustomAdapter(Context context, ArrayList<ModelPlan> planModelArrayList) {

        this.context = context;
        this.planModelArrayList = planModelArrayList;
    }


    @Override
    public int getCount() {
        return planModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return planModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.single_item, null, true);

            holder.tvname = (TextView) convertView.findViewById(R.id.trip_title);

            convertView.setTag(holder);
        }else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)convertView.getTag();
        }

        holder.tvname.setText(planModelArrayList.get(position).getTitle());


        return convertView;
    }

    private class ViewHolder {

        protected TextView tvname;
    }

}
