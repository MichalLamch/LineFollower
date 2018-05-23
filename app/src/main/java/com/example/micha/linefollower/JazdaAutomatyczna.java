package com.example.micha.linefollower;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JazdaAutomatyczna extends AppCompatActivity implements GenericActivitySignalContract{
    private EditText predkosc_wpisz, prog_wpisz, kp_wpisz, kd_wpisz, w1_wpisz, w2_wpisz, w3_wpisz, w4_wpisz, w5_wpisz;
    private TextView predkosc_odczytane, prog_odczytane, kp_odczytane, kd_odczytane, w1_odczytane, w2_odczytane, w3_odczytane, w4_odczytane, w5_odczytane;
    private Button predkosc_sprawdz, prog_sprawdz, kp_sprawdz, kd_sprawdz, w1_sprawdz, w2_sprawdz, w3_sprawdz, w4_sprawdz, w5_sprawdz;
    private Button predkosc_ok, prog_ok, kp_ok, kd_ok, w1_ok, w2_ok, w3_ok, w4_ok, w5_ok;
    private Button b_automatyczna_start, b_automatyczna_stop;
    private byte[] t;
    private byte[] t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jazda_automatyczna);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        predkosc_wpisz = (EditText)findViewById(R.id.predkosc_wpisz);
        prog_wpisz = (EditText)findViewById(R.id.prog_wpisz);
        kp_wpisz = (EditText)findViewById(R.id.kp_wpisz);
        kd_wpisz = (EditText)findViewById(R.id.kd_wpisz);
        w1_wpisz = (EditText)findViewById(R.id.w1_wpisz);
        w2_wpisz = (EditText)findViewById(R.id.w2_wpisz);
        w3_wpisz = (EditText)findViewById(R.id.w3_wpisz);
        w4_wpisz = (EditText)findViewById(R.id.w4_wpisz);
        w5_wpisz = (EditText)findViewById(R.id.w5_wpisz);

        predkosc_odczytane = (TextView)findViewById((R.id.predkosc_odczytane));
        prog_odczytane = (TextView)findViewById((R.id.prog_odczytane));
        kp_odczytane = (TextView)findViewById((R.id.kp_odczytane));
        kd_odczytane = (TextView)findViewById((R.id.kd_odczytane));
        w1_odczytane = (TextView)findViewById((R.id.w1_odczytane));
        w2_odczytane = (TextView)findViewById((R.id.w2_odczytane));
        w3_odczytane = (TextView)findViewById((R.id.w3_odczytane));
        w4_odczytane = (TextView)findViewById((R.id.w4_odczytane));
        w5_odczytane = (TextView)findViewById((R.id.w5_odczytane));

        predkosc_sprawdz = (Button)findViewById((R.id.predkosc_sprawdz));
        prog_sprawdz = (Button)findViewById((R.id.prog_sprawdz));
        kp_sprawdz = (Button)findViewById((R.id.kp_sprawdz));
        kd_sprawdz = (Button)findViewById((R.id.kd_sprawdz));
        w1_sprawdz = (Button)findViewById((R.id.w1_sprawdz));
        w2_sprawdz = (Button)findViewById((R.id.w2_sprawdz));
        w3_sprawdz = (Button)findViewById((R.id.w3_sprawdz));
        w4_sprawdz = (Button)findViewById((R.id.w4_sprawdz));
        w5_sprawdz = (Button)findViewById((R.id.w5_sprawdz));

        predkosc_ok = (Button)findViewById((R.id.predkosc_ok));
        prog_ok = (Button)findViewById((R.id.prog_ok));
        kp_ok = (Button)findViewById((R.id.kp_ok));
        kd_ok = (Button)findViewById((R.id.kd_ok));
        w1_ok = (Button)findViewById((R.id.w1_ok));
        w2_ok = (Button)findViewById((R.id.w2_ok));
        w3_ok = (Button)findViewById((R.id.w3_ok));
        w4_ok = (Button)findViewById((R.id.w4_ok));
        w5_ok = (Button)findViewById((R.id.w5_ok));

        b_automatyczna_start = (Button)findViewById((R.id.b_automatyczna_start));
        b_automatyczna_stop = (Button)findViewById((R.id.b_automatyczna_stop));
    }
    public void obslugaKlikniecia(View view) {
        // Przycisk Sprawdź - Sprawdzenie obecnej wartości danej na mikrokontrolerze
        if(view.getId()==R.id.predkosc_sprawdz){
            t1 = new byte[]{1, 10, 0, 0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.prog_sprawdz){
            t1 = new byte[]{1,11,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.kp_sprawdz){
            t1 = new byte[]{1,12,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.kd_sprawdz){
            t1 = new byte[]{1,13,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.w1_sprawdz){
            t1 = new byte[]{1,14,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.w2_sprawdz){
            t1 = new byte[]{1,15,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.w3_sprawdz){
            t1 = new byte[]{1,16,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.w4_sprawdz){
            t1 = new byte[]{1,17,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.w5_sprawdz){
            t1 = new byte[]{1,18,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
        // Przycisk OK - Zaakceptowanie wpisanej wartości i wysłanie do mikrokontrolera
        if(view.getId()==R.id.predkosc_ok){
            if(!predkosc_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(predkosc_wpisz.getText().toString());
                t1 = new byte[]{1,20,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.prog_ok){
            if(!prog_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(prog_wpisz.getText().toString());
                t1 = new byte[]{1,21,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.kp_ok){
            if(!kp_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(kp_wpisz.getText().toString());
                t1 = new byte[]{1,22,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.kd_ok){
            if(!kd_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(kd_wpisz.getText().toString());
                t1 = new byte[]{1,23,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.w1_ok){
            if(!w1_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(w1_wpisz.getText().toString());
                t1 = new byte[]{1,24,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.w2_ok){
            if(!w2_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(w2_wpisz.getText().toString());
                t1 = new byte[]{1,25,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.w3_ok){
            if(!w3_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(w3_wpisz.getText().toString());
                t1 = new byte[]{1,26,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.w4_ok){
            if(!w4_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(w4_wpisz.getText().toString());
                t1 = new byte[]{1,27,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        if(view.getId()==R.id.w5_ok){
            if(!w5_wpisz.getText().toString().equals("")){
                t =  TransformValueTo2Byte(w5_wpisz.getText().toString());
                t1 = new byte[]{1,28,t[0], t[1]};
                SendMessageToDeviceAutomatic(t1);
            }
        }
        // Przyciski start i stop
        if(view.getId()==R.id.b_automatyczna_start){
            t1 = new byte[]{1,1,0,1};
            SendMessageToDeviceAutomatic(t1);
        }
        if(view.getId()==R.id.b_automatyczna_stop){
            t1 = new byte[]{1,1,0,0};
            SendMessageToDeviceAutomatic(t1);
        }
    }

    @Override
    public void setMessageFromDevice(byte[] messageFromDevice, int length) {
        // Odbieranie danych od mikrokontrolera
        if(length==4){
            if(messageFromDevice[0]==1){
                if(messageFromDevice[1]==10){
                    predkosc_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==11){
                    prog_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==12){
                    kp_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==13){
                    kd_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==14){
                    w1_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==15){
                    w2_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==16){
                    w3_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==17){
                    w4_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
                if(messageFromDevice[1]==18){
                    w5_odczytane.setText(Transform2ByteToInt(messageFromDevice[2], messageFromDevice[3]));
                }
            }

        }
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
    }
    // Funkacja wysyłająca wiadomość do mikrokontrolera
    private void SendMessageToDeviceAutomatic(byte[] message){
        ((GenericApplication)this.getApplicationContext()).mMyBluetoothService.SendMessageToDevice(message);
    }
    // Zatrzymanie silników
    private void StopMotors(){
        byte t1[] = {1,1,0,0};
        SendMessageToDeviceAutomatic(t1);
    }
    // Transformacja wartości String do dwuelementowej tablicy bajtów
    private byte[] TransformValueTo2Byte(String val){
        int temp = Integer.parseInt(val);
        if(temp >=0 && temp <=9999){
        int temp1 = temp/100;
        int temp2 = temp-100*temp1;
        byte temp3 = (byte)temp1;
        byte temp4 = (byte)temp2;
        byte t[] = {temp3,temp4};
        return t;
        } else {
            byte t[] = {0,0};
            return t;
        }

    }
    // Transformacja tablicy dwuelementowej bajtów do Stringa
    private String Transform2ByteToInt(byte b1, byte b2){
        int temp = 100*b1+b2;
        return String.valueOf(temp);
    }
}
