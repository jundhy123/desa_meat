package com.desameat.website.model;

import jakarta.persistence.*;

@Entity
@Table(name = "wisata")
public class Wisata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nama;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String deskripsi;

    @Column(nullable = false, length = 150)
    private String lokasi;

    @Column(nullable = false)
    private Double rating = 4.8;

    @Column(length = 255)
    private String gambar;

    @Column(name = "fasilitas", length = 200)
    private String fasilitas; // Comma separated tags e.g. "Parkir, Spot Foto"

    public Wisata() {}

    public Wisata(String nama, String deskripsi, String lokasi, Double rating, String gambar, String fasilitas) {
        this.nama = nama;
        this.deskripsi = deskripsi;
        this.lokasi = lokasi;
        this.rating = rating;
        this.gambar = gambar;
        this.fasilitas = fasilitas;
    }

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

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public Double getRating() {
         return rating;
    }

    public void setRating(Double rating) {
         this.rating = rating;
    }

    public String getGambar() {
         return gambar;
    }

    public void setGambar(String gambar) {
         this.gambar = gambar;
    }

    public String getFasilitas() {
         return fasilitas;
    }

    public void setFasilitas(String fasilitas) {
         this.fasilitas = fasilitas;
    }
}
