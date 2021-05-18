
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

public class v23 extends AppCompatActivity {

    private Button inainteBtn,inapoiBtn;
    int position=0;
    Bitmap bitmap;
    ImageView imageview;
    TextView textView;
    private ArrayList<Bitmap>imageBit;
    private ArrayList<String>descriereList;
    private ArrayList<ModelClass>obiectiveTuristice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_v23);
        imageview=findViewById(R.id.imaginesc);
        inainteBtn = findViewById(R.id.urmatoareaBtn);
        inapoiBtn = findViewById(R.id.anteriorBtn);
        textView=findViewById(R.id.textView);
        Database sqlite;
        sqlite=new Database(this);
        obiectiveTuristice=new ArrayList<ModelClass>();
        descriereList=new ArrayList<>();
        obiectiveTuristice=sqlite.obtToateObiectivele(lista.getPos1());

        imageBit=new ArrayList<>();
        if(obiectiveTuristice!=null) {
            for (ModelClass a : obiectiveTuristice) {
                imageBit.add(a.getImage());
                descriereList.add(a.getDescriere());
            }
        }
        position=0;
        Toast.makeText(v23.this, "asd"+position , Toast.LENGTH_SHORT).show();
        if(imageBit.get(position)!=null){
            imageview.setImageBitmap(imageBit.get(position));
            textView.setText(descriereList.get(position));
        }
        inapoiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position > 0) {
                    position = position - 1;
                    textView.setText(descriereList.get(position));
                    imageview.setImageBitmap(imageBit.get(position));
                } else {
                    Toast.makeText(v23.this, "Nu exista o imagine anterioara", Toast.LENGTH_SHORT).show();
                }
            }
        });
        inainteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position < imageBit.size() - 1) {
                    position++;
                    textView.setText(descriereList.get(position));
                    imageview.setImageBitmap(imageBit.get(position));
                } else {
                    Toast.makeText(v23.this, "Nu exista mai multe imagini", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}