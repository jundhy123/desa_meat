package com.desameat.website.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "galeri")
public class Galeri {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String judul;

    @Column(length = 1000)
    private String deskripsi;

    private String gambar;
}