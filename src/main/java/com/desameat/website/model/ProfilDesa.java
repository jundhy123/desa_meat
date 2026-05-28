package com.desameat.website.model;

import jakarta.persistence.*;

@Entity
@Table(name = "profil_desa")
public class ProfilDesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String namaDesa = "Meat";

    @Column(nullable = false, columnDefinition = "TEXT")
    private String sejarah;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String visi;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String misi;

    @Column(length = 200)
    private String kepalaDesa;

    @Column(columnDefinition = "TEXT")
    private String deskripsiGeografis;

    @Column(columnDefinition = "TEXT")
    private String alamat;

    // =========================
    // RELASI KE USER (ADMIN PEMBUAT/PEMELIHARA DATA)
    // =========================
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // =========================
    // CONSTRUCTOR
    // =========================
    public ProfilDesa() {
    }

    public ProfilDesa(String sejarah, String visi, String misi,
                      String kepalaDesa, String deskripsiGeografis,
                      String alamat, User user) {
        this.sejarah = sejarah;
        this.visi = visi;
        this.misi = misi;
        this.kepalaDesa = kepalaDesa;
        this.deskripsiGeografis = deskripsiGeografis;
        this.alamat = alamat;
        this.user = user;
    }

    // =========================
    // GETTER & SETTER
    // =========================

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaDesa() {
        return namaDesa;
    }

    public void setNamaDesa(String namaDesa) {
        this.namaDesa = namaDesa;
    }

    public String getSejarah() {
        return sejarah;
    }

    public void setSejarah(String sejarah) {
        this.sejarah = sejarah;
    }

    public String getVisi() {
        return visi;
    }

    public void setVisi(String visi) {
        this.visi = visi;
    }

    public String getMisi() {
        return misi;
    }

    public void setMisi(String misi) {
        this.misi = misi;
    }

    public String getKepalaDesa() {
        return kepalaDesa;
    }

    public void setKepalaDesa(String kepalaDesa) {
        this.kepalaDesa = kepalaDesa;
    }

    public String getDeskripsiGeografis() {
        return deskripsiGeografis;
    }

    public void setDeskripsiGeografis(String deskripsiGeografis) {
        this.deskripsiGeografis = deskripsiGeografis;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}