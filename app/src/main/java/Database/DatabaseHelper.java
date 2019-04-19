package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;


import com.example.chathura.plan.ModelPlan;

import java.util.ArrayList;

/**
 * Created by Chathura on 9/21/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static String DATABASE_NAME = "travel";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE = "trips";
    private static final String KEY_ID = "id";
    private static final String KEY_TITLE = "title";
    private static final String KEY_NUMDAYS = "num_days";
    private static final String KEY_STARTDATE = "start_date";
    private static final String KEY_ENDDATE = "end_date";
    private static final String KEY_TYPE = "type";
    private static final String KEY_NUMPERSON = "num_person";


    private static final String CREATE_TABLE_TRIPS = "CREATE TABLE "
            + TABLE + "(" + KEY_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_TITLE + " TEXT, "+ KEY_NUMDAYS + " TEXT, "+ KEY_STARTDATE + " TEXT, "+ KEY_ENDDATE + " TEXT, "+ KEY_TYPE + " TEXT, "+ KEY_NUMPERSON + " TEXT);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d("table", CREATE_TABLE_TRIPS);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_TRIPS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS '" + TABLE + "'");
        onCreate(db);
    }

    public void addTripDetail(String title, String nDays, String sDate, String eDate, String type, String nPerson) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_NUMDAYS, nDays);
        values.put(KEY_STARTDATE, sDate);
        values.put(KEY_ENDDATE, eDate);
        values.put(KEY_TYPE, type);
        values.put(KEY_NUMPERSON, nPerson);

        //long insert =
        db.insert(TABLE, null, values);

        //return insert;
    }

    public ArrayList<ModelPlan> getAllTrips() {
        ArrayList<ModelPlan> planModelArrayList = new ArrayList<ModelPlan>();

        String selectQuery = "SELECT  * FROM " + TABLE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        if (c.moveToFirst()) {
            do {
                ModelPlan mp = new ModelPlan();
                mp.setPlan_id(c.getInt(c.getColumnIndex(KEY_ID)));
                mp.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                mp.setNum_days(c.getString(c.getColumnIndex(KEY_NUMDAYS)));
                mp.setStart_date(c.getString(c.getColumnIndex(KEY_STARTDATE)));
                mp.setEnd_date(c.getString(c.getColumnIndex(KEY_ENDDATE)));
                mp.setTrans_type(c.getString(c.getColumnIndex(KEY_TYPE)));
                mp.setNum_person(c.getString(c.getColumnIndex(KEY_NUMPERSON)));

                planModelArrayList.add(mp);
            } while (c.moveToNext());
        }
        return planModelArrayList;
    }

    public ModelPlan displayTrip(int id){
        String selectQuery = "SELECT  * FROM " + TABLE+ " WHERE "+KEY_ID+" = "+id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        ModelPlan mp = new ModelPlan();

        while (c.moveToNext()) {
                mp.setTitle(c.getString(c.getColumnIndex(KEY_TITLE)));
                mp.setNum_days(c.getString(c.getColumnIndex(KEY_NUMDAYS)));
                mp.setStart_date(c.getString(c.getColumnIndex(KEY_STARTDATE)));
                mp.setEnd_date(c.getString(c.getColumnIndex(KEY_ENDDATE)));
                mp.setTrans_type(c.getString(c.getColumnIndex(KEY_TYPE)));
                mp.setNum_person(c.getString(c.getColumnIndex(KEY_NUMPERSON)));

        }
        mp.setPlan_id(id);
        return mp;
    }

    public int updateTrip(int id, String title, String nDays, String sDate, String eDate, String type, String nPerson) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TITLE, title);
        values.put(KEY_NUMDAYS, nDays);
        values.put(KEY_STARTDATE, sDate);
        values.put(KEY_ENDDATE, eDate);
        values.put(KEY_TYPE, type);
        values.put(KEY_NUMPERSON, nPerson);

        return db.update(TABLE, values, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

    public void deleteTrip(int id) {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE, KEY_ID + " = ?",
                new String[]{String.valueOf(id)});
    }

}