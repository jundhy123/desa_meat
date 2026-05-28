package com.desameat.website.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "penduduk")
public class Penduduk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String nik;

    @Column(nullable = false, length = 100)
    private String nama;

    @Column(nullable = false, name = "tanggal_lahir")
    private LocalDate tanggalLahir;

    @Column(nullable = false, name = "jenis_kelamin", length = 15)
    private String jenisKelamin; // "Laki-laki" or "Perempuan"

    @Column(nullable = false, length = 200)
    private String alamat;

    @Column(length = 100)
    private String pekerjaan;

    @Column(name = "status_perkawinan", length = 20)
    private String statusPerkawinan;

    public Penduduk() {}

    public Penduduk(String nik, String nama, LocalDate tanggalLahir, String jenisKelamin, String alamat, String pekerjaan, String statusPerkawinan) {
        this.nik = nik;
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.jenisKelamin = jenisKelamin;
        this.alamat = alamat;
        this.pekerjaan = pekerjaan;
        this.statusPerkawinan = statusPerkawinan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public LocalDate getTanggalLahir() {
         return tanggalLahir;
    }

    public void setTanggalLahir(LocalDate tanggalLahir) {
         this.tanggalLahir = tanggalLahir;
    }

    public String getJenisKelamin() {
         return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
         this.jenisKelamin = jenisKelamin;
    }

    public String getAlamat() {
         return alamat;
    }

    public void setAlamat(String alamat) {
         this.alamat = alamat;
    }

    public String getPekerjaan() {
         return pekerjaan;
    }

    public void setPekerjaan(String pekerjaan) {
         this.pekerjaan = pekerjaan;
    }

    public String getStatusPerkawinan() {
         return statusPerkawinan;
    }

    public void setStatusPerkawinan(String statusPerkawinan) {
         this.statusPerkawinan = statusPerkawinan;
    }
}
