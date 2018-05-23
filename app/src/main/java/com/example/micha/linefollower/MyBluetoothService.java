package com.example.micha.linefollower;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by micha on 08.04.2018.
 */

public class MyBluetoothService {
    private static final String TAG = "MY_APP_DEBUG_TAG";
    private MyHandler mMyHandler; // handler that gets info from Bluetooth service
    private ConnectedThread mConnectedThread;
    // Defines several constants used when transmitting messages between the
    // service and the UI.
    private interface MessageConstants {
        public static final int MESSAGE_READ = 0;
        // ... (Add other message types here as needed.)
    }
    public void setActivityViewContract(GenericActivitySignalContract activity) {
        mMyHandler.setActivityViewContract(activity);
    }
    // Start wątku obsługi wiadomości Bluetooth
    public void StartConnectedThread(BluetoothSocket mBluetoothSocket){
        mConnectedThread = new ConnectedThread(mBluetoothSocket);
        (new Thread(mConnectedThread)).start();
        this.mMyHandler= new MyHandler();
    }
    // Stop wątku
    public void StopConnectedThread(){
        if(!(mConnectedThread==null)){
            mConnectedThread.cancel();
        }
    }
    // Sprawdzanie połączenia z urządzeniem
    public boolean checkConnection(){
        if(!(mConnectedThread==null)){
        return mConnectedThread.ifSocketIsConnected();}
        else return false;
    }
    // Wysłanie wiadomości do mikrokontrolera
    public void SendMessageToDevice(byte[] message){
            mConnectedThread.write(message);

    }
    // Klasa ConnectedThread - wątek obsługi wiadomości
    private class ConnectedThread extends Thread {
        private final BluetoothSocket mmSocket;
        private final InputStream mmInStream;
        private final OutputStream mmOutStream;
        private byte[] mmBuffer; // mmBuffer store for the stream

        public ConnectedThread(BluetoothSocket socket) {
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the input and output streams; using temp objects because
            // member streams are final.
            try {
                tmpIn = socket.getInputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating input stream", e);
            }
            try {
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when creating output stream", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        public void run() {
            mmBuffer = new byte[100];
            int numBytes; // bytes returned from read()

            // Keep listening to the InputStream until an exception occurs.
            while (true) {
                try {
                    // Read from the InputStream.
                    numBytes = mmInStream.read(mmBuffer);
                    // Send the obtained bytes to the UI activity.
                    Message readMsg = mMyHandler.obtainMessage(
                            MessageConstants.MESSAGE_READ, numBytes, -1, mmBuffer);
                    readMsg.sendToTarget();
                } catch (IOException e) {
                    Log.d(TAG, "Input stream was disconnected", e);
                    break;
                }
            }
        }

        // Call this from the main activity to send data to the remote device.
        public void write(byte[] bytes) {
            try {
                mmOutStream.write(bytes);
                Log.d("BletoothWrite", "Wysłano");
            } catch (IOException e) {
                Log.e(TAG, "Error occurred when sending data", e);
            }
        }

        // Call this method from the main activity to shut down the connection.
        public void cancel() {
            try {
                mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "Could not close the connect socket", e);
            }
        }

        public boolean ifSocketIsConnected(){
            return mmSocket.isConnected();
        }
    }
}