package com.desameat.website.model;

import jakarta.persistence.*;

@Entity
@Table(name = "misi")
public class Misi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer urutan;

    @Lob
    @Column(nullable = false)
    private String isi;

    @ManyToOne
    @JoinColumn(name = "profil_desa_id", nullable = false)
    private ProfilDesa profilDesa;

    public Misi() {}

    public Misi(Integer urutan, String isi, ProfilDesa profilDesa) {
        this.urutan = urutan;
        this.isi = isi;
        this.profilDesa = profilDesa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUrutan() {
        return urutan;
    }

    public void setUrutan(Integer urutan) {
        this.urutan = urutan;
    }

    public String getIsi() {
        return isi;
    }

    public void setIsi(String isi) {
        this.isi = isi;
    }

    public ProfilDesa getProfilDesa() {
        return profilDesa;
    }

    public void setProfilDesa(ProfilDesa profilDesa) {
        this.profilDesa = profilDesa;
    }
}