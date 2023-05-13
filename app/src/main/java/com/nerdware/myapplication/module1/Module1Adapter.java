package com.nerdware.myapplication.module1;

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

public class Module1Adapter extends RecyclerView.Adapter<Module1Adapter.ViewHolder> {
    ArrayList<Item1> item1ArrayList;
    Context context;
    Activity activity;
    int counter = 0;


    public Module1Adapter(ArrayList<Item1> item1ArrayList, Context context) {
        this.item1ArrayList = item1ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mod1,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(item1ArrayList.get(position).getImg());
        holder.textView.setText(item1ArrayList.get(position).getTxt());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                counter ++;
                if (counter == 1) {

                    if (mod1.minterstitialAd != null) {
                        mod1.minterstitialAd.show(activity);
                        mod1.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                mod1.minterstitialAd = null;
                                mod1.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                mod1.minterstitialAd = null;
                                mod1.loadads(activity);
                            }
                        });
                    }
                    counter = 0;
                }
                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "math.pdf";
                        break;
                    case 1:
                        fileName = "td.pdf";
                        break;
                    case 2:
                        fileName = "ict_ee_comms.pdf";
                        break;
                    case 3 :
                        fileName = "ms_eep.pdf";
                        break;
                    case 4 :
                        fileName = "works .pdf";
                        break;
                    case 5 :
                        fileName = "vehicle _tech.pdf";
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
        return item1ArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img1);
            textView = itemView.findViewById(R.id.txt1);
        }
    }
}
