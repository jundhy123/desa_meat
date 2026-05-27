package com.desameat.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Entity
@Table(name = "berita")
public class Berita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    @Column(nullable = false)
    private String judul;

    @NotBlank(message = "Konten tidak boleh kosong")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String konten;

    @Column(name = "gambar")
    private String gambar;

    @Column(name = "penulis")
    private String penulis;

    @Enumerated(EnumType.STRING)
    @Column(name = "kategori")
    private KategoriBerita kategori;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusBerita status = StatusBerita.DRAFT;

    @Column(name = "tanggal_dibuat")
    private LocalDateTime tanggalDibuat;

    @Column(name = "tanggal_diupdate")
    private LocalDateTime tanggalDiupdate;

    @PrePersist
    protected void onCreate() {
        tanggalDibuat = LocalDateTime.now();
        tanggalDiupdate = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        tanggalDiupdate = LocalDateTime.now();
    }

    public enum KategoriBerita {
        BERITA, PENGUMUMAN, ACARA, INFORMASI
    }

    public enum StatusBerita {
        DRAFT, PUBLISHED, ARCHIVED
    }

    public Berita() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getKonten() { return konten; }
    public void setKonten(String konten) { this.konten = konten; }

    public String getGambar() { return gambar; }
    public void setGambar(String gambar) { this.gambar = gambar; }

    public String getPenulis() { return penulis; }
    public void setPenulis(String penulis) { this.penulis = penulis; }

    public KategoriBerita getKategori() { return kategori; }
    public void setKategori(KategoriBerita kategori) { this.kategori = kategori; }

    public StatusBerita getStatus() { return status; }
    public void setStatus(StatusBerita status) { this.status = status; }

    public LocalDateTime getTanggalDibuat() { return tanggalDibuat; }
    public void setTanggalDibuat(LocalDateTime tanggalDibuat) { this.tanggalDibuat = tanggalDibuat; }

    public LocalDateTime getTanggalDiupdate() { return tanggalDiupdate; }
    public void setTanggalDiupdate(LocalDateTime tanggalDiupdate) { this.tanggalDiupdate = tanggalDiupdate; }
}
