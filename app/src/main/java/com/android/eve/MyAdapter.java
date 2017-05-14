package com.android.eve;

/**
 * Created by Mark on 2/19/2017.
 */

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MyAdapter extends ArrayAdapter<ChatMessage> {
    private static LayoutInflater inflater = null;
    ArrayList<ChatMessage> item = new ArrayList<>();

    public MyAdapter(Context context, int textViewResourceId, ArrayList<ChatMessage> objects) {
        super(context, textViewResourceId, objects);
        item = objects;
        inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;
        v = inflater.inflate(R.layout.chatbubble, null);
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.bubble_layout);
        LinearLayout parent_layout = (LinearLayout) v.findViewById(R.id.bubble_layout_parent);
        LinearLayout linear_emotion = (LinearLayout) v.findViewById(R.id.linear_emotion);
        LinearLayout linear_bully = (LinearLayout) v.findViewById(R.id.linear_bully);
        LinearLayout linear_someone = (LinearLayout) v.findViewById(R.id.linear_someone);
        LinearLayout linear_emotion_2 = (LinearLayout) v.findViewById(R.id.linear_emotion_2);
        LinearLayout linear_yesno_1 = (LinearLayout) v.findViewById(R.id.linear_yesno_1);
        LinearLayout linear_yesno_2 = (LinearLayout) v.findViewById(R.id.linear_yesno_2);
        LinearLayout linear_tellthem = (LinearLayout) v.findViewById(R.id.linear_tellthem);
        LinearLayout linear_stoAsk = (LinearLayout) v.findViewById(R.id.linear_stoAsk);
        LinearLayout linear_suggest = (LinearLayout) v.findViewById(R.id.linear_suggest);
        LinearLayout linear_ask3 = (LinearLayout) v.findViewById(R.id.linear_ask3);
        LinearLayout linear_ask4 = (LinearLayout) v.findViewById(R.id.linear_ask4);

        ImageView img_avatar1 = (ImageView) v.findViewById(R.id.img_avatar1);
        ImageView img_avatar2 = (ImageView) v.findViewById(R.id.img_avatar2);
        TextView txt_item = (TextView) v.findViewById(R.id.message_text);
        TextView txt_sender = (TextView) v.findViewById(R.id.txt_sender);
        TextView txt_time = (TextView) v.findViewById(R.id.txt_time);
        txt_time.setText(item.get(position).getDate());
        txt_item.setText(item.get(position).getBody());
        txt_item.setTag(item.get(position).getMsgid());




       // Toast.makeText(getContext(),item.get(position).getIsOption()+"",Toast.LENGTH_LONG).show();
        if (item.get(position).getIsMine() == 1) {
            txt_sender.setText("Me");
            img_avatar1.setVisibility(View.GONE);
            layout.setBackgroundResource(R.drawable.bubble2);
            parent_layout.setGravity(Gravity.RIGHT);

        } else {
            img_avatar2.setVisibility(View.GONE);
            txt_sender.setText(item.get(position).getSender());
            layout.setBackgroundResource(R.drawable.bubble1);
            parent_layout.setGravity(Gravity.LEFT);

        }
        txt_item.setTextColor(Color.BLACK);


        if (item.get(position).getIsOption() == 0 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.VISIBLE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 1 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);

            linear_bully.setVisibility(View.GONE);//set teporary to GONE
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 2 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.VISIBLE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 3 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);

            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.VISIBLE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 4 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.VISIBLE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 5 && item.get(position).getIsMine() == 2 ) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.VISIBLE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 6 && item.get(position).getIsMine() == 2) {

            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.VISIBLE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 7 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.VISIBLE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 8 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.VISIBLE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        } else if (item.get(position).getIsOption() == 9 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.VISIBLE);
            linear_ask4.setVisibility(View.GONE);
        }else if (item.get(position).getIsOption() == 10 && item.get(position).getIsMine() == 2) {
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.VISIBLE);
        }else{
            linear_emotion.setVisibility(View.GONE);
            linear_bully.setVisibility(View.GONE);
            linear_someone.setVisibility(View.GONE);
            linear_emotion_2.setVisibility(View.GONE);
            linear_yesno_1.setVisibility(View.GONE);
            linear_yesno_2.setVisibility(View.GONE);
            linear_tellthem.setVisibility(View.GONE);
            linear_stoAsk.setVisibility(View.GONE);
            linear_suggest.setVisibility(View.GONE);
            linear_ask3.setVisibility(View.GONE);
            linear_ask4.setVisibility(View.GONE);
        }

        return v;

    }


}