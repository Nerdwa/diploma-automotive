package com.nerdware.myapplication;
;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;
import com.github.barteksc.pdfviewer.listener.OnPageErrorListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.io.File;
import java.io.IOException;

public class PDF extends AppCompatActivity {
    private ProgressBar progressBar;
    private String fileName;
    private PDFView pdfView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        ImageView imageView = findViewById(R.id.bak4);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        MobileAds.initialize(this);
         AdView mAdView = findViewById(R.id.pdf);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);

        Toast.makeText(this, "this may take a while.Please be patient", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Ensure your have an internet connection", Toast.LENGTH_LONG).show();

        pdfView = findViewById(R.id.view);
        progressBar = findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.VISIBLE);

        fileName = getIntent().getStringExtra("FILE_NAME");
        final File localFile;
        try {
            localFile = File.createTempFile("robot", "pdf");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        StorageReference reference = FirebaseStorage.getInstance().getReference().child("pdfs/" + fileName);
        Log.v("PDF", "Before getFile() method call");
        reference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                Log.d("PDF", "getFile() method success");
                displaypdf(localFile);
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void displaypdf(File localFile) {
        Uri uri = Uri.fromFile(localFile);
        if (pdfView != null) {
            pdfView.fromUri(uri)
                    .onLoad(new OnLoadCompleteListener() {
                        @Override
                        public void loadComplete(int nbPages) {

                        }
                    })
                    .onPageError(new OnPageErrorListener() {
                        @Override
                        public void onPageError(int page, Throwable t) {

                        }
                    })
                    .load();
        }
    }
}