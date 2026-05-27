package com.desameat.website.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "layanan_surat")
public class Layanan {

    @Id
    @Column(name = "id_surat", length = 32)
    private String id; // e.g. SRT-2026-X

    @Column(nullable = false, length = 16)
    private String nik;

    @Column(name = "nama_lengkap", nullable = false, length = 100)
    private String namaLengkap;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false, length = 20)
    private String whatsapp;

    @Column(name = "jenis_surat", nullable = false, length = 100)
    private String jenisSurat;

    @Column(columnDefinition = "TEXT")
    private String keterangan;

    @Column(name = "tanggal_pengajuan")
    private LocalDate tanggalPengajuan;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private StatusSurat status = StatusSurat.PENDING;

    @Column(name = "catatan_admin", columnDefinition = "TEXT")
    private String catatanAdmin;

    public enum StatusSurat {
        PENDING, DIPROSES, SELESAI, DITOLAK
    }

    // Getters and Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getNik() { return nik; }
    public void setNik(String nik) { this.nik = nik; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String name) { this.namaLengkap = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String wat) { this.whatsapp = wat; }
    public String getJenisSurat() { return jenisSurat; }
    public void setJenisSurat(String typ) { this.jenisSurat = typ; }
    public String getKeterangan() { return keterangan; }
    public void setKeterangan(String ket) { this.keterangan = ket; }
    public LocalDate getTanggalPengajuan() { return tanggalPengajuan; }
    public void setTanggalPengajuan(LocalDate d) { this.tanggalPengajuan = d; }
    public StatusSurat getStatus() { return status; }
    public void setStatus(StatusSurat sts) { this.status = sts; }
    public String getCatatanAdmin() { return catatanAdmin; }
    public void setCatatanAdmin(String note) { this.catatanAdmin = note; }
}
