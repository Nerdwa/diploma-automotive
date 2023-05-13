package com.nerdware.myapplication.module3;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.nerdware.myapplication.PDF;
import com.nerdware.myapplication.R;

import java.util.ArrayList;

public class Module3Adapter extends RecyclerView.Adapter<Module3Adapter.ViewHolder> {

    ArrayList<Item3> item3ArrayList;
    Context context;

    Activity activity;
    int counter = 0;
    public Module3Adapter(ArrayList<Item3> item3ArrayList, Context context) {
        this.item3ArrayList = item3ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mod3,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(item3ArrayList.get(position).getImg3());
        holder.textView.setText(item3ArrayList.get(position).text3);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter ++;
                if (counter == 1) {

                    if (mod3.minterstitialAd != null) {
                        mod3.minterstitialAd.show(activity);
                        mod3.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                mod3.minterstitialAd = null;
                                mod3.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                mod3.minterstitialAd = null;
                                mod3.loadads(activity);
                            }
                        });
                    }
                    counter = 0;
                }

                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "math3.pdf";
                        break;
                    case 1:
                        fileName = "auto.pdf";
                        break;
                    case 2:
                        fileName = "control.pdf";
                        break;


                }
                Intent intent = new Intent(v.getContext(), PDF.class);
                intent.putExtra("FILE_NAME", fileName);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return item3ArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img3);
            textView = itemView.findViewById(R.id.txt3);
        }
    }
}
