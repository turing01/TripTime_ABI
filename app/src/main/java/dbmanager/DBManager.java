package dbmanager;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by Daniel on 8/03/2017.
 */

public class DBManager {
    private static DBManager instance = null;

    protected DBManager(){

    }

    public DBManager getInstance(){
        if(instance == null){
            instance = new DBManager();
        }

        return instance;
    }

    public ArrayList<String> obtenerCentros(String pais){
        ArrayList<String> centros = new ArrayList<String>();
        String query = "select centro from centros where pais = '" + pais + "'";
        SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(":memory", null);
        Cursor cursor = db.rawQuery(query, null);
        String sCentro = "";
        if (cursor.moveToNext()){
            sCentro = cursor.getString(0);
        }

        return centros;
    }
}
