package com.papaer.acc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.accessibilityexample.R;

import java.util.Collections;
import java.util.List;

/**
 * Created by Valdio Veliu on 2/11/2016.
 */

public class Recycler_View_Adapter extends RecyclerView.Adapter<Recycler_View_Adapter.View_Holder> {

    List list = Collections.emptyList();
    Context context;

    public Recycler_View_Adapter(List list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public View_Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Inflate the layout, initialize the View Holder
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_t, parent, false);
        View_Holder holder = new View_Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(View_Holder holder, int position) {
        //Use the provided View Holder on the onCreateViewHolder method to populate the current row on the RecyclerView
        holder.textView.setText("Accessibility test " + position);
    }

    @Override
    public int getItemCount() {
        //returns the number of elements the RecyclerView will display
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


    public class View_Holder extends RecyclerView.ViewHolder {

        TextView textView;
        View_Holder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.recyclerItem);
        }
    }
}