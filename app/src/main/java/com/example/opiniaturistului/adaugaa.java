package com.example.opiniaturistului;

import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.graphics.Bitmap;
        import android.net.Uri;
        import android.os.Bundle;
        import android.provider.MediaStore;
        import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
        import android.widget.ImageView;
        import android.widget.Toast;

public class adaugaa extends AppCompatActivity {
    private EditText imageDetail,imageName;
    private ImageView objectImageView;
    private static final int PICK_IMAGE_REQUEST=100;
    private Uri imageFilePath;
    private Bitmap imageToStore;
    Database objectDatabase;
    CheckBox recomandarecb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adaugaa);
        try{
            imageDetail=findViewById(R.id.descriere);
            objectImageView=findViewById(R.id.image);
            imageName =findViewById(R.id.nume);
            recomandarecb=findViewById(R.id.checkBox);
            objectDatabase=new Database(this);

        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void chooseImage(View objectView)
    {
        try{
            Intent objectIntent=new Intent();
            objectIntent.setType("image/*");
            objectIntent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(objectIntent,PICK_IMAGE_REQUEST);
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{  super.onActivityResult(requestCode, resultCode, data);
            if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK &&data!=null &&data.getData()!=null) {
                imageFilePath = data.getData();
                imageToStore = MediaStore.Images.Media.getBitmap(getContentResolver(), imageFilePath);
                objectImageView.setImageBitmap(imageToStore);
            }
        }
        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    public void storeImage(View view)
    {
        try {
            if (!imageName.getText().toString().isEmpty()&& objectImageView.getDrawable()!=null&&!imageDetail.getText().toString().isEmpty()&&imageToStore!=null)
                if(recomandarecb.isChecked()==true) {
                    objectDatabase.storeImage(new ModelClass(imageDetail.getText().toString(),imageName.getText().toString(),imageToStore,"da"));
                }
            else {
                    objectDatabase.storeImage(new ModelClass(imageDetail.getText().toString(),imageName.getText().toString(),imageToStore,"nu"));
                }
            else {
                Toast.makeText(this,"Selecteaza o image si adauga descrierea si numele",Toast.LENGTH_SHORT).show();
            }
        }

        catch (Exception e)
        {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
}