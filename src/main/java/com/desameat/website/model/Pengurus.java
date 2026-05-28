package com.desameat.website.model;

public class Pengurus {
    private String jabatan;
    private String nama;

    public Pengurus(String jabatan, String nama) {
        this.jabatan = jabatan;
        this.nama = nama;
    }

    // Getter dan Setter
    public String getJabatan() { return jabatan; }
    public void setJabatan(String jabatan) { this.jabatan = jabatan; }
    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }
}