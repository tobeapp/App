package com.example.ankit.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 29-07-2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
   private ArrayList<String> mDataset;
    //private ItemData[] itemdata;
    private List<ItemData> itemdata;
    public MainAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    public MainAdapter(List<ItemData> itemdata) {
        this.itemdata = itemdata;
    }

    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v= LayoutInflater.from(parent.getContext())
            .inflate(R.layout.row,parent,false);
        ViewHolder vh=new ViewHolder(v);
        return  vh;
    }

    @Override
    public void onBindViewHolder(MainAdapter.ViewHolder holder, int position) {
        holder.mtitle.setText(itemdata.get(position).getTitle());
        //holder.mimage.setImageResource(itemdata.get(position).getImageURL());
    }

    @Override
    public int getItemCount() {
        return itemdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView mtitle;
        public ImageView mimage;

        public ViewHolder(View itemView) {
            super(itemView);
            mtitle= (TextView) itemView.findViewById(R.id.title);
            mimage=(ImageView)itemView.findViewById(R.id.image);
        }

    }
}
