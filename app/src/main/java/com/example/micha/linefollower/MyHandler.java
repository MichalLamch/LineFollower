package com.example.micha.linefollower;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by micha on 15.04.2018.
 */
// "Pętla" odczytująca wiadomości wysyłanie gdzieś w projekcie i przechwytująca je w wątku UI
public class MyHandler extends Handler {
    private static final int MESSAGE_READ = 0;
    private GenericActivitySignalContract activityViewContract;

    public void setActivityViewContract(GenericActivitySignalContract activityForUiUpdates) {
        this.activityViewContract = activityForUiUpdates;
    }

    @Override
    // W przypadku otrzymania wiadomości z mikrokontrolera - wywołanie funkcji setMessageFromDevice tak gdzie jest obecnie przypięty handler
    public void handleMessage(Message msg) {
        if(msg.what == MESSAGE_READ) {
            byte[] temp = (byte[])msg.obj;
            int length = msg.arg1;
            activityViewContract.setMessageFromDevice(temp, length);
            Log.d("Odebrana wiadomość", "Dlugosc: " + msg.arg1 + " " +temp[0]+" "+temp[1]+" "+temp[2]+" "+temp[3]+" "+temp[4]+" "+temp[5]+" "+temp[6]+" "+temp[7]);
            // nie pamiętam, który dokładnie parametr powinien posiadać dane odczytywane z urządzenia
              // możesz poeksperymentować z msg.what, msg.arg1, msg.arg2, etc.
        }
    }
}
