package com.triptime.devcell;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.myfirstapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import ValueObjects.Center;
import ValueObjects.Task;
import dbmanager.DBHelper;

public class InputSample extends AppCompatActivity {
    private ArrayList<String> centros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_sample);

        Intent intent = getIntent();
        String message = intent.getStringExtra(BrandsAdapter.BRAND_NAME);
        ((TextView)findViewById(R.id.marca)).setText(message);

        //llenarCentros();
        DBHelper db = DBHelper.getInstance(this);

        List<Center> centers = db.getAllCenters();
        List<String> cNames = db.getAllCentersNames();

        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, cNames);
        final AutoCompleteTextView autoCentros;
        autoCentros = (AutoCompleteTextView) findViewById(R.id.in_cdOrigen);
        autoCentros.setAdapter(arrayAdapter);
        autoCentros.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View arg0){
                autoCentros.showDropDown();
            }
        });
    }

    public void aceptarMuestra(View v){
        AutoCompleteTextView cdOrigen = (AutoCompleteTextView)findViewById(R.id.in_cdOrigen);
        TextView horaMuestra = (TextView)findViewById(R.id.in_horaMuestra);

        if (isValidCenter(cdOrigen.getText().toString()) && isValidTime(horaMuestra.getText().toString())) {
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.BRAND_MSG, ((TextView) findViewById(R.id.marca)).getText().toString());
            returnIntent.putExtra(MainActivity.CD_MSG, cdOrigen.getText().toString());
            returnIntent.putExtra(MainActivity.TIME_MSG, horaMuestra.getText().toString());
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        else{
            if (!isValidCenter(cdOrigen.getText().toString())){
                cdOrigen.setError("Este campo es requerido");
            }

            if (!isValidTime(horaMuestra.getText().toString())){
                horaMuestra.setError("La hora ingresada no es válida");
            }
        }
    }

    public void cancelarMuestra(View v){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();
    }

    private boolean isValidTime(String time){
        boolean isValid = true;

        if (time.contains(":") && (time.length() > 0)){
            String[] parts = time.split(":");
            if ((Integer.parseInt(parts[0]) > 23 || Integer.parseInt(parts[1]) > 59)){
                isValid = false;
            }
        }
        else{
            isValid = false;
        }

        return isValid;
    }

    private boolean isValidCenter(String center){
        return (center.length() > 0);
    }
}
