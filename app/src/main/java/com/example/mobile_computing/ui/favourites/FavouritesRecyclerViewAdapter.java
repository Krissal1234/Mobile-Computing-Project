package com.example.mobile_computing.ui.favourites;



import android.annotation.SuppressLint;
import android.content.Context;
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
import com.example.mobile_computing.model.FlightModel;
import com.example.mobile_computing.model.HotelModel;

import java.util.ArrayList;

class FavouritesRecyclerViewAdapter extends RecyclerView.Adapter<FavouritesRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<FlightModel> flights;
    ArrayList<HotelModel> hotels;
    private FavouritesSelectListener listener;
    private OnFavouriteClickListener favouriteClickListener;

    public FavouritesRecyclerViewAdapter(Context context, ArrayList<FlightModel> flights, ArrayList<HotelModel> hotels, FavouritesSelectListener listener){
        this.context = context;
        this.flights = flights;
        this.listener = listener;
        this.hotels = hotels;
    }
    @NonNull
    @Override
    public FavouritesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.favourite_view_row,parent,false);

        return new FavouritesRecyclerViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesRecyclerViewAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        //assigning values to the views created
        holder.date.setText(flights.get(position).getDepartureDate());
        holder.tvCountry.setText(flights.get(position).getDestination());
        holder.tvPrice.setText(flights.get(position).getPrice());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClicked(flights.get(position), hotels.get(position));
            }
        });
        //Loads url image, if an error occurs, an error image is displayed
        Glide.with(context)
                .load(flights.get(position).getImageUrl())
                .placeholder(R.drawable.ic_baseline_image_24)
                .error(R.drawable.ic_baseline_error_24)
                .into(holder.imageView);
    }



    public void setOnFavouriteClickListener(OnFavouriteClickListener listener) {
        this.favouriteClickListener = listener;
    }
    @Override
    public int getItemCount() {
        return flights.size();
    }

    public interface OnFavouriteClickListener {
        void onFavouriteClick(int position);
    }



    public class MyViewHolder extends  RecyclerView.ViewHolder {
        ImageView imageView;
        TextView date;
        TextView tvCountry, tvPrice;
        public CardView cardView;
        ImageView favouriteButton;
        /**
         * Constructor for MyViewHolder class.
         * Initializes the views required for the favourites fragment within the item view.
         *
         * @param itemView The item view for the RecyclerView.
         */
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.favourites_date);
            imageView = itemView.findViewById(R.id.favourites_imageView);
            tvCountry = itemView.findViewById(R.id.favourites_country);
            tvPrice = itemView.findViewById(R.id.favourites_price);
            cardView = itemView.findViewById(R.id.favourites_container);
            favouriteButton = itemView.findViewById(R.id.favorite_button_Row);

            favouriteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (favouriteClickListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            favouriteClickListener.onFavouriteClick(position);
                        }
                    }
                }
            });
        }

    }
}

