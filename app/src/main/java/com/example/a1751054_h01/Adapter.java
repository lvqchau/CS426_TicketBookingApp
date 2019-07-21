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
import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    //vars
    private ArrayList<String> textOfDay;
    private ArrayList<CardView> mCardView = new ArrayList<>();
    private ArrayList<RelativeLayout> mLayout = new ArrayList<>();
    private ArrayList<String> dayNumber;
    private ArrayList<TextView> mText = new ArrayList<>();
    private ArrayList<TextView> mText2 = new ArrayList<>();

    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfDay;
        TextView dayOfWeek;
        CardView dayView;
        RelativeLayout dayLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            nameOfDay = itemView.findViewById(R.id.daytext);
            dayOfWeek = itemView.findViewById(R.id.day);
            dayView = itemView.findViewById(R.id.day_view);
            dayLayout = itemView.findViewById(R.id.day_layout);
        }
    }




    public Adapter(Context context, ArrayList<String> dayText, ArrayList<String> day) {
        textOfDay = dayText;
        dayNumber = day;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem, parent, false);
        return new ViewHolder(view);
    }

    @Override
    //bind data to each item
    public void onBindViewHolder(final ViewHolder holder,final  int position) {

        holder.nameOfDay.setText(textOfDay.get(position));
        holder.dayOfWeek.setText(dayNumber.get(position));

        holder.dayView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.dayLayout.setBackgroundResource(R.drawable.border_date);

                for (int i=0; i<mCardView.size(); i++) {
                    mLayout.get(i).setBackgroundResource(R.drawable.border_date);
                    mText.get(i).setTextColor(Color.parseColor("#C4C9DF"));
                    mText2.get(i).setTextColor(Color.parseColor("#C4C9DF"));
                }

                mLayout.add(holder.dayLayout);
                mText.add(holder.nameOfDay);
                mText2.add(holder.dayOfWeek);
                mCardView.add(holder.dayView);
                holder.nameOfDay.setTextColor(Color.parseColor("#212224"));
                holder.dayOfWeek.setTextColor(Color.parseColor("#212224"));
                holder.dayLayout.setBackgroundResource(R.drawable.border_date_clicked);
            }
        });

    }


    @Override
    public int getItemCount() {
        return textOfDay.size();
    }


}
