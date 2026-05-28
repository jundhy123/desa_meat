package com.desameat.website.dto;

public class LayananRequest {

    private String jenisSurat;
    private String nik;
    private String namaPemohon;
    private String deskripsiKebutuhan;

    public LayananRequest() {}

    public LayananRequest(String jenisSurat, String nik, String namaPemohon, String deskripsiKebutuhan) {
        this.jenisSurat = jenisSurat;
        this.nik = nik;
        this.namaPemohon = namaPemohon;
        this.deskripsiKebutuhan = deskripsiKebutuhan;
    }

    public String getJenisSurat() {
        return jenisSurat;
    }

    public void setJenisSurat(String jenisSurat) {
        this.jenisSurat = jenisSurat;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaPemohon() {
        return namaPemohon;
    }

    public void setNamaPemohon(String namaPemohon) {
        this.namaPemohon = namaPemohon;
    }

    public String getDeskripsiKebutuhan() {
        return deskripsiKebutuhan;
    }

    public void setDeskripsiKebutuhan(String deskripsiKebutuhan) {
        this.deskripsiKebutuhan = deskripsiKebutuhan;
    }
}
