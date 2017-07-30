package com.example.ankit.myapplication;

import android.content.ClipData;
import android.content.Context;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private static final String TAG="She played the fiddle in an Irish band But she fell in love with an English man";
    FirebaseDatabase database;
    DatabaseReference ref;
    MainAdapter adapter;
   // private ItemData[] itemdata;
    private List<ItemData> itemdata;
    private RecyclerView.LayoutManager mlayoutManager;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<String> mDataset;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference("/Data");
        itemdata=new ArrayList<ItemData>();
        adapter=new MainAdapter(itemdata);
        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mlayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mRecyclerView.setLayoutManager(mlayoutManager);

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<ItemData,myViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<ItemData, myViewHolder>(ItemData.class,R.layout.row,myViewHolder.class,ref) {
                    @Override
                    protected void populateViewHolder(myViewHolder viewHolder, ItemData model, int position) {
                        viewHolder.setTitle(model.getTitle());
                        viewHolder.setImage(getApplicationContext(),model.getImageURL());
                    }
                };

               mRecyclerView.setAdapter(firebaseRecyclerAdapter);

     /*   mRecyclerView.setAdapter(adapter);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             //   itemdata.removeAll(itemdata);
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    Log.d(TAG, "populateViewHolder: "+snapshot.getChildren());
                    ItemData item=snapshot.getValue(ItemData.class);
                    System.out.println(item.getTitle()+"********************"+item.getImageURL());
                  //  Log.d(TAG, "populateViewHolder: "+item.getTitle());
                    itemdata.add(item);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
    public static class myViewHolder  extends RecyclerView.ViewHolder{
        View my;

        public myViewHolder(View itemView){
            super(itemView);
            my=itemView;

        }
        public void setTitle(String title){
            TextView title1=(TextView)my.findViewById(R.id.title);
            title1.setText(title);
        }
        public void setImage(Context ctx,String image){
            ImageView img=(ImageView)my.findViewById(R.id.image);
            Picasso.with(ctx).load(image).into(img);
        }

    }
}
