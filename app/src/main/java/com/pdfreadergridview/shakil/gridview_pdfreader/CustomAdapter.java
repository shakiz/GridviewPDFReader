package com.pdfreadergridview.shakil.gridview_pdfreader;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {


    ArrayList<PDFDoc> pdfDocs;
    Context context;

    public CustomAdapter(ArrayList<PDFDoc> pdfDocs,Context context){
        this.pdfDocs=pdfDocs;
        this.context=context;
    }

    @Override
    public int getCount() {
        return pdfDocs.size();
    }

    @Override
    public Object getItem(int i) {
        return pdfDocs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view==null){
            //if view is null then inflate the custom layout
            view= LayoutInflater.from(context).inflate(R.layout.model,viewGroup,false);
        }
        //creating PDFDoc classes instance
        final PDFDoc pdfDoc= (PDFDoc) this.getItem(i);
        //init the attributes
        TextView nameText=view.findViewById(R.id.nameTxt);
        ImageView img=view.findViewById(R.id.pdfImage);
        //binding tha data
        nameText.setText(pdfDoc.getName());
        img.setImageResource(R.drawable.pdf_icon);
        //setting the onclick event on item
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPDFView(pdfDoc.getPath());
            }
        });
        return view;
    }
    //this method will be used whenever an item of view is clicked
    public void openPDFView(String path) {
        Intent intent=new Intent(context,PDFActivity.class);
        intent.putExtra("PATH",path);
        context.startActivity(intent);
    }
}
