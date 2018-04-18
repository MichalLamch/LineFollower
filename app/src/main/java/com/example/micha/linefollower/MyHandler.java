package com.example.micha.linefollower;

import android.os.Handler;
import android.os.Message;

/**
 * Created by micha on 15.04.2018.
 */

public class MyHandler extends Handler {
    private final static int MESSAGE_ONE = 1;
    private final static int MESSAGE_TWO = 2;
    private GenericActivitySignalContract activityViewContract;

    public void setActivityViewContract(GenericActivitySignalContract activityForUiUpdates) {
        this.activityViewContract = activityForUiUpdates;
    }

    @Override
    public void handleMessage(Message msg) {
        if(msg.what == MESSAGE_ONE) {                             // nie pamiętam, który dokładnie parametr powinien posiadać dane odczytywane z urządzenia
            activityViewContract.setMessageFromDevice(msg.what);  // możesz poeksperymentować z msg.what, msg.arg1, msg.arg2, etc.
        } else if(msg.what == MESSAGE_TWO) {                      // IDE podpowie Ci, jakie metody są dostępne
            activityViewContract.setMessageFromDevice(msg.what);  // tutaj można też wywołać inną metodę z interfejsu, jeśli zostanie stworzona
        }
    }
}
