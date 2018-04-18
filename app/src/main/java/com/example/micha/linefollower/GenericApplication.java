package com.example.micha.linefollower;

import android.app.Activity;
import android.app.Application;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static android.content.ContentValues.TAG;

/**
 * Created by micha on 15.04.2018.
 */

public class GenericApplication extends Application {
    public MyBluetoothService mMyBluetoothService;
    @Override
    public void onCreate()
    {
        super.onCreate();
        mMyBluetoothService = new MyBluetoothService();
    }
}
