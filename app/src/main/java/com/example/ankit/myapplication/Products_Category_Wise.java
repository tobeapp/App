package com.example.ankit.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Products_Category_Wise extends AppCompatActivity {
    private final String TAG="I need rest, a lot of rest";
    DatabaseReference ref;
    FirebaseDatabase database;
    private RecyclerView mrecylerView;
    MainAdapter adapter;
    String for_ref;
    private List<ItemData>  itemdata;
    private RecyclerView.LayoutManager mlayoutmanager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products__category__wise);
         for_ref=getIntent().getStringExtra("For_Reference");
        database=FirebaseDatabase.getInstance();
        ref=database.getReference();
      //  Log.d(TAG,for_ref);
       // System.out.println(for_ref);
       // Toast.makeText(Products_Category_Wise.class,for_ref,Toast.LENGTH_LONG).show();
        itemdata=new ArrayList<>();
        adapter=new MainAdapter(itemdata,getApplicationContext());
        mrecylerView=(RecyclerView)findViewById(R.id.recycler_view3);
        mrecylerView.setHasFixedSize(true);
        mlayoutmanager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        mrecylerView.setLayoutManager(mlayoutmanager);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mrecylerView.setAdapter(adapter);
        ref.child("/Accessories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int counter=0;
                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
                    ItemData item=new ItemData();
                    // System.out.println(snapshot.child("Image").getValue().toString());
                    item.setImageURL(snapshot.child("Image").getValue().toString());
                    // System.out.println(item.getImageURL());
                    item.setTitle(snapshot.child("Title").getValue().toString());
                    // System.out.println(item.getTitle());
                    itemdata.add(item);
                    counter++;
                    if(counter== dataSnapshot.getChildrenCount())
                        break;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
