package com.example.micha.linefollower;

/**
 * Created by micha on 15.04.2018.
 */
// Interfejs stworzony w celu przekazania sygnału z handlera do Acitivy
public interface GenericActivitySignalContract {
    void setMessageFromDevice(byte[] messageFromDevice, int length);
}
