package com.example.micha.linefollower;

import android.graphics.PorterDuff;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

public class JazdaManualna extends AppCompatActivity implements GenericActivitySignalContract{
    private SeekBar sb_l, sb_p;
    private ImageView s1, s2, s3, s4, s5, s6, s7, s8;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jazda_manualna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        s1 = (ImageView) findViewById(R.id.sensor1);
        s2 = (ImageView) findViewById(R.id.sensor2);
        s3 = (ImageView) findViewById(R.id.sensor3);
        s4 = (ImageView) findViewById(R.id.sensor4);
        s5 = (ImageView) findViewById(R.id.sensor5);
        s6 = (ImageView) findViewById(R.id.sensor6);
        s7 = (ImageView) findViewById(R.id.sensor7);
        s8 = (ImageView) findViewById(R.id.sensor8);
        sb_l = (SeekBar) findViewById(R.id.SeekBarL);
        sb_p = (SeekBar) findViewById(R.id.SeekBarP);
        GivePermissionToStart();
        StopMotors();
        StartReadingSensors();
        sb_l.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            // Puszczenie Seekbar'a - stop danego silnika
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
            // Reackja na zmianę wartości Seekbar'a - wysłanie wiadomości o aktualnie żadanej prędkości
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            if(fromUser){
                byte temp[] = {2,2,0,(byte)(progress)};
                SendMessageToDeviceManual(temp);
            }

            }

        });
        sb_p.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            // Puszczenie Seekbar'a - stop danego silnika
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
            // Reackja na zmianę wartości Seekbar'a - wysłanie wiadomości o aktualnie żadanej prędkości
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                byte temp[] = {2,2,1,(byte)progress};
                SendMessageToDeviceManual(temp);

            }

        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // przypięcie bieżącego activity do Handlera
        ((GenericApplication) this.getApplicationContext()).mMyBluetoothService.setActivityViewContract(this);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        StopMotors();
        DontGivePermissionToStart();
        StopReadingSensors();
    }
    // Funkcja wysyłająca wiadomość do mikrokontrolera
    private void SendMessageToDeviceManual(byte[] message){
        ((GenericApplication)this.getApplicationContext()).mMyBluetoothService.SendMessageToDevice(message);
    }
    // Zatrzymanie obu silników
    private void StopMotors(){
        byte t1[] = {2,2,0,127};
        SendMessageToDeviceManual(t1);
        byte t2[] = {2,2,1,127};
        SendMessageToDeviceManual(t2);
    }
    // Wydanie pozwolenia na jazdę
    private void GivePermissionToStart(){
        byte t1[] = {2,1,0,1};
        SendMessageToDeviceManual(t1);
    }
    // Zabranie pozwolenia na jazdę
    private void DontGivePermissionToStart(){
        byte t1[] = {2,1,0,0};
        SendMessageToDeviceManual(t1);
    }
    // Przerwanie odczytu danych z czujników - zarzymanie timera na mikrokontrolerze, którego przerwania wysyłają wiadomość z danymi
    private void StopReadingSensors(){
        byte t1[] = {2,3,0,0};
        SendMessageToDeviceManual(t1);
    }
    // Rozpoczęcie odczytu danych z czujników
    private void StartReadingSensors(){
        byte t1[] = {2,3,0,1};
        SendMessageToDeviceManual(t1);
    }

    @Override
    // Obsługa wiadomości otrzymanej z mikrokontrolera
    public void setMessageFromDevice(byte[] messageFromDevice, int length) {
        if(length==8){
            Log.d("Test", "test" + messageFromDevice[0]);
            // Zmiana kolorów znaczników
            ChangeColorOfImageView(s1, messageFromDevice[0]);
            ChangeColorOfImageView(s2, messageFromDevice[1]);
            ChangeColorOfImageView(s3, messageFromDevice[3]);
            ChangeColorOfImageView(s4, messageFromDevice[4]);
            ChangeColorOfImageView(s5, messageFromDevice[5]);
            ChangeColorOfImageView(s6, messageFromDevice[6]);
            ChangeColorOfImageView(s7, messageFromDevice[7]);
            ChangeColorOfImageView(s8, messageFromDevice[2]);
        }
    }
    // Funkcja zmieniająca kolory znaczników w zależności od wartości z czujników
    public void ChangeColorOfImageView(ImageView im, byte b){
        if(b>= 0 && b <= 8) {
            im.setColorFilter(getBaseContext().getResources().getColor(R.color.zielony), PorterDuff.Mode.SRC_ATOP);
        } else if (b>8 && b<=15) {
            im.setColorFilter(getBaseContext().getResources().getColor(R.color.zolty), PorterDuff.Mode.SRC_ATOP);
        } else if (b>15 && b<=25) {
            im.setColorFilter(getBaseContext().getResources().getColor(R.color.pomaranczowy), PorterDuff.Mode.SRC_ATOP);
        } else if (b>25 && b<=41) {
            im.setColorFilter(getBaseContext().getResources().getColor(R.color.czerwony), PorterDuff.Mode.SRC_ATOP);
        } else {
            im.setColorFilter(getBaseContext().getResources().getColor(R.color.czarny), PorterDuff.Mode.SRC_ATOP);
        }
    }

}
