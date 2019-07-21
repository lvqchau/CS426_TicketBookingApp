package com.example.a1751054_h01;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.a1751054_h01.data.model.Showtime;
import com.muddzdev.styleabletoastlibrary.StyleableToast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterTime extends RecyclerView.Adapter<AdapterTime.ViewHolder> {

    public List<Showtime> mTime = new ArrayList<>();
    private ArrayList<CardView> mCard = new ArrayList<>();
    private ArrayList<RelativeLayout> mLayout = new ArrayList<>();
    private Context context;
    private StyleableToast toast;
    private ArrayList<TextView> mText = new ArrayList<>();

    public AdapterTime(List<Showtime> time, Context context) {
        mTime = time;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView timeOfMovie;
        public CardView timeCard;
        public RelativeLayout timeLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            timeOfMovie = itemView.findViewById(R.id.time);
            timeCard = itemView.findViewById(R.id.time_view);
            timeLayout = itemView.findViewById(R.id.time_layout);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_timeitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    //bind data to each item
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final Showtime time = mTime.get(position);
        holder.timeOfMovie.setText(
                String.format(Locale.US,
                        "%02d:%02d",
                        time.getTime().getHours(), time.getTime().getMinutes())
        );

        if (!time.isAvailable()) {
            holder.timeLayout.setBackgroundResource(R.drawable.border_unavailable);
        }

        holder.timeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (time.isAvailable()) {
                    for (int i = 0; i < mCard.size(); i++) {
                        mLayout.get(i).setBackgroundResource(R.drawable.border_date);
                        mText.get(i).setTextColor(Color.parseColor("#C4C9DF"));
                    }
                    holder.timeOfMovie.setTextColor(Color.parseColor("#5E636A"));
                    holder.timeLayout.setBackgroundResource(R.drawable.border_date_clicked);
                    mCard.add(holder.timeCard);
                    mLayout.add(holder.timeLayout);
                    mText.add(holder.timeOfMovie);
                } else {
                    if (toast != null) {
                        toast.cancel();
                    }
                    toast = StyleableToast.makeText(v.getContext(), "Full reservation!", R.style.toastStyle);
                    toast.show();
                }
            }
        });
    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public int getItemCount() {
        return mTime.size();
    }



}
