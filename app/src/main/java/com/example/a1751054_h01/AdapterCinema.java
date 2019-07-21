package com.example.a1751054_h01;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a1751054_h01.data.model.CinemaInfo;

import java.util.ArrayList;
import java.util.List;


public class AdapterCinema extends RecyclerView.Adapter<AdapterCinema.ViewHolder> {


    public List<CinemaInfo> mCinema = new ArrayList<>();
    private ArrayList<CardView> mCard = new ArrayList<>();
    private ArrayList<RelativeLayout> mLayout = new ArrayList<>();
    private ArrayList<TextView> mText = new ArrayList<>();

    private Context context;

    public AdapterCinema(List<CinemaInfo> cinema, Context context) {
        mCinema = cinema;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView cinemaView;
        public RecyclerView timeView;
        public CardView timeCard;
        public RelativeLayout timeLayout;
        //public TextView timeText;

        public ViewHolder(View itemView) {
            super(itemView);
            cinemaView = itemView.findViewById(R.id.cinema);
            timeView = itemView.findViewById(R.id.recyclerViewTime);
            timeCard = itemView.findViewById(R.id.time_view);
            timeLayout = itemView.findViewById(R.id.time_layout);
            //timeText = itemView.findViewById(R.id.time);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_cinemaitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    //bind data to each item
    public void onBindViewHolder(final ViewHolder holder, int position) {
        CinemaInfo Cinema = mCinema.get(position);
        holder.cinemaView.setText(Cinema.getCinema());

        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(holder.timeView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        holder.timeView.setLayoutManager(layoutManager2);
        AdapterTime adapter2 = new AdapterTime(Cinema.getShowTime(), holder.timeView.getContext());
        holder.timeView.setAdapter(adapter2);


    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return mCinema.size();
    }

}
