package com.android.eve;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 PlayerAdapter * Created by Mark on 03/11/2017.
 */

public class PlayerAdapter extends BaseAdapter {
    private Activity activity;
    private ArrayList data;
    private static LayoutInflater inflater = null;

    Player tempValues = null;
    int i = 0;

    public PlayerAdapter(Activity activity_, ArrayList arrayList) {
        activity = activity_;
        data = arrayList;
        inflater = (LayoutInflater) activity.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        if (data.size() <= 0)
            return 1;
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder {
        public TextView txt_name;
        public TextView txt_age;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi = convertView;
        ViewHolder holder;

        if (convertView == null) {


           // vi = inflater.inflate(R.layout.row_listview, null);
            holder = new ViewHolder();
            holder.txt_name = (TextView) vi.findViewById(R.id.txt_name);
            holder.txt_age = (TextView) vi.findViewById(R.id.txt_age);
            vi.setTag(holder);
        } else
            holder = (ViewHolder) vi.getTag();
/*
        if (data.size() <= 0) {
            holder.txt_name.setText("No Data");
        } else {*/
        if (data.size() >= 1) {
            tempValues = null;
            tempValues = (Player) data.get(position);
            holder.txt_name.setTag(tempValues.getName());
            holder.txt_age.setText(tempValues.getBirdayDay());

        }
        return vi;
    }

    public  void  updateResult(ArrayList<Player> players){
        this.data = players;
        notifyDataSetChanged();
    }
}
