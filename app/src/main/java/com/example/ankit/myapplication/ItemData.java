package com.example.ankit.myapplication;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ankit on 29-07-2017.
 */

public class ItemData {
    private String title;
    private String imageURL;
   // private HashMap<String,String> my=new HashMap<String, String>();
   public ItemData() {
   //     title="Gondor";
      //   System.out.println("I the Default Constructor is called");
     //   imageURL="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT56DdYky8Zk9TDADqKiULaLFdr7nOyxu6YE_zHSlJF21TvtcT-WlQdlDmT";
    }

    public ItemData(String imageURL, String title) {
        this.title = title;
        this.imageURL = imageURL;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String  getImageURL() {
        return imageURL;
    }
}
