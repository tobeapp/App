package com.example.ankit.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankit on 29-07-2017.
 */

class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
   private ArrayList<String> mDataset;
    private Context ctx;
    //private ItemData[] itemdata;
    private List<ItemData> itemdata;
    public MainAdapter(ArrayList<String> mDataset) {
        this.mDataset = mDataset;
    }

    public MainAdapter(List<ItemData> itemdata,Context ctc) {
        this.itemdata = itemdata;
        ctx=ctc;
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
        Picasso.with(ctx).load(itemdata.get(position).getImageURL()).into(holder.mimage);
    }

    @Override
    public int getItemCount() {
        return itemdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView mtitle;
        public ImageView mimage;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mtitle= (TextView) itemView.findViewById(R.id.title);
            mimage=(ImageView)itemView.findViewById(R.id.image);
        }

        @Override
        public void onClick(View v) {
          //  System.out.println(mtitle);
            Intent in=new Intent(ctx,Products_Category_Wise.class);
            in.putExtra("For_Reference",mtitle.toString());
            ctx.startActivity(in);

        }

    }
}
