package com.android.eve;

import android.app.Activity;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by Mark on 3/11/2017.
 */

public class tools {
    public static void setFullScreen(Activity activity) {
        activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    public static void ReadPlayer(Activity activity) {
        PlayerHelper db = new PlayerHelper(activity);
        List<Player> players = db.getAllPlayer();
        for (Player player : players) {
            player.setCn(player.getCn());
            player.setName(player.getName());
            player.setBirdayDay(player.getBirdayDay());
            player.setEmail(player.getEmail());
            userInfo.id = player.getCn() + "";
            userInfo.name = player.getName();
            userInfo.bday = player.getBirdayDay();
            userInfo.emal = player.getBirdayDay();

            // Variables.CustomListViewValuesArr.add(player);
        }
    }




}
