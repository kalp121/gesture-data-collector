package com.example.gesture_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity3 extends AppCompatActivity {

    private static int VIDEO_REQUEST=101;
    private Uri videoUri;
    private String path;
    private Button upload;
    private  Button reset;
    private String value;
    private String lastname;
    private String ts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Long tsLong = System.currentTimeMillis()/1000;
        ts = tsLong.toString();
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        lastname=intent.getStringExtra("name");
        path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/MC/upload/"+value+"_"+lastname+"_"+ts+".mp4";



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        captureVideo();

        upload=(Button)findViewById(R.id.upload);
        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadVideo();
            }
        });
        reset=(Button)findViewById(R.id.reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(MainActivity3.this, MainActivity.class);
                MainActivity3.this.startActivity(myIntent);
            }
        });

    }

    private void uploadVideo() {
        class UploadVideo extends AsyncTask<Void, Void, String> {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Intent myIntent = new Intent(MainActivity3.this, MainActivity.class);
               // myIntent.putExtra("key", value);
                //myIntent.putExtra("name","kalp");
                MainActivity3.this.startActivity(myIntent);

            }

            @Override
            protected String doInBackground(Void... params) {
                Upload u = new Upload();
                String msg = u.uploadVideo(path);
                return msg;
            }
        }
        UploadVideo uv = new UploadVideo();
        uv.execute();
    }

    public void captureVideo(){

        Intent videoIntent=new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,5);

        if(videoIntent.resolveActivity(getPackageManager())!=null){
            startActivityForResult(videoIntent,VIDEO_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==VIDEO_REQUEST && resultCode==RESULT_OK){

            try {
                AssetFileDescriptor videoAsset = getContentResolver().openAssetFileDescriptor(data.getData(), "r");
                FileInputStream fis = videoAsset.createInputStream();

                File tmpFile = new File(Environment.getExternalStorageDirectory(),"Download/MC/upload/"+value+"_"+lastname+"_"+ts+".mp4");
                FileOutputStream fos = new FileOutputStream(tmpFile);

                byte[] buf = new byte[1024];
                int len;
                while ((len = fis.read(buf)) > 0) {
                    fos.write(buf, 0, len);
                }
                System.out.println(path);
                fis.close();
                fos.close();
            } catch (IOException io_e) {
                // TODO: handle error
            }

        }
    }
}
