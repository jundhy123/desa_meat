package com.desameat.website.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "umkm")
public class Umkm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaUsaha;

    private String pemilik;

    @Column(length = 1000)
    private String deskripsi;

    private String alamat;
}