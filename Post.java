package com.khanosman.cameraapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static com.khanosman.cameraapp.MainActivity.mCurrentPhotoPath;

public class Post extends AppCompatActivity {

    public static ArrayList<String>selection = new ArrayList<String>();
    public  static  String getLocationtxt;

    private TextView locationText;
    private TextView des_Incident;

   // private  EditText location;

    private ImageView capturedImage;

    private CheckBox highSpeed, danOvertaking,danRoadCross,unsciRoad,danTurn;

    private Button postBtn;

    // Firebase
    private ProgressDialog mProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        locationText = (TextView)findViewById(R.id.textLocation);
        des_Incident = (TextView)findViewById(R.id.describeIncident);
        final EditText location = (EditText)findViewById(R.id.locationId);
        capturedImage = (ImageView) findViewById(R.id.capturedImage);
        highSpeed = (CheckBox)findViewById(R.id.hpId);
        danOvertaking = (CheckBox)findViewById(R.id.doId);
        danRoadCross =(CheckBox)findViewById(R.id.drId);
        unsciRoad =(CheckBox)findViewById(R.id.urId);
        danTurn =(CheckBox)findViewById(R.id.dtId);
        postBtn =(Button)findViewById(R.id.postBtn);

        getLocationtxt = location.getText().toString().trim();

        //Bring the captured image
       // Toast.makeText(Post.this,"path:"+mCurrentPhotoPath,Toast.LENGTH_SHORT).show();
        capturedImage.setImageBitmap(decodeSampledBitmapFromFile(mCurrentPhotoPath, 120, 120));

        //Storing checked values


        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (location != null){
                    Intent intent = new Intent(Post.this,Uploaded.class);
                    startActivity(intent);
                }



            }
        });

    }

    public void selectItem(View v){
        boolean checked = ((CheckBox)v).isChecked();

        switch (v.getId()){
            case R.id.hpId:
                if(checked){
                    String text=highSpeed.getText().toString();
                    selection.add(text);
                }
                break;
            case R.id.doId:
                if(checked){
                    String text=danOvertaking.getText().toString();
                    selection.add(text);
                }
                break;
            case R.id.drId:
                if(checked){
                    String text=danRoadCross.getText().toString();
                    selection.add(text);
                }
                break;
            case R.id.urId:
                if(checked){
                    String text=unsciRoad.getText().toString();
                    selection.add(text);
                }
                break;
            case R.id.dtId:
                if(checked){
                    String text=danTurn.getText().toString();
                    selection.add(text);
                }
                break;


        }

    }


    public static Bitmap decodeSampledBitmapFromFile(String path, int reqWidth, int reqHeight) { // BEST QUALITY MATCH

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);

        // Calculate inSampleSize
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        int inSampleSize = 1;

        if (height > reqHeight) {
            inSampleSize = Math.round((float)height / (float)reqHeight);
        }

        int expectedWidth = width / inSampleSize;

        if (expectedWidth > reqWidth) {
            //if(Math.round((float)width / (float)reqWidth) > inSampleSize) // If bigger SampSize..
            inSampleSize = Math.round((float)width / (float)reqWidth);
        }


        options.inSampleSize = inSampleSize;

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(path, options);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Post.this,MainActivity.class);
        startActivity(intent);
    }


}
