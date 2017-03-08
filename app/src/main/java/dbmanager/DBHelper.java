package dbmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import ValueObjects.Center;
import ValueObjects.Sample;
import ValueObjects.Task;

/**
 * Created by Daniel on 8/03/2017.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ABI_TripTime";

    private static final int DATABASE_VERSION = 3;
    private static final String KEY_CENTER_ID = "cd_id";
    private static final String KEY_CENTER_NAME = "cd_name";
    private static final String KEY_SAMPLE_BRAND = "marca";
    private static final String KEY_SAMPLE_QUANTITY = "cantidad";
    private static final String KEY_SAMPLE_TIME = "hora";
    private static final String KEY_SAMPLE_DATE = "fecha";
    private static final String KEY_SAMPLE_SYNCSTATUS = "sync";
    private static final String TABLE_CENTER = "Centro";
    private static final String TABLE_SAMPLE = "Muestra";
    private static final String TABLE_SAMPLERECORD = "RegistroMuestra";

    private static DBHelper instance = null;

    // Database creation sql statement
    private static final String DATABASE_CREATE_CENTRES = "create table " + TABLE_CENTER + "( " + KEY_CENTER_ID + " text primary key," + KEY_CENTER_NAME + " text not null);";
    private static final String DATABASE_CREATE_SAMPLESTOTAKE = "create table " + TABLE_SAMPLE + "( " + KEY_SAMPLE_BRAND + " text not null," + KEY_SAMPLE_QUANTITY + " integer not null);";
    private static final String DATABASE_CREATE_DAILYSAMPLE = "create table " + TABLE_SAMPLERECORD + "( " + KEY_SAMPLE_BRAND + " text not null," + KEY_CENTER_ID + " text not null," + KEY_SAMPLE_TIME + " text not null," + KEY_SAMPLE_DATE + " text not null," + KEY_SAMPLE_SYNCSTATUS + " integer not null);";

    protected DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static DBHelper getInstance(Context context){
        if(instance == null){
            instance = new DBHelper(context);
        }

        return instance;
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase database) {
        Log.d("DB CREATE", "Creando tablas");

        database.execSQL(DATABASE_CREATE_CENTRES);
        database.execSQL(DATABASE_CREATE_SAMPLESTOTAKE);
        database.execSQL(DATABASE_CREATE_DAILYSAMPLE);

        Log.d("DB CREATE", "Carga inicial de datos para centros");
        //Carga inicial de datos - CENTROS
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Turbaco','Turbaco')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Santa Marta','Santa Marta')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Valledupar','Valledupar')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Corozal','Corozal')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Techo','Techo')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Pasto','Pasto')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Pereira','Pereira')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Girardot','Girardot')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Neiva','Neiva')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Villavicencio','Villavicencio')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Cúcuta','Cúcuta')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Armenia','Armenia')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Yopal','Yopal')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Honda','Honda - Centro')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Cucuta','O L Cucuta Poblaciones')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Calle22BBogota','O L Calle 22B Bogotá')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('BgaCali','Bga Cali')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('KABucaramanga','O L Cuentas Claves Bucaramanga')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Autosur','Autosur')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('AlmavivaBarranquilla','O L Almaviva Barranquilla')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('AlmavivaCartagena','OL Almaviva Cartagena')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Tunja','Tunja')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Ibague','Ibagué')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Popayan','Popayán')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('SanAndres','San Andrés')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Riohacha','Riohacha')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Maicao','O L Maicao')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Luruaco','O L Luruaco')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Fundacion','O L Fundación')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Bosconia','Bosconia')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Magangue','Magangué')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('O L Tolu','O L Tolu')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Aguachica','Aguachica')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('PuenteNacional','Puente Nacional')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('SanGil','San Gil')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Fonseca','Fonseca')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Malambo','Malambo')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Galapa','Galapa')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('JuandeAcosta','Juan de Acosta')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Bocagrande','Bocagrande')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Acacias','Acacias')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('CarmenDeBolivar','Carmen De Bolívar')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Barrancabermeja','Barrancabermeja')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Chiquinquira','O L Chiquinquira')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Curumani','Curumaní')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Villeta','O L Villeta')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Monteria','Montería')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('HondaUnion','Honda - Unión')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Caucasia','Caucasia')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('Apartado','Apartadó')");
        database.execSQL("insert into " + TABLE_CENTER + "(" + KEY_CENTER_ID + "," + KEY_CENTER_NAME + ") values('AlmavivaMedellin','OL Almaviva Medellín')");

        Log.d("DB CREATE", "Carga inicial de datos para muestras a tomar");
        //Carga inicial de datos - MUESTRAS
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Club Colombia Roja 330cc',2)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Club Colombia Dorada 330cc',3)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Poker Lata 250cc',3)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Poker Light 330cc',2)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Poker 330cc',4)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Pilsen 330cc',1)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Aguila Light 330cc',5)");
        database.execSQL("insert into " + TABLE_SAMPLE + "(" + KEY_SAMPLE_BRAND + "," + KEY_SAMPLE_QUANTITY + ") values('Aguila Cero 330cc',4)");
    }

    // Method is called during an upgrade of the database,
    @Override
    public void onUpgrade(SQLiteDatabase database,int oldVersion,int newVersion){
        Log.w(DBHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS Centro");
        database.execSQL("DROP TABLE IF EXISTS Muestra");
        database.execSQL("DROP TABLE IF EXISTS RegistroMuestra");
        onCreate(database);
    }

    public void addCenter(Center center){
        Log.d("addCenter", center.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_CENTER_ID, center.getId()); // get title
        values.put(KEY_CENTER_NAME, center.getName()); // get author

        db.insert(TABLE_CENTER, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        db.close();
    }

    public List<Center> getAllCenters() {
        List<Center> centers = new LinkedList<Center>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_CENTER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Center center = null;
        if (cursor.moveToFirst()) {
            do {
                center = new Center();
                center.setId(cursor.getString(0));
                center.setName(cursor.getString(1));

                centers.add(center);
            } while (cursor.moveToNext());
        }

        Log.d("getAllCenters()", centers.toString());

        return centers;
    }

    public List<String> getAllCentersNames() {
        List<String> centers = new LinkedList<String>();

        // 1. build the query
        String query = "SELECT  " + KEY_CENTER_NAME + " FROM " + TABLE_CENTER;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Center center = null;
        if (cursor.moveToFirst()) {
            do {
                centers.add(cursor.getString(0));
            } while (cursor.moveToNext());
        }

        Log.d("getAllCentersNames()", centers.toString());

        return centers;
    }

    public void addSampleTask(Task task){
        Log.d("addSampleTask", task.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SAMPLE_BRAND, task.getBrand()); // get title
        values.put(KEY_SAMPLE_QUANTITY, String.valueOf(task.getDailySamples())); // get author

        db.insert(TABLE_SAMPLE, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        db.close();
    }

    public List<Task> getAllSampleTasks() {
        List<Task> tasks = new LinkedList<Task>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SAMPLE;

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Task task = null;
        if (cursor.moveToFirst()) {
            do {
                task = new Task();
                task.setBrand(cursor.getString(0));
                task.setDailySamples(Integer.parseInt(cursor.getString(1)));
                task.setPendingSamples(Integer.parseInt(cursor.getString(1)));

                tasks.add(task);
            } while (cursor.moveToNext());
        }

        Log.d("getAllSampleTasks()", tasks.toString());

        return tasks;
    }

    public void addSampleRecord(Sample sample){
        Log.d("addSampleRecord", sample.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SAMPLE_BRAND, sample.getSampleBrand());
        values.put(KEY_CENTER_ID, String.valueOf(sample.getInitCD()));
        values.put(KEY_SAMPLE_TIME, String.valueOf(sample.getSampleTime()));
        values.put(KEY_SAMPLE_DATE, Calendar.getInstance().getTime().toString());
        values.put(KEY_SAMPLE_SYNCSTATUS, "0");

        db.insert(TABLE_SAMPLERECORD, // table
                null, //nullColumnHack
                values); // key/value -> keys = column names/ values = column values

        db.close();
    }

    public List<Sample> getAllPendingSampleRecords() {
        List<Sample> samples = new LinkedList<Sample>();

        // 1. build the query
        String query = "SELECT  * FROM " + TABLE_SAMPLERECORD + " WHERE " + KEY_SAMPLE_SYNCSTATUS + " = 0";

        // 2. get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        // 3. go over each row, build book and add it to list
        Sample sample = null;
        if (cursor.moveToFirst()) {
            do {
                sample = new Sample();
                sample.setSampleBrand(cursor.getString(0));
                sample.setInitCD(cursor.getString(1));
                sample.setSampleTime(cursor.getString(2));
                sample.setRecordDate(cursor.getString(3));

                samples.add(sample);
            } while (cursor.moveToNext());
        }

        Log.d("getSampleRecords()", samples.toString());

        return samples;
    }
}
