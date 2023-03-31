package com.example.mobile_computing.ui.flightsPage;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightDescriptionModel;

import java.util.ArrayList;

class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<FlightsRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<FlightDescriptionModel> flights;

    public FlightsRecyclerViewAdapter(Context context, ArrayList<FlightDescriptionModel> flights){
        this.context = context;
        this.flights = flights;
    }
    @NonNull
    @Override
    public FlightsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);

        return new FlightsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightsRecyclerViewAdapter.MyViewHolder holder, int position) {
    //assigning values to the views created
        holder.tvCountry.setText(flights.get(position).getCountry());
        holder.tvPrice.setText(flights.get(position).getPrice());
    }

    @Override
    public int getItemCount() {
        return flights.size();
    }

    public static class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvCountry, tvPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            Log.d("country: ", tvCountry.getText().toString());
        }
    }
}
