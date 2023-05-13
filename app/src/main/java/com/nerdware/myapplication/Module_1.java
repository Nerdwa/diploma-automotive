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

import com.nerdware.myapplication.module1.Module1Adapter;
import com.nerdware.myapplication.module1.Item1;

import java.util.ArrayList;

public class Module_1 extends AppCompatActivity {

    AdView mAdView, mad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_module1);

        ImageView imageView = findViewById(R.id.bak1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Module_1.this,Home.class);
                startActivity(intent);
            }
        });


        MobileAds.initialize(this);
         mAdView = findViewById(R.id.ban2);
         mad = findViewById(R.id.ban3);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mad.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        RecyclerView recyclerView = findViewById(R.id.mod1_recycler);
        ArrayList<Item1> item1s = new ArrayList<>();

        recyclerView.setLayoutManager(new GridLayoutManager(this,1));

        item1s.add(new Item1(R.drawable.ma1,"Engineering Mathematics "));
        item1s.add(new Item1(R.drawable.tdd1," Engineering Drawing I"));
        item1s.add(new Item1(R.drawable.ict,"ICT /  Entrepreneurship & Communication Skills"));
        item1s.add(new Item1(R.drawable.f,"Mechanical Science && Electrical Principles"));
        item1s.add(new Item1(R.drawable.d,"Workshop Technology"));
        item1s.add(new Item1(R.drawable.e,"Vehicle Technology && Practice"));


        Module1Adapter module1Adapter = new Module1Adapter(item1s,this);
        recyclerView.setAdapter(module1Adapter);

    }
}