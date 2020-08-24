package com.example.myapplication;
/*
RYAN DUFFY - S1826064
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private ArrayList<ParseItem> parseItems;
    private Context context;

    public ItemAdapter(ArrayList<ParseItem> parseItems, Context context){
        this.parseItems = parseItems;
        this.context = context;
    }

    @Override
    public ItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.alternative_content, parent, false); //parent.getContext()
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ItemAdapter.ViewHolder holder, int position) {
        ParseItem currentItem = parseItems.get(position);

        //holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.tv_Location.setText(currentItem.getTitle());
        holder.tv_Date.setText(currentItem.getDescription());// .substring(0, 10));
        holder.tv_minTemp.setText("Data published on: " + currentItem.getDate());
        holder.tv_maxTemp.setText("See full forecast at: " + currentItem.getLink());
    }

    @Override
    public int getItemCount() {
        return parseItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        //public ImageView mImageView;
        public TextView tv_Location;
        public TextView tv_Date;
        public TextView tv_minTemp;
        public TextView tv_maxTemp;

        public ViewHolder( View itemView) {
            super(itemView);
            //mImageView = itemView.findViewById(R.id.imageView);
            tv_Location = itemView.findViewById(R.id.tv_Location);
            tv_Date = itemView.findViewById(R.id.tv_Date);
            tv_minTemp = itemView.findViewById(R.id.tv_minTemp);
            tv_maxTemp = itemView.findViewById(R.id.tv_maxTemp);
        }
    }
}
