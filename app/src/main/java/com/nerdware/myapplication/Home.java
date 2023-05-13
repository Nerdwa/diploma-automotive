package com.nerdware.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        MobileAds.initialize(this);
        AdView mAdView = findViewById(R.id.ban1);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        NetworkChangeReceiver networkChangeReceiver = new NetworkChangeReceiver();
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(networkChangeReceiver, filter);
        // Check for internet connectivity
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            // Disable or hide certain features of your app
            // Show a dialog box to inform the user that they need an internet connection
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("You need an internet connection to use this feature.")
                    .setTitle("No Internet Connection")
                    .setPositiveButton("OK", null);
            AlertDialog dialog = builder.create();
            dialog.show();
        }
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        ArrayList<Item> items = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        items.add(new Item(R.drawable.a,"Module 1"));
        items.add(new Item(R.drawable.b,"Module 2"));
        items.add(new Item(R.drawable.c,"Module  3"));


        MyAdapter myAdapter = new MyAdapter(items,this);
        recyclerView.setAdapter(myAdapter);

        ImageSlider imageSlider = findViewById(R.id.image_slider);

        ArrayList<SlideModel> imagelist = new ArrayList<>();

        imagelist.add(new SlideModel(R.drawable.tdd1, ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.tdd3, ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.a, ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.c, ScaleTypes.FIT));
        imagelist.add(new SlideModel(R.drawable.ma1, ScaleTypes.FIT));

        imageSlider.setImageList(imagelist,ScaleTypes.FIT);

        imageSlider.setItemClickListener(i -> {

            String fileName = "";

            int position = 0;
            switch (position) {
                case 0:
                    fileName = "mao.pdf";
                    break;
                case 1:
                    fileName = "td.pdf";
                    break;
                case 2:
                    fileName = "workshop.pdf";
                    break;
                case 3:
                    fileName = "ict.pdf";
                    break;
                case 4:
                    fileName = "ee.pdf";
                    break;

            }
            Intent intent = new Intent(Home.this, PDF.class);
            intent.putExtra("FILE_NAME", fileName);
            startActivity(intent);
        });
        hom.loadads(Home.this);
    }
}