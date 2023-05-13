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
import com.nerdware.myapplication.module2.Item2;
import com.nerdware.myapplication.module2.Module2Adapter;

import java.util.ArrayList;

public class Module_2 extends AppCompatActivity {

    AdView mad, mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module2);

        ImageView imageView = findViewById(R.id.bak2);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module_2.this,Home.class);
                startActivity(intent);
            }
        });

        MobileAds.initialize(this);
        mAdView = findViewById(R.id.ban4);
        mad = findViewById(R.id.ban5);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mad.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        RecyclerView recyclerView = findViewById(R.id.mod2_recycler);
        ArrayList<Item2> item2s = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        item2s.add(new Item2(R.drawable.ma2,"Engineering Mathematics II"));
        item2s.add(new Item2(R.drawable.tdd2,"Engineering Drawing && Design II"));
        item2s.add(new Item2(R.drawable.g,"Engine Technology && Body Works"));
        item2s.add(new Item2(R.drawable.d,"Industrial Organisation && Management"));
        item2s.add(new Item2(R.drawable.s,"Strength of Material && Mechanics of Machines"));

        Module2Adapter module2Adapter = new Module2Adapter(item2s,this);
        recyclerView.setAdapter(module2Adapter);

    }
}