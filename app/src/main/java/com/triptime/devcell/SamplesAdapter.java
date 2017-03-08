package com.triptime.devcell;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.R;

import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ValueObjects.Sample;

/**
 * Created by Development on 3/3/2017.
 */

public class SamplesAdapter extends RecyclerView.Adapter<SamplesAdapter.ViewHolder> {
    private List<Sample> mDataset;
    private final Context mContext;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public final View sampleItem;
        public ViewHolder(View v) {
            super(v);
            sampleItem = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public SamplesAdapter(Context context, List<Sample> data){
        this.mContext = context;
        if (data != null)
            mDataset = data;
    }

    public void addItem(Sample s, int position){
        position = position == -1 ? getItemCount()  : position;
        mDataset.add(position,s);
        notifyItemInserted(position);
    }

    public void remove(int position){
        if (position < getItemCount()  ) {
            mDataset.remove(position);
            notifyItemRemoved(position);
        }
    }

    // Create new views (invoked by the layout manager)
    @Override
    public SamplesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        // create a new view
        final View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sampleitem, parent, false);
        // set the view's size, margins, paddings and layout parameters

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        /*holder.mTextView.setText(mDataset.get(position));
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"Position ="+position,Toast.LENGTH_SHORT).show();
            }
        });*/
        ((TextView) holder.sampleItem.findViewById(R.id.brand_value)).setText((mDataset.get(position)).getSampleBrand());
        ((TextView) holder.sampleItem.findViewById(R.id.cd_value)).setText((mDataset.get(position)).getInitCD());
        ((TextView) holder.sampleItem.findViewById(R.id.time_value)).setText((mDataset.get(position)).getSampleTime());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
