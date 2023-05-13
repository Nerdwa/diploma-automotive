package com.nerdware.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.nerdware.myapplication.module3.Item3;
import com.nerdware.myapplication.module3.Module3Adapter;

import java.util.ArrayList;

public class Module_3 extends AppCompatActivity {
    AdView mad, mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module3);

        ImageView imageView = findViewById(R.id.bak3);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module_3.this,Home.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(this);
        mAdView = findViewById(R.id.ban6);
        mad = findViewById(R.id.ban7);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mad.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        RecyclerView recyclerView = findViewById(R.id.mod3_recycler);
        ArrayList<Item3> item3s = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        item3s.add(new Item3(R.drawable.ma3,"Engineering Mathematics III"));
        item3s.add(new Item3(R.drawable.au,"Auto Electrics && Electronics"));
        item3s.add(new Item3(R.drawable.cs,"Control Systems && Instrumentation"));


        Module3Adapter module3Adapter = new Module3Adapter(item3s,this);
        recyclerView.setAdapter(module3Adapter);
    }
}