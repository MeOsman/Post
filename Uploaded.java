package com.khanosman.cameraapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import static com.khanosman.cameraapp.MainActivity.mCurrentPhotoPath;
import static com.khanosman.cameraapp.Post.getLocationtxt;
import static com.khanosman.cameraapp.Post.selection;

public class Uploaded extends AppCompatActivity {

    private TextView locationText;
    private TextView des_Incident;
    private TextView successUpload;

    public ListView listView;
   // private TextView location;

    private ImageView capturedImage;
    private Button doneBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uploaded);

        locationText = (TextView)findViewById(R.id.textLocation);
        des_Incident = (TextView)findViewById(R.id.describeIncident);
        final TextView location = (TextView)findViewById(R.id.locationId);
        capturedImage = (ImageView) findViewById(R.id.clickedImage);
        listView = (ListView) findViewById(R.id.checkedList);

        doneBtn =(Button)findViewById(R.id.doneBtn);
        String s= getLocationtxt;
        location.setText(s);



        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,selection);
        listView.setAdapter(adapter);





        //Bring the captured image
        //Toast.makeText(Uploaded.this,"path:"+mCurrentPhotoPath,Toast.LENGTH_SHORT).show();
        capturedImage.setImageBitmap(decodeSampledBitmapFromFile(mCurrentPhotoPath, 120, 120));



        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onBackPressed();


            }
        });
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
        Intent intent = new Intent(Uploaded.this,MainActivity.class);
        startActivity(intent);
    }

}
