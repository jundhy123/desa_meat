package com.desameat.website.model;

import jakarta.persistence.*;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "profil_desa")
public class ProfilDesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // One to One ke User (hanya user_id di tabel ini)
    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    @OneToMany(mappedBy = "profilDesa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Visi> visi;

    @OneToMany(mappedBy = "profilDesa", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Misi> misi;

    public ProfilDesa() {}

    public ProfilDesa(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Visi> getVisi() {
        return visi;
    }

    public void setVisi(List<Visi> visi) {
        this.visi = visi;
    }

    public List<Misi> getMisi() {
        return misi;
    }

    public void setMisi(List<Misi> misi) {
        this.misi = misi;
    }
}