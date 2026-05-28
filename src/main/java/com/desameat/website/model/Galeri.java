package com.desameat.website.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "galeri")
public class Galeri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String judul;

    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    @Column(nullable = false, name = "url_gambar", length = 255)
    private String urlGambar;

    @Column(nullable = false, length = 50)
    private String kategori = "Umum";

    @Column(nullable = false)
    private LocalDate tanggal = LocalDate.now();

    public Galeri() {}

    public Galeri(String judul, String deskripsi, String urlGambar, String kategori, LocalDate tanggal) {
        this.judul = judul;
        this.deskripsi = deskripsi;
        this.urlGambar = urlGambar;
        this.kategori = kategori;
        if (tanggal != null) {
            this.tanggal = tanggal;
        }
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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getUrlGambar() {
        return urlGambar;
    }

    public String getGambar() {
        return urlGambar;
    }

    public void setUrlGambar(String urlGambar) {
        this.urlGambar = urlGambar;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;
    }
}
