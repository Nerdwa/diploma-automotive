package com.nerdware.myapplication.module2;

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

public class Module2Adapter extends RecyclerView.Adapter<Module2Adapter.ViewHolder> {
    ArrayList<Item2> item2ArrayList;
    Context context;

    Activity activity;
    int counter = 0;

    public Module2Adapter(ArrayList<Item2> item2ArrayList, Context context) {
        this.item2ArrayList = item2ArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.mod2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.imageView.setImageResource(item2ArrayList.get(position).getImag2());
        holder.textView.setText(item2ArrayList.get(position).getText2());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter ++;
                if (counter == 1) {

                    if (mod2.minterstitialAd != null) {
                        mod2.minterstitialAd.show(activity);
                        mod2.minterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback() {
                            @Override
                            public void onAdDismissedFullScreenContent() {
                                super.onAdDismissedFullScreenContent();
                                mod2.minterstitialAd = null;
                                mod2.loadads(activity);
                            }

                            @Override
                            public void onAdFailedToShowFullScreenContent(@NonNull AdError adError) {
                                super.onAdFailedToShowFullScreenContent(adError);
                                mod2.minterstitialAd = null;
                                mod2.loadads(activity);
                            }
                        });
                    }
                    counter = 0;
                }

                int position = holder.getAdapterPosition();
                String fileName = "";

                switch (position) {
                    case 0:
                        fileName = "math2.pdf";
                        break;
                    case 1:
                        fileName = "td2.pdf";
                        break;
                    case 2:
                        fileName = "engine.pdf";
                        break;
                    case 3 :
                        fileName = "industrial.pdf";
                        break;
                    case 4 :
                        fileName = "strength.pdf";
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
        return item2ArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.img2);
            textView = itemView.findViewById(R.id.txt2);
        }
    }
}
