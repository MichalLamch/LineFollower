package com.example.micha.linefollower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SeekBar;

public class JazdaManualna extends AppCompatActivity {
    private SeekBar sb_l, sb_p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jazda_manualna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        sb_l = (SeekBar) findViewById(R.id.SeekBarL);
        sb_p = (SeekBar) findViewById(R.id.SeekBarP);
        GivePermissionToStart();
        StopMotors();
        sb_l.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                byte temp[] = {2,2,0,127};
                sb_l.setProgress(127);
                SendMessageToDeviceManual(temp);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                byte temp[] = {2,2,0,(byte)(progress)};
                SendMessageToDeviceManual(temp);
            }

            }

        });
        sb_p.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                byte temp[] = {2,2,1,127};
                sb_p.setProgress(127);
                SendMessageToDeviceManual(temp);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte temp[] = {2,2,1,(byte)progress};
                SendMessageToDeviceManual(temp);

            }

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        StopMotors();
        DontGivePermissionToStart();

    }

    private void SendMessageToDeviceManual(byte[] message){
        ((GenericApplication)this.getApplicationContext()).mMyBluetoothService.SendMessageToDevice(message);
    }
    private void StopMotors(){
        byte t2[] = {2,2,0,127};
        SendMessageToDeviceManual(t2);
        byte t3[] = {2,2,1,127};
        SendMessageToDeviceManual(t3);
    }
    private void GivePermissionToStart(){
        byte t1[] = {2,1,0,1};
        SendMessageToDeviceManual(t1);
    }
    private void DontGivePermissionToStart(){
        byte t4[] = {2,1,0,0};
        SendMessageToDeviceManual(t4);
    }
}
