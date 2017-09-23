package com.ahmedaziz.coofde.DataBase;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import net.simonvt.schematic.annotation.Table;

/**
 * Created by Ahmed Aziz on 9/3/2017.
 */

public class DataBasa {
    private static final String LOG_TAG = DataBasa.class.getSimpleName();
    private DataBasa() {}
    public static final int VERSION = 3;

    @Table(ColumnsDB.class)public static final String COOFDE = "coofde";

    public static final String [] _MIGRATIONS ={
            "ALTER TABLE" + COOFDE + "ADD COLUMN url TEXT",
            "ALTER TABLE" + COOFDE + "ADD COLUMN store TEXT"

    };
  //  @onUpgrade
    public static void onUpgrade (SQLiteDatabase db , int oldVersion , int newVersion){
        for (int i = oldVersion ; i < newVersion ; i++){
            String migration = _MIGRATIONS [i - 1];
            db.beginTransaction();
            try {
                db.execSQL(migration);
                db.setTransactionSuccessful();




            }catch (Exception e ){
                Log.d(LOG_TAG , "Error Executing DataBase "+ migration);
                break;
            } finally {
                db.endTransaction();
            }
        }
    }
}
