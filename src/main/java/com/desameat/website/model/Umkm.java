package com.desameat.website.model;

import jakarta.persistence.*;

@Entity
@Table(name = "umkm")
public class Umkm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "nama_produk", length = 100)
    private String namaProduk;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String deskripsi;

    @Column(nullable = false)
    private Double harga = 0.0;

    @Column(nullable = false, length = 100, name = "pembuat")
    private String pembuat;

    @Column(nullable = false, length = 30)
    private String kontak;

    @Column(length = 255)
    private String gambar;

    @Column(nullable = false, length = 50)
    private String kategori;

    @Column(nullable = false)
    private Double rating = 4.8;

    public Umkm() {}

    public Umkm(String namaProduk, String deskripsi, Double harga, String pembuat, String kontak, String gambar, String kategori, Double rating) {
        this.namaProduk = namaProduk;
        this.deskripsi = deskripsi;
        if (harga != null) this.harga = harga;
        this.pembuat = pembuat;
        this.kontak = kontak;
        this.gambar = gambar;
        this.kategori = kategori;
        if (rating != null) this.rating = rating;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public Double getHarga() {
        return harga;
    }

    public void setHarga(Double harga) {
        this.harga = harga;
    }

    public String getPembuat() {
        return pembuat;
    }

    public void setPembuat(String pembuat) {
        this.pembuat = pembuat;
    }

    public String getKontak() {
        return kontak;
    }

    public void setKontak(String kontak) {
        this.kontak = kontak;
    }

    public String getGambar() {
        return gambar;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
