package com.triptime.devcell;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import ValueObjects.Center;
import ValueObjects.Sample;
import ValueObjects.Task;
import dbmanager.DBHelper;

public class MainActivity extends AppCompatActivity {

    //private Map<String, Integer> brands;
    //private String[] sampleList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView mBrandCards;
    private RecyclerView.Adapter mBrandsAdapter;
    private RecyclerView.LayoutManager mBrandsLayout;
    Context mContext;

    public static final String BRAND_MSG = "BRAND";
    public static final String CD_MSG = "CD";
    public static final String TIME_MSG = "HORA";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        Log.i("TrimTime Begin","Iniciando la aplicación");

        Log.i("TrimTime Begin","Obteniendo marcas");
        //getBrands();
        DBHelper db = DBHelper.getInstance(this);

        List<Task> tasks = db.getAllSampleTasks();

        Log.i("TrimTime Begin","Iterando marcas para crear tarjetas a mostrar");
        mBrandCards = (RecyclerView) findViewById(R.id.cardHolder);
        mBrandCards.setHasFixedSize(true);

        mBrandsLayout = new LinearLayoutManager(this);
        mBrandCards.setLayoutManager(mBrandsLayout);
        //mBrandsAdapter = new BrandsAdapter(getBrands(), this);
        mBrandsAdapter = new BrandsAdapter(tasks, this);
        mBrandCards.setAdapter(mBrandsAdapter);

        mRecyclerView = (RecyclerView) findViewById(R.id.samples);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        DividerItemDecoration didecor = new DividerItemDecoration(mRecyclerView.getContext(), R.drawable.divider);
        mRecyclerView.addItemDecoration(didecor);
        mAdapter = new SamplesAdapter(this, new ArrayList<Sample>());
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if(resultCode == RESULT_OK){
                Log.i("RESULT", "Muestra ingresada OK");

                String brand = data.getStringExtra(BRAND_MSG);
                String cdOrigen = data.getStringExtra(CD_MSG);
                String horaMuestra = data.getStringExtra(TIME_MSG);

                Log.i("RESULT", "Marca: " + brand);
                Log.i("RESULT", "CD: " + cdOrigen);
                Log.i("RESULT", "Hora: " + horaMuestra);

                Sample sample = new Sample();
                sample.setSampleTime(horaMuestra);
                sample.setInitCD(cdOrigen);
                sample.setSampleBrand(brand);

                RecyclerView v = (RecyclerView) findViewById(R.id.samples);
                SamplesAdapter sAdapter = (SamplesAdapter) v.getAdapter();
                sAdapter.addItem(sample, sAdapter.getItemCount());
                sAdapter.notifyItemInserted(sAdapter.getItemCount());

                BrandsAdapter bAdapter = (BrandsAdapter) ((RecyclerView) findViewById(R.id.cardHolder)).getAdapter();
                int index = bAdapter.editItem(brand);
                bAdapter.notifyItemChanged(index);
                Log.i("MACT","Tarjeta modificada: " + String.valueOf(index));

                DBHelper db = DBHelper.getInstance(this);
                db.addSampleRecord(sample);
            }
            if (resultCode == RESULT_CANCELED) {
                //Write your code if there's no result
                Log.i("RESULT", "Muestra cancelada");
            }
        }
    }

    /*public void saveData(View view){
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.bottle_time);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);*/

        /*TableLayout tl = (TableLayout) findViewById(R.id.samples);

        TableRow tr = new TableRow(this);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        EditText cdOrigen = new EditText(this);
        Spinner sCDOrigen = (Spinner) findViewById(R.id.origin_plant);
        cdOrigen.setText(sCDOrigen.getSelectedItem().toString());
        cdOrigen.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        cdOrigen.setEnabled(false);
        sCDOrigen.setSelection(sCDOrigen.getAdapter().getCount());

        tr.addView(cdOrigen);

        EditText brand = new EditText(this);
        Spinner sBrand = (Spinner) findViewById(R.id.sample_brand);
        brand.setText(sBrand.getSelectedItem().toString());
        brand.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        brand.setEnabled(false);
        sBrand.setSelection(sBrand.getAdapter().getCount());

        tr.addView(brand);

        EditText hour = new EditText(this);
        EditText tHour = (EditText) findViewById(R.id.bottle_time);
        hour.setText(tHour.getText().toString());
        hour.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));
        hour.setEnabled(false);
        tHour.setText("");

        tr.addView(hour);
        tr.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.WRAP_CONTENT, TableRow.LayoutParams.WRAP_CONTENT));

        tl.addView(tr, new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));
        //tl.addView(tr, new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, ScrollView.LayoutParams.WRAP_CONTENT));
    }*/

    /*private void fillSampleList(){
        sampleList = new String[20];
        sampleList[0] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[1] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[2] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[3] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[4] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[5] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[6] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[7] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[8] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[9] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[10] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[11] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[12] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[13] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[14] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[15] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[16] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[17] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[18] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
        sampleList[19] = "CD: Tocancipá \nMarca: Aguila Light 330cc \nHora: 13:15";
    }*/
}
