package com.pdfreadergridview.shakil.gridview_pdfreader;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnLoadCompleteListener;

import java.io.File;

public class PDFActivity extends AppCompatActivity {

    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        //init the attributes
        pdfView=findViewById(R.id.pdfView);
        //getting the intent data that passed from the previous activity
        Intent intent=this.getIntent();
        String path=intent.getExtras().getString("PATH");

        //getting the pdf file from the desire path
        File file=new File(path);

        if (file.canRead()){
            //if the file is readable then load the file
            pdfView.fromFile(file).defaultPage(1).onLoad(new OnLoadCompleteListener() {
                @Override
                public void loadComplete(int nbPages) {
                    Toast.makeText(getApplicationContext(),String.valueOf(nbPages),Toast.LENGTH_SHORT).show();
                }
            }).load();
        }
    }
}
