package com.triptime.devcell;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;

import java.util.ArrayList;
import java.util.List;

import ValueObjects.Sample;
import ValueObjects.Task;

class BrandsAdapter extends RecyclerView
        .Adapter<BrandsAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "BrandsAdapter";
    private List<Task> mDataset;
    private static BrandClickListener brandClickListener;
    Context mContext;
    public static final String BRAND_NAME = "BRAND";
    private static final Integer SAMPLE_DATA = 1;

    public static class DataObjectHolder extends RecyclerView.ViewHolder
            implements View
            .OnClickListener {
        TextView sBrand;
        TextView sSample;

        public DataObjectHolder(View itemView) {
            super(itemView);
            sBrand = (TextView) itemView.findViewById(R.id.sampleBrand);
            sSample = (TextView) itemView.findViewById(R.id.pendingSamples);
            Log.i(LOG_TAG, "Adding Listener to Card");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            brandClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(BrandClickListener brandClickListener) {
        this.brandClickListener = brandClickListener;
    }

    public BrandsAdapter(List<Task> myDataset, Activity activity) {
        mDataset = myDataset;
        this.brandClickListener = new CardClickHandler(activity);
        //this.mContext = _mContext;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent,
                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.sBrand.setText(mDataset.get(position).getBrand());
        holder.sSample.setText(mDataset.get(position).getPendingSamples()+"");
    }

    public void addItem(Task dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public int editItem(String brand) {
        int index = 0;
        Log.i("BADAPTER","Cantidad tarjetas " + String.valueOf(mDataset.size()));

        for (int i = 0; i < mDataset.size(); i++){
            Task t = (Task) mDataset.get(i);
            if (t.getBrand().toString().equals(brand)){
                int pendientes = t.getPendingSamples();

                index = i;

                Log.i("BADAPTER","Modificar tarjeta para marca " + brand);
                Log.i("BADAPTER","Tarjeta encontrada " + t.getBrand());

                t.setPendingSamples(pendientes-1);
                mDataset.set(index,t);
                notifyItemChanged(index);
                return index;
            }
            index++;
        }

        return index;
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface BrandClickListener {
        public void onItemClick(int position, View v);
    }

    public class CardClickHandler extends AppCompatActivity implements BrandClickListener
    {
        private Activity activity;

        public CardClickHandler(Activity _activity){
            this.activity = _activity;
        }

        @Override
        public void onItemClick(int position, View v) {
            //Toast.makeText(v.getContext(),"Marca seleccionada: "+((Task)mDataset.get(position)).getBrand(),Toast.LENGTH_SHORT).show();
            try {
                Task tSelected = (Task) mDataset.get(position);

                if (tSelected.getPendingSamples() > 0) {
                    Log.i("CLICK", "Iniciar actividad para recolección de datos");

                    Intent intent = new Intent(this.activity.getApplicationContext(), InputSample.class);
                    String message = tSelected.getBrand();
                    intent.putExtra(BRAND_NAME, message);
                    this.activity.startActivityForResult(intent, SAMPLE_DATA);
                }
                else{
                    Log.i("CLICK", "No hay pendientes de registro para esta marca");
                    Toast.makeText(v.getContext(),"Cero pendientes para marca "+((Task)mDataset.get(position)).getBrand(),Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Log.e("BrandsAdapter", e.toString());
            }
        }
    }
}
