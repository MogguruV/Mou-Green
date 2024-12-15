package com.example.mougreen;

public class Kritik {

    public String namaUser;
    public String kritikUser;
    public String emailUser;
    public String no_telepon;

    // Default constructor (diperlukan oleh Firestore)
    public Kritik() {}

    // Constructor dengan parameter
    public Kritik(String namaUser, String kritikUser, String emailUser, String no_telepon) {
        this.namaUser = namaUser;
        this.kritikUser = kritikUser;
        this.emailUser = emailUser;
        this.no_telepon = no_telepon;
    }

    // Getter dan Setter
    public String getNamaUser() {
        return namaUser;
    }

    public void setNamaUser(String namaUser) {
        this.namaUser = namaUser;
    }

    public String getKritikUser() {
        return kritikUser;
    }

    public void setKritikUser(String kritikUser) {
        this.kritikUser = kritikUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getNo_telepon() {
        return no_telepon;
    }

    public void setNo_telepon(String no_telepon) {
        this.no_telepon = no_telepon;
    }
}
