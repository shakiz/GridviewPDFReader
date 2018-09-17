package com.pdfreadergridview.shakil.gridview_pdfreader;

import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    GridView gridView;
    LinearLayout linearLayout;
    TextView msg,msg2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init the gridview
        gridView=findViewById(R.id.gv);
        //init the layout main
        linearLayout=findViewById(R.id.mainLayout);
        msg=findViewById(R.id.message);
        msg2=findViewById(R.id.message2);
        //setting the adapter
        gridView.setAdapter(new CustomAdapter(getPDFs(),MainActivity.this));
    }


    public ArrayList<PDFDoc> getPDFs() {
        ArrayList<PDFDoc> pdfDocs=new ArrayList<>();
        File downloadsFolder = new File(Environment.getExternalStorageDirectory() + "/Documents");
        msg.setText("Folder:"+downloadsFolder);
        PDFDoc pdfDoc;
        if (downloadsFolder.exists()){
            File[] files=downloadsFolder.listFiles();
            msg2.setText("Files:"+files);
            try{
                for (int i=0;i<files.length;i++){
                    File file=files[i];
                    if (file.getPath().endsWith("pdf")){
                        pdfDoc=new PDFDoc();
                        pdfDoc.setName(file.getName());
                        pdfDoc.setPath(file.getAbsolutePath());
                        pdfDocs.add(pdfDoc);
                        Log.d("PATH",""+file.getPath());
                    }
                }
            }catch (Exception e){
                //Toast.makeText(getApplicationContext(),"Error : "+e.getMessage(),Toast.LENGTH_SHORT).show();
                Snackbar.make(linearLayout,"Error : "+e.getMessage(),Snackbar.LENGTH_LONG).show();
            }
        }
        else{
            Snackbar.make(linearLayout,"Folder does not exists.",Snackbar.LENGTH_LONG).show();
        }
        return pdfDocs;
    }
}
