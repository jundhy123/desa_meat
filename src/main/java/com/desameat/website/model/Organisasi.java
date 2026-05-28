package com.desameat.website.model;

import jakarta.persistence.*;

@Entity
@Table(name = "organisasi")
public class Organisasi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nama;

    @Column(length = 20)
    private String singkatan;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    @Column(nullable = false, length = 100)
    private String ketua;

    @Column(name = "jumlah_anggota", nullable = false)
    private Integer jumlahAnggota = 0;

    @Column(length = 50)
    private String kategori;

    public Organisasi() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getSingkatan() {
        return singkatan;
    }

    public void setSingkatan(String singkatan) {
        this.singkatan = singkatan;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getKetua() {
        return ketua;
    }

    public void setKetua(String ketua) {
        this.ketua = ketua;
    }

    public Integer getJumlahAnggota() {
        return jumlahAnggota;
    }

    public void setJumlahAnggota(Integer jumlahAnggota) {
        this.jumlahAnggota = jumlahAnggota;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
