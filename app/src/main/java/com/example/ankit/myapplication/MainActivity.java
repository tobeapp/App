package com.example.ankit.myapplication;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.support.v4.view.ViewPager;
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
import android.widget.VideoView;

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
    private RecyclerView mRecyclerView,mRecyclerView1,mRecylcerView2;
    private static final String TAG="She played the fiddle in an Irish band But she fell in love with an English man";
    FirebaseDatabase database;
    ViewPager mViewPager;
    CustomPagerAdapter mCustomPagerAdapter;//customPagerAdapter;
    DatabaseReference ref;
    MainAdapter adapter,adapter1,adapter2;
   // private ItemData[] itemdata;
    private List<ItemData> itemdata,itemdata1,itemdata2;
    private RecyclerView.LayoutManager mlayoutManager,mlayoutManager1,mlayoutManager2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database=FirebaseDatabase.getInstance();
        ref=database.getReference();

        ////////////////////////////////////////////
        itemdata2=new ArrayList<ItemData>();
        itemdata=new ArrayList<ItemData>();
        itemdata1=new ArrayList<ItemData>();
        /////////////////////////////////////////////
        adapter=new MainAdapter(itemdata,getApplicationContext());
        adapter1=new MainAdapter(itemdata1,getApplicationContext());
        adapter2=new MainAdapter(itemdata2,getApplicationContext());
        ///////////////////////////////////////////////

        mRecyclerView=(RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView1=(RecyclerView)findViewById(R.id.recycler_view1);
        mRecyclerView1.setHasFixedSize(true);
        mRecylcerView2=(RecyclerView)findViewById(R.id.recycler_view2);
        mRecylcerView2.setHasFixedSize(true);
        ///////////////////////////////////////////////////

        mlayoutManager=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mlayoutManager1=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        mlayoutManager2=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);

        ///////////////////////////////////////////////////
        mRecyclerView.setLayoutManager(mlayoutManager);
        mRecyclerView1.setLayoutManager(mlayoutManager1);
        mRecylcerView2.setLayoutManager(mlayoutManager2);
        ///////////////////////////////////////////////////
        mCustomPagerAdapter = new CustomPagerAdapter(this);

        //////////////////////////////////////////////////
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mCustomPagerAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();

////////////////////////////////////////////////////////////////////////////////////////////////////
        mRecyclerView.setAdapter(adapter);
       // mRecyclerView1.setAdapter(adapter);
        ref.child("/Electronics").addValueEventListener(new ValueEventListener() {
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
        //////////////////////////////////////////////////////////////////////////////
        mRecyclerView1.setAdapter(adapter1);
        ref.child("/Medicals").addValueEventListener(new ValueEventListener() {
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
                    itemdata1.add(item);
                    counter++;
                    if(counter== dataSnapshot.getChildrenCount())
                        break;
                }
                adapter1.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ///////////////////////////////////////////////////////////////////////////////////
        mRecylcerView2.setAdapter(adapter2);
        ref.child("/Data").addValueEventListener(new ValueEventListener() {
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
                    itemdata2.add(item);
                    counter++;
                    if(counter== dataSnapshot.getChildrenCount())
                        break;
                }
                adapter2.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
