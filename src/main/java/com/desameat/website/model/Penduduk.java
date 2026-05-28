package com.desameat.website.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table(name = "penduduk")
public class Penduduk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nik;
    private String nama;
    private String jenisKelamin; // Laki-laki / Perempuan
    private LocalDate tanggalLahir;
    private String agama;
    private String pendidikan;
    private String pekerjaan;
    private String statusPernikahan;
    private String dusun;

    @Transient
    public int getUsia() {
        if (tanggalLahir == null) return 0;
        return Period.between(tanggalLahir, LocalDate.now()).getYears();
    }
}