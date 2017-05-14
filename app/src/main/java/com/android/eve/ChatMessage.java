package com.android.eve;

import java.util.Random;

/**
 * Created by Mark on 3/11/2017.
 */

public class ChatMessage {
    public String body, sender,  senderName;
    public String Date, Time;
    public String msgid;
    public int isMine;// Did I send the message.
 public int isOption;
    public ChatMessage(String d, String Sender,String messageString, String ID, int isMINE,int option) {
        body = messageString;
        this.Date = d;
        isMine = isMINE;
        sender = Sender;
        msgid = ID;
        isOption = option;
        senderName = sender;
    }



    public int getIsMine() {
        return isMine;
    }

    public String getBody() {
        return body;
    }

    public String getSender() {
        return sender;
    }

    public String getSenderName() {
        return senderName;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getMsgid() {
        return msgid;
    }

    public int getIsOption() {
        return isOption;
    }

    public void setMsgID() {

        msgid += "-" + String.format("%02d", new Random().nextInt(100));

    }

}
