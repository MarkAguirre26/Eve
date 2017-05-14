package com.android.eve;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mark on 10/18/2016.
 */

public class PlayerHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlayerDb";
    private static final String TABLE_PLAYER = "PlayerTable";
    private static final String KEY_ID = "cn";
    private static final String KEY_NAME = "name";
    private static final String KEY_BDAY = "bday";
    private static final String KEY_EMAIL = "email";


    public PlayerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_PLAYER + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT, " + KEY_BDAY + " TEXT, " + KEY_EMAIL + " TEXT)";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PLAYER);
        onCreate(db);
    }

    public void addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, player.getName());;
        values.put(KEY_BDAY, player.getBirdayDay());
        db.insert(TABLE_PLAYER, null, values);
        db.close();
    }

    public Player getPlayer(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_PLAYER, new String[]{KEY_ID, KEY_NAME}, KEY_ID + "=?", new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();
        Player player = new Player(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(2));
        return player;
    }


    public List<Player> getAllPlayer() {
        List<Player> shopList = new ArrayList<Player>();
        String selectQuery;
        selectQuery = "SELECT * FROM " + TABLE_PLAYER + " ORDER BY " + KEY_ID + " DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
// looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = new Player();
                player.setCn(Integer.parseInt(cursor.getString(0)));
                player.setName(cursor.getString(1));
                shopList.add(player);
            } while (cursor.moveToNext());
        }

        return shopList;
    }


    public int getPlayerCount() {
        String countQuery = "SELECT * FROM " + TABLE_PLAYER + " ";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        //cursor.close();
// return count
        return cursor.getCount();
    }

    public int getCheckDuplicate(String name) {
        String countQuery = "SELECT * FROM " + TABLE_PLAYER + " Where " + KEY_NAME + " = '" + name + "'";
        Log.d("qry", countQuery);
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        return cursor.getCount();
    }



    public void deletePlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PLAYER, KEY_ID + " = ?", new String[]{String.valueOf(player.getCn())});
        db.close();
    }

}
