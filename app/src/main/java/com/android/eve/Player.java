package com.android.eve;

/**
 * Created by Mark on 10/18/2016.
 */

public class Player {
    private int cn;
    private String name,birdayDay,email;


public Player(){

}

    public Player(int id, String name_, String bday,String eml) {
        cn = id;
        name = name_;
        birdayDay = bday;
        email = eml;
    }


    public int getCn() {
        return cn;
    }

    public String getName() {
        return name;
    }

    public String getBirdayDay() {
        return birdayDay;
    }

    public String getEmail() {
        return email;
    }

    public void setCn(int cn) {
        this.cn = cn;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirdayDay(String birdayDay) {
        this.birdayDay = birdayDay;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
