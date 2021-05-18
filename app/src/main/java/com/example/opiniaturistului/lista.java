package com.example.opiniaturistului;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;

public class lista extends AppCompatActivity {
    static String pos;
    ListView myList;
    ArrayList<String> list;
    ArrayList<ModelClass>obiectiveTuristice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        myList =(ListView)findViewById(R.id.listarecomandari);
        list =new ArrayList<String>();
        Database sqlite;
        sqlite=new Database(this);
        obiectiveTuristice=new ArrayList<ModelClass>();
        obiectiveTuristice=sqlite.obtrecomandateObiectivele();
        if(obiectiveTuristice!=null) {
            for (ModelClass a : obiectiveTuristice)
              if(verific(list,a.getNume())==true)  list.add(a.getNume());
        }
        ArrayAdapter<String> obvAdapter=new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        myList.setAdapter(obvAdapter);
        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(lista.this,list.get(position)+"Selected",Toast.LENGTH_SHORT).show();
                pos=list.get(position);
                Intent i = new Intent(lista.this,v23.class);
                startActivity(i);
            }
        });

    }

    private boolean verific(ArrayList<String> list1, String nume) {
        for(String a:list1) {
            if(nume.compareTo(a)==0)return false;
        }
        return true;

    }

    public static String getPos1()
    {
        return pos;
    }
}