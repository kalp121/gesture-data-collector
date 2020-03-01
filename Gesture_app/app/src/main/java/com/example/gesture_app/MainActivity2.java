package com.example.gesture_app;

import android.Manifest;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity2 extends AppCompatActivity {
    private static final int PERMISSION_STORAGE_CODE=1000;
    private TextView text1;
    private VideoView videoView;
    private String value;
    private Boolean downloaded=Boolean.FALSE;
    private MediaController mediaController;
    private String url;
    private Button practice;
    private  String lastname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Intent intent = getIntent();
        value = intent.getStringExtra("key");
        lastname=intent.getStringExtra("name");
        text1=(TextView)findViewById(R.id.text1);
        text1.setText(value+"  Gesture");

        practice=(Button)findViewById(R.id.practice);
        practice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity2.this, MainActivity3.class);
                myIntent.putExtra("key", value);
                myIntent.putExtra("name","kalp");
                MainActivity2.this.startActivity(myIntent);}
                });

        videoView=(VideoView)findViewById(R.id.video);
        url= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath()+"/MC/download/"+value+"_gesture_practice_"+lastname+".mp4";
        File tmpFile = new File(Environment.getExternalStorageDirectory(),"Download/MC/upload");
            if (!tmpFile.exists()) {Boolean tp = tmpFile.mkdirs();}

        Handler handler = new Handler();
        mediaController = new
                MediaController(this);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                videoView.setVideoURI(Uri.parse(url));


                mediaController.setAnchorView(videoView);
                videoView.setMediaController(mediaController);
                videoView.start();
            }
        }, 1500);

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

            if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED){
                String[] permissions={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                requestPermissions(permissions,PERMISSION_STORAGE_CODE);
            }
            else{
                download();
            }
        }
        else{
            download();
        }





    }


    private void download(){
        String url=getLink(value);
        DownloadManager.Request request= new DownloadManager.Request(Uri.parse(url));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file...");
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,"/MC/download/"+value+"_gesture_practice_"+lastname+".mp4");
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager manager=(DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);

        manager.enqueue(request);
        //downloaded=Boolean.TRUE;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PERMISSION_STORAGE_CODE:{
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                    download();
                }
                else{
                    Toast.makeText(this,"Permission Denied..!",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


    private String getLink(String ges){
        String link="";

        switch(ges){

            case "gift":
                link="https://www.signingsavvy.com/media/mp4-ld/23/23781.mp4";
                break;
            case "car":
                link="https://www.signingsavvy.com/media/mp4-ld/26/26165.mp4";
                break;
            case "pay":
                link="https://www.signingsavvy.com/media/mp4-ld/14/14618.mp4";
                break;
            case "pet":
                link="https://www.signingsavvy.com/media/mp4-ld/25/25066.mp4";
                break;
            case "sell":
                link="https://www.signingsavvy.com/media/mp4-ld/9/9199.mp4";
                break;
            case "explain":
                link="https://www.signingsavvy.com/media/mp4-ld/14/14366.mp4";
                break;
            case "that":
                link="https://www.signingsavvy.com/media/mp4-ld/14/14326.mp4";
                break;
            case "book":
                link="https://www.signingsavvy.com/media/mp4-ld/7/7774.mp4";
                break;
            case "now":
                link="https://www.signingsavvy.com/media/mp4-ld/14/14523.mp4";
                break;
            case "work":
                link="https://www.signingsavvy.com/media/mp4-ld/26/26467.mp4";
                break;
            case "total":
                link="https://www.signingsavvy.com/media/mp4-ld/9/9117.mp4";
                break;
            case "trip":
                link="https://www.signingsavvy.com/media/mp4-ld/14/14736.mp4";
                break;
            case "future":
                link="https://www.signingsavvy.com/media/mp4-ld/21/21534.mp4";
                break;
            case "good":
                link="https://www.signingsavvy.com/media/mp4-ld/21/21533.mp4";
                break;
            case "thank you":
                link="https://www.signingsavvy.com/media/mp4-ld/21/21560.mp4";
                break;
            case "learn":
                link="https://www.signingsavvy.com/media/mp4-ld/8/8626.mp4";
                break;
            case "agent":
                link="https://www.signingsavvy.com/media/mp4-ld/8/8626.mp4";
                break;
            case "should":
                link="https://www.signingsavvy.com/media/mp4-ld/9/9563.mp4";
                break;
            case "like":
                link="https://www.signingsavvy.com/media/mp4-ld/6/6394.mp4";
                break;
            case "movie":
                link="https://www.signingsavvy.com/media/mp4-ld/8/8626.mp4";
                break;
            default:
                link="";
                break;
        }

        return link;

    }
}
