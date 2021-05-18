package com.example.opiniaturistului;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;
import android.graphics.Bitmap;
import android.provider.MediaStore;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class v22 extends AppCompatActivity {

    private Button inainteBtn,inapoiBtn;
    int position=0;
    Bitmap bitmap;
    ImageView imageview;
    TextView descriereview;
    private ArrayList<Bitmap>imageBit;
    private ArrayList<String>descriereCuvinte;
    private ArrayList<ModelClass>obiectiveTuristice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.v22);
        imageview=findViewById(R.id.imaginesc);
        inainteBtn = findViewById(R.id.urmatoareaBtn);
        inapoiBtn = findViewById(R.id.anteriorBtn);
        descriereview=findViewById(R.id.textView);
        descriereCuvinte=new ArrayList<>();
        Database sqlite;
        sqlite=new Database(this);
        obiectiveTuristice=new ArrayList<ModelClass>();
        obiectiveTuristice=sqlite.obtToateObiectivele(cauta.getPos());

        imageBit=new ArrayList<>();
        if(obiectiveTuristice!=null) {
            for (ModelClass a : obiectiveTuristice) {
                imageBit.add(a.getImage());
                descriereCuvinte.add(a.getDescriere());
            }
        }
        position=0;
        if(imageBit.get(position)!=null){
        imageview.setImageBitmap(imageBit.get(position));
        descriereview.setText(descriereCuvinte.get(position));
        }
        inapoiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    position = position - 1;
                    imageview.setImageBitmap(imageBit.get(position));
                    descriereview.setText(descriereCuvinte.get(position));
                } else {
                    Toast.makeText(v22.this, "Nu exista o imagine anterioara", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inainteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < imageBit.size() - 1) {
                    position++;
                    imageview.setImageBitmap(imageBit.get(position));
                    descriereview.setText(descriereCuvinte.get(position));
                } else {
                    Toast.makeText(v22.this, "Nu exista mai multe imagini", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}