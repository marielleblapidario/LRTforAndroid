package edu.mobidev.barrettokalingolapidario.newtestingproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by vetkin123 on 10/27/2015.
 */
public class UserDatabaseHelper extends SQLiteOpenHelper {

    static final String SCHEMA = "lrtforandroid";

    public UserDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, SCHEMA, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //here is where you create your tables
        String sql = "CREATE TABLE " + User.TABLE_NAME +
                " ( " + User.COLUMN_USER_ID + " INTEGER PRIMARY KEY, "
                + " " + User.COLUMN_USER_FIRST_NAME + " TEXT , "
                + " " + User.COLUMN_USER_LAST_NAME + " TEXT , "
                + " " + User.COLUMN_USERNAME + " TEXT , "
                + " " + User.COLUMN_EMAIL + " TEXT , "
                + " " + User.COLUMN_PASSWORD + " TEXT );";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User.COLUMN_USER_LAST_NAME, user.getLastName());
        cv.put(User.COLUMN_USER_FIRST_NAME, user.getFirstName());
        cv.put(User.COLUMN_EMAIL, user.getEmail());
        cv.put(User.COLUMN_USERNAME, user.getUsername());
        cv.put(User.COLUMN_PASSWORD, user.getPassword());
        long result = db.insert(User.TABLE_NAME, null, cv);
        db.close();
        if(result == -1)
            return false;
        return true;
    }

    public ArrayList<User> getAllUser (){

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(User.TABLE_NAME, null, null, null, null, null, null);

        ArrayList<User> userList = new ArrayList<>();
        if(c.moveToFirst()){
            do{

                User user = new User();
                user.setUserId(c.getInt(c.getColumnIndex(User.COLUMN_USER_ID)));
                user.setFirstName(c.getString(c.getColumnIndex(User.COLUMN_USER_FIRST_NAME)));
                user.setLastName(c.getString(c.getColumnIndex(User.COLUMN_USER_LAST_NAME)));
                user.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
                user.setEmail(c.getString(c.getColumnIndex(User.COLUMN_EMAIL)));
                user.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
                userList.add(user);

            }while(c.moveToNext());
        }
        c.close();
        db.close();
        return userList;
    }

    public User getUser(int id){
        User user = new User();
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query(User.TABLE_NAME, null, User.COLUMN_USER_ID + " = ? ", new String[]{String.valueOf(id)}, null, null, null);
        if(c.moveToFirst()){
            user.setUserId(c.getInt(c.getColumnIndex(User.COLUMN_USER_ID)));
            user.setFirstName(c.getString(c.getColumnIndex(User.COLUMN_USER_FIRST_NAME)));
            user.setLastName(c.getString(c.getColumnIndex(User.COLUMN_USER_LAST_NAME)));
            user.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
            user.setEmail(c.getString(c.getColumnIndex(User.COLUMN_EMAIL)));
            user.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
        }
        c.close();
        db.close();
        return user;
    }

    public User getUser(String username){
        User user = new User();
        SQLiteDatabase db = getReadableDatabase();
        Log.i("getUser", "create query");
        Cursor c = db.query(User.TABLE_NAME, null, User.COLUMN_USERNAME + " = ? ", new String[]{String.valueOf(username)}, null, null, null);
        Log.i("getUser", "query executed");
        if(c.moveToFirst()){
            user.setUserId(c.getInt(c.getColumnIndex(User.COLUMN_USER_ID)));
            user.setFirstName(c.getString(c.getColumnIndex(User.COLUMN_USER_FIRST_NAME)));
            user.setLastName(c.getString(c.getColumnIndex(User.COLUMN_USER_LAST_NAME)));
            user.setUsername(c.getString(c.getColumnIndex(User.COLUMN_USERNAME)));
            user.setEmail(c.getString(c.getColumnIndex(User.COLUMN_EMAIL)));
            user.setPassword(c.getString(c.getColumnIndex(User.COLUMN_PASSWORD)));
        }
//        Log.i("getUser", user.getUsername());
        c.close();
        db.close();
        return user;
    }

    public boolean deleteuser(int id){
        SQLiteDatabase db = getWritableDatabase();
        int count = db.delete(User.TABLE_NAME, User.COLUMN_USER_ID + " = ? ", new String[]{String.valueOf(id)});

        if(count == 0)
            return false;
        return true;
    }

    public void updateUser(User user){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(User.COLUMN_USER_FIRST_NAME, user.getFirstName());
        cv.put(User.COLUMN_USER_LAST_NAME, user.getLastName());
        cv.put(User.COLUMN_USERNAME, user.getUsername());
        cv.put(User.COLUMN_EMAIL, user.getEmail());
        cv.put(User.COLUMN_PASSWORD, user.getPassword());
        db.update(User.TABLE_NAME,
                cv,
                User.COLUMN_USER_ID + " = ? ",
                new String[]{String.valueOf(user.getUserId())} );
    }
}

