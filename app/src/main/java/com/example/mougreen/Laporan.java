package com.example.mougreen;

public class Laporan {
    private String nama;
    private String laporan;
    private String imageUrl;

    public Laporan() {
        // Diperlukan untuk Firestore
    }

    public Laporan(String nama, String laporan, String imageUrl) {
        this.nama = nama;
        this.laporan = laporan;
        this.imageUrl = imageUrl;
    }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getLaporan() { return laporan; }
    public void setLaporan(String laporan) { this.laporan = laporan; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
