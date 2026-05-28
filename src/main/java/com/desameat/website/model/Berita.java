package com.desameat.website.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "berita")
public class Berita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String judul;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String konten;

    @Column(nullable = false)
    private LocalDate tanggal = LocalDate.now();

    @Column(length = 255)
    private String gambar;

    @Column(nullable = false, length = 50)
    private String kategori;

    @Column(nullable = false, length = 100)
    private String penulis = "Admin Desa Meat";

    public Berita() {}

    public Berita(String judul, String konten, LocalDate tanggal, String gambar, String kategori, String penulis) {
        this.judul = judul;
        this.konten = konten;
        if (tanggal != null) {
            this.tanggal = tanggal;
        }
        this.gambar = gambar;
        this.kategori = kategori;
        this.penulis = penulis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getKonten() {
        return konten;
    }

    public void setKonten(String konten) {
        this.konten = konten;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
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

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
}
