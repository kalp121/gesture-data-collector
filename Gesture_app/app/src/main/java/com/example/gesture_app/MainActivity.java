package com.example.gesture_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner dropdown;
    private Button select;
    private String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dropdown =(Spinner)findViewById(R.id.dropdown);
        ArrayAdapter<String> gestureAdapter=new ArrayAdapter<String>(MainActivity.this,
                android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gestures));

        gestureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(gestureAdapter);

        select=(Button)findViewById(R.id.select);
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainActivity.this, MainActivity2.class);
                value = dropdown.getSelectedItem().toString();
                myIntent.putExtra("key", value);
                myIntent.putExtra("name","kalp");//Optional parameters
                MainActivity.this.startActivity(myIntent);
            }
        });
    }
}
