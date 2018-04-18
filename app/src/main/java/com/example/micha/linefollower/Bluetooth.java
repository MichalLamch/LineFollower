package com.example.micha.linefollower;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothClass;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity {

    private BluetoothAdapter mBluetoothAdapter;
    private Button b5, b6, b7, b10, b11;
    private TextView tv1, tv2;
    private AdapterUrzadzenia adapter;
    private ListView lv;
    private ArrayList<Urzadzenia> urzadzenia = new ArrayList<Urzadzenia>();
    private BluetoothSocket mBluetoothSocket;
    private static final UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private BluetoothDevice mBluetoothDevice = null;
    private GenericApplication mGenericApplication = null;
    // #defines for identifying shared types between calling functions

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        b5 = (Button) findViewById(R.id.button5);
        b6 = (Button) findViewById(R.id.button6);
        b7 = (Button) findViewById(R.id.button7);
        b10 = (Button) findViewById(R.id.button10);
        b11 = (Button) findViewById(R.id.button11);
        lv = (ListView) findViewById(R.id.listView1);
        tv1 = (TextView) findViewById(R.id.wybraneUrzadzenieNazwa);
        tv2 = (TextView) findViewById(R.id.wybraneUrzadzenieMac);
        adapter = new AdapterUrzadzenia(this, 0, urzadzenia);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                tv1.setText(urzadzenia.get(i).getNazwa());
                tv2.setText(urzadzenia.get(i).getAdresMac());

                //showToast("Wybrano: " + urzadzenia.get(i).getNazwa());
                //Intent startActivityCustomList = new Intent(Bluetooth.this, MainActivity.class);
                //startActivity(startActivityCustomList);


            }
        });
        int MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION = 1;
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                MY_PERMISSIONS_REQUEST_ACCESS_COARSE_LOCATION);



        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onResume() {
        if (mBluetoothAdapter.isEnabled()) {
            b5.setBackgroundColor(getResources().getColor(R.color.zielony));
        } else {
            b5.setBackgroundColor(getResources().getColor(R.color.czerwony));
        }
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mBluetoothAdapter != null) {
            mBluetoothAdapter.cancelDiscovery();
        }
        if (mReceiver.getAbortBroadcast()) {
            this.unregisterReceiver(mReceiver);
        }

    }

    public void obslugaKliknieciaBluetooth(View view) {
        if (view.getId() == R.id.button5) {
            if (mBluetoothAdapter == null) {
                Log.d("Bluetooth", "Błąd połączenia z hardware'm");
                throw new AssertionError();
            }
            if (!mBluetoothAdapter.isEnabled()) {
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 1);
                Log.d("Bluetooth", "Połączono z hardware'm");
            }
        }
        if (view.getId() == R.id.button6) {
            urzadzenia.clear();
            mBluetoothAdapter.cancelDiscovery();
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                // There are paired devices. Get the name and address of each paired device.

                for (BluetoothDevice device : pairedDevices) {
                    urzadzenia.add(new Urzadzenia(device.getName(), device.getAddress()));
                    String deviceName = device.getName();
                    String deviceHardwareAddress = device.getAddress(); // MAC address
                    Log.d("Sparowane urządzenia", deviceName + " " + deviceHardwareAddress);
                }
                adapter.notifyDataSetChanged();
            }
        }
        if (view.getId() == R.id.button7) {
            Log.d("Bluetooth", "Szukam innych urządzeń (ok 12s)");
            urzadzenia.clear();
            adapter.notifyDataSetChanged();
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
            filter.addAction(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
            registerReceiver(mReceiver, filter);
            mBluetoothAdapter.startDiscovery();
        }
        if (view.getId() == R.id.button11) {
            mBluetoothAdapter.cancelDiscovery();
            mBluetoothDevice = mBluetoothAdapter.getRemoteDevice(tv2.getText().toString());
            //showToast("Laczenie z: " + tv1.getText());
            try {
                mBluetoothSocket = mBluetoothDevice.createRfcommSocketToServiceRecord(MY_UUID);
                mBluetoothSocket.connect();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //mMyBluetoothService = new MyBluetoothService();
            //mConnectedThread = mMyBluetoothService.new ConnectedThread(mBluetoothSocket);
            //(new Thread(mConnectedThread)).start();
            if(mBluetoothSocket.isConnected()){
                ((GenericApplication)this.getApplicationContext()).mMyBluetoothService.StartConnectedThread(mBluetoothSocket);
                showToast("Połączono!");
                //Intent i =  new Intent(this, MainActivity.class);
                //startActivity(i);

            } else {
                showToast("Błąd połączenia");
            }


        }
        if (view.getId() == R.id.button10) {
            byte[] start = {1, 1, 10, 0};
            Log.d("BletoothWrite", "Wysłano1");
            ((GenericApplication)this.getApplicationContext()).mMyBluetoothService.SendMessageToDevice(start);



        }

    }

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Discovery has found a device. Get the BluetoothDevice
                // object and its info from the Intent.
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                urzadzenia.add(new Urzadzenia(device.getName(), device.getAddress()));
                Log.d("Bluetooth", "Znaleziono:" + device.getName() + " " + device.getAddress());
                adapter.notifyDataSetChanged();
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)) {
                showToast("Wyszukiwanie...");
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {
                showToast("Wyszukiwanie zakończone");
            }
        }
    };

    private void showToast(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }


}
