package pregcare.teamiv.lk.pregcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanninayake on 3/27/2017.
 */

public class DataBase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "CreateAppointment.db";
    private static final int DATABASE_VERSION = 1;
    public static final String APPOINTMENT_TABLE_NAME = "CreateAppointment";
    public static final String APPOINTMENT_COLUMN_ID = "appoId";
    public static final String APPOINTMENT_COLUMN_TITLE = "title";
    public static final String APPOINTMENT_COLUMN_TIME = "time";
    public static final String APPOINTMENT_COLUMN_DETAILS = "details";
    public static final String APPOINTMENT_COLUMN_DAY = "day";

    private static DataBase sInstance;

    public static synchronized DataBase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DataBase(context.getApplicationContext());
        }
        return sInstance;
    }

    public DataBase(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + APPOINTMENT_TABLE_NAME + "(" +
                APPOINTMENT_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                APPOINTMENT_COLUMN_TITLE + " VARCHAR, " +
                APPOINTMENT_COLUMN_TIME + " VARCHAR, " +
                APPOINTMENT_COLUMN_DETAILS + " VARCHAR, " +
                APPOINTMENT_COLUMN_DAY + " VARCHAR )"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + APPOINTMENT_TABLE_NAME);
        onCreate(db);
    }

    //insert appointment

    public void addAppo(){
        SQLiteDatabase db = getWritableDatabase();

        try {
            ContentValues values =new ContentValues();
            values.put(APPOINTMENT_COLUMN_TITLE, Appo.title);
            values.put(APPOINTMENT_COLUMN_TIME, Appo.time);
            values.put(APPOINTMENT_COLUMN_DETAILS, Appo.details);
            values.put(APPOINTMENT_COLUMN_DAY, Appo.day);
            db.insert(APPOINTMENT_TABLE_NAME,null,values);
            db.close();
        } catch (Exception e) {

        }
    }

    public List<Appo> titles(){
        List<Appo> titleList = new ArrayList<>();

        String selectquery = ("SELECT title, day FROM CreateAppointment;");

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectquery, null);

        if (cursor.moveToFirst()) {
            do {
                Appo appo =new Appo();
                appo.setTitle(cursor.getString(0));
                appo.setDay(cursor.getString(1));
                titleList.add(appo);
            } while(cursor.moveToNext());
        }
        return titleList;
    }



    public List<Appo> getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + APPOINTMENT_TABLE_NAME, null);

        List<Appo> apposList = new ArrayList<>();
            if (cursor.moveToFirst()) {
                Appo appo = new Appo();
                while (cursor.moveToNext()){
                    appo.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(APPOINTMENT_COLUMN_ID))));
                    appo.setTitle(cursor.getString(cursor.getColumnIndex(APPOINTMENT_COLUMN_TITLE)));
                    appo.setTime(cursor.getString(cursor.getColumnIndex(APPOINTMENT_COLUMN_TIME)));
                    appo.setDetails(cursor.getString(cursor.getColumnIndex(APPOINTMENT_COLUMN_DETAILS)));
                    appo.setDay(cursor.getString(cursor.getColumnIndex(APPOINTMENT_COLUMN_DAY)));
                    apposList.add(appo);
                }

            }
        return apposList;
    }

    public void deleteAllAppo(Appo appo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(APPOINTMENT_TABLE_NAME, APPOINTMENT_COLUMN_DAY + " = ?",
                new String[] { String.valueOf(appo.getDay()) });
        db.close();
    }

    public void deleteAnAppo(Appo appo) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(APPOINTMENT_TABLE_NAME, APPOINTMENT_COLUMN_ID + " = ?",
                new String[] { String.valueOf(appo.getId()) });
        db.close();
    }

    public int updateDate(Appo appo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(APPOINTMENT_COLUMN_TITLE,appo.getTitle());
       // values.put(APPOINTMENT_COLUMN_TIME,appo.getTime());
        //values.put(APPOINTMENT_COLUMN_DETAILS,appo.getDetails());
        values.put(APPOINTMENT_COLUMN_DAY, appo.getDay());

        // updating row
        return db.update(APPOINTMENT_TABLE_NAME, values, APPOINTMENT_COLUMN_ID + " = ?",
                new String[] { String.valueOf(appo.getId()) });
    }

    public int update(Appo appo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(APPOINTMENT_COLUMN_TITLE,appo.getTitle());
        values.put(APPOINTMENT_COLUMN_TIME,appo.getTime());
        values.put(APPOINTMENT_COLUMN_DETAILS,appo.getDetails());

        // updating row
        return db.update(APPOINTMENT_TABLE_NAME, values, APPOINTMENT_COLUMN_ID + " = ?",
                new String[] { String.valueOf(appo.getId()) });
    }
}
