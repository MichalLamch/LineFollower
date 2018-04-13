package com.example.micha.linefollower;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button b1, b2, b3, b4;
    ImageView ivBluetooth;
    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b1 = (Button) findViewById(R.id.button1);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button3);
        b4 = (Button) findViewById(R.id.button4);
        ivBluetooth = (ImageView) findViewById(R.id.imageView1);
        ivBluetooth.setBackgroundColor(getResources().getColor(R.color.czerwony));
    }

    public void obslugaKlikniecia(View view){
        if(view.getId()==R.id.button1){
            i = new Intent(this, Bluetooth.class);
            startActivity(i);
        }

        //if(view.getId()==R.id.button2){
        //    i = new Intent(this, Parametry.class);
        //    startActivity(i);
       // }

        if(view.getId()==R.id.button3){
            i = new Intent(this, JazdaAutomatyczna.class);
            startActivity(i);
        }

        if(view.getId()==R.id.button4){
            i = new Intent(this, JazdaManualna.class);
            startActivity(i);
        }
    }

}
