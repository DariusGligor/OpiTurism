package com.example.opiniaturistului;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Toast;

import androidx.annotation.Nullable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {
    Context context;
    private ByteArrayOutputStream objByte;
    private static String DATABASE_NUME="obiective.db";
    private static int DATA_VERSION=1;
    private byte[]ImageinBytes;

    public static String createTable="create table obiectivinfo2(imageName TEXT"+",descriere TEXT"+",image BLOB"+",recomandare TEXT)";
    public Database(Context context) {
        super(context, DATABASE_NUME, null, DATA_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{

            db.execSQL(createTable);
            Toast.makeText(context,"Tabelul a fost creat cu succes!",Toast.LENGTH_LONG).show();

        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void storeImage(ModelClass obv)
    {   try {
        SQLiteDatabase obj = this.getWritableDatabase();
        Bitmap imageToStoreBitmap = obv.getImage();
        objByte = new ByteArrayOutputStream();
        imageToStoreBitmap.compress(Bitmap.CompressFormat.JPEG, 100,objByte);
        ImageinBytes=objByte.toByteArray();
        ContentValues objectContentValues=new ContentValues();
        objectContentValues.put("ImageName",obv.getNume());
        objectContentValues.put("descriere",obv.getDescriere());
        objectContentValues.put("image",ImageinBytes);
        objectContentValues.put("recomandare",obv.getRecomandare());
        long check=obj.insert("obiectivinfo2",null,objectContentValues);
        if (check!=-1)
        {
            Toast.makeText(context,"Am adaugat cu suuces",Toast.LENGTH_SHORT).show();
            obj.close();
        }
        else{
            Toast.makeText(context,"Am adaugat cu suuces",Toast.LENGTH_SHORT).show();
            obj.close();
        }
    }
    catch (Exception e)
    {
        Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
    }
    }
    public ArrayList<ModelClass> obtToateObiectivele()
    {
        try {
            SQLiteDatabase objSql=this.getReadableDatabase();
            ArrayList<ModelClass>objList=new ArrayList<>();
            Cursor objectCursor=objSql.rawQuery("select* from obiectivinfo2",null);

            if(objectCursor.getCount()!=0)
            {
                while (objectCursor.moveToNext())
                {
                    String nume=objectCursor.getString(0);
                    String descriere=objectCursor.getString(1);
                    byte[]imageBytes=objectCursor.getBlob(2);
                    String recomandare=objectCursor.getString(3);
                    Bitmap objBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    objList.add(new ModelClass(descriere,nume,objBitmap,recomandare));
                }
                objectCursor.close();
                return objList;
            }
            else{
                Toast.makeText(context,"Nu avem obiective turistice",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
        return null;
        }
    }
    public ArrayList<ModelClass> obtToateObiectivele(String numeObv)
    {
        try {
            SQLiteDatabase objSql=this.getReadableDatabase();
            ArrayList<ModelClass>objList=new ArrayList<>();
            Cursor objectCursor=objSql.rawQuery("select* from obiectivinfo2 where imageName is ?", new String[]{numeObv});

            if(objectCursor.getCount()!=0)
            {
                while (objectCursor.moveToNext())
                {
                    String nume=objectCursor.getString(0);
                    String descriere=objectCursor.getString(1);
                    byte[]imageBytes=objectCursor.getBlob(2);
                    String recomandare=objectCursor.getString(3);
                    Bitmap objBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    objList.add(new ModelClass(descriere,nume,objBitmap,recomandare));
                }
                objectCursor.close();
                return objList;
            }
            else{
                Toast.makeText(context,"Nu avem obiective turistice",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }
    }
    public ArrayList<ModelClass> obtrecomandateObiectivele()
    {
        String numeObv="da";
        try {
            SQLiteDatabase objSql=this.getReadableDatabase();
            ArrayList<ModelClass>objList=new ArrayList<>();
            Cursor objectCursor=objSql.rawQuery("select* from obiectivinfo2 where recomandare is ?", new String[]{numeObv});

            if(objectCursor.getCount()!=0)
            {
                while (objectCursor.moveToNext())
                {
                    String nume=objectCursor.getString(0);
                    String descriere=objectCursor.getString(1);
                    byte[]imageBytes=objectCursor.getBlob(2);
                    String recomandare=objectCursor.getString(3);
                    Bitmap objBitmap= BitmapFactory.decodeByteArray(imageBytes,0,imageBytes.length);
                    objList.add(new ModelClass(descriere,nume,objBitmap,recomandare));
                }
                objectCursor.close();
                return objList;
            }
            else{
                Toast.makeText(context,"Nu avem obiective turistice",Toast.LENGTH_SHORT).show();
                return null;
            }
        }
        catch (Exception e)
        {
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            return null;
        }
    }
}