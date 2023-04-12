package com.example.mobile_computing.ui.flightsPage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mobile_computing.R;
import com.example.mobile_computing.model.FlightDescriptionModel;

import java.util.ArrayList;

class FlightsRecyclerViewAdapter extends RecyclerView.Adapter<FlightsRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<FlightDescriptionModel> flights;
    private FlightsSelectListener listener;


    public FlightsRecyclerViewAdapter(Context context, ArrayList<FlightDescriptionModel> flights, FlightsSelectListener listener){
        this.context = context;
        this.flights = flights;
        this.listener = listener;
    }
    @NonNull
    @Override
    public FlightsRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recycler_view_row,parent,false);

        return new FlightsRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightsRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
    //assigning values to the views created
        holder.tvCountry.setText(flights.get(position).getCountry());
        holder.tvPrice.setText(flights.get(position).getPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(flights.get(position));
            }
        });
        //Loads url image, if an error occurs, an error image is displayed
        Glide.with(context)
                .load(flights.get(position).getImageUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imageView);
    }



    @Override
    public int getItemCount() {
        return flights.size();
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvCountry, tvPrice;
        public CardView cardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvCountry = itemView.findViewById(R.id.tvCountry);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            cardView = itemView.findViewById(R.id.main_container);
        }

    }
}

