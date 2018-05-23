package com.example.micha.linefollower;

/**
 * Created by micha on 27.03.2018.
 */
// Klasa reprezentująca wyszukane urządzenie Bluetooth
public class Urzadzenia {
    public Urzadzenia(String n, String m)
    {
        nazwa = n;
        adresMac = m;
    }
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdresMac() {
        return adresMac;
    }

    public void setAdresMac(String adresMac) {
        this.adresMac = adresMac;
    }

    private String nazwa;
    private String adresMac;

}
