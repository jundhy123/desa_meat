package com.desameat.website.model;

import java.time.LocalDate;
import java.time.Period;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "penduduk")
public class Penduduk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 16)
    private String nik;

    @Column(nullable = false)
    private String nama;

    @Column(name = "jenis_kelamin", nullable = false)
    @Enumerated(EnumType.STRING)
    private JenisKelamin jenisKelamin;

    @Column(name = "tanggal_lahir", nullable = false)
    private LocalDate tanggalLahir;

    @Column(nullable = false)
    private String agama;

    @Column(nullable = false)
    private String pekerjaan;

    @Column(nullable = false)
    private String pendidikan;

    @Column(name = "status_pernikahan", nullable = false)
    private String statusPernikahan;

    @Column(nullable = false)
    private String alamat;

    public enum JenisKelamin {
        LAKI_LAKI, PEREMPUAN
    }

    // Computed age from tanggalLahir
    @Transient
    public int getUsia() {
        if (tanggalLahir == null) return 0;
        return Period.between(tanggalLahir, LocalDate.now()).getYears();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public JenisKelamin getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(JenisKelamin jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public LocalDate getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(LocalDate tanggalLahir) { this.tanggalLahir = tanggalLahir; }

    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }

    public String getPekerjaan() { return pekerjaan; }
    public void setPekerjaan(String pekerjaan) { this.pekerjaan = pekerjaan; }

    public String getPendidikan() { return pendidikan; }
    public void setPendidikan(String pendidikan) { this.pendidikan = pendidikan; }

    public String getStatusPernikahan() { return statusPernikahan; }
    public void setStatusPernikahan(String statusPernikahan) { this.statusPernikahan = statusPernikahan; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
}