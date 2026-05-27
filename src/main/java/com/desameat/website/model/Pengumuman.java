package com.desameat.website.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "pengumuman")
public class Pengumuman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Judul tidak boleh kosong")
    @Column(nullable = false)
    private String judul;

    @NotBlank(message = "Isi tidak boleh kosong")
    @Column(columnDefinition = "TEXT", nullable = false)
    private String isi;

    @Column(name = "tanggal_mulai")
    private LocalDate tanggalMulai;

    @Column(name = "tanggal_berakhir")
    private LocalDate tanggalBerakhir;

    @Enumerated(EnumType.STRING)
    @Column(name = "prioritas")
    private PrioritasPengumuman prioritas = PrioritasPengumuman.NORMAL;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusPengumuman status = StatusPengumuman.AKTIF;

    @Column(name = "dibuat_oleh")
    private String dibuatOleh;

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

    public enum PrioritasPengumuman {
        RENDAH, NORMAL, TINGGI, URGENT
    }

    public enum StatusPengumuman {
        AKTIF, NONAKTIF, KADALUARSA
    }

    public Pengumuman() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getJudul() { return judul; }
    public void setJudul(String judul) { this.judul = judul; }

    public String getIsi() { return isi; }
    public void setIsi(String isi) { this.isi = isi; }

    public LocalDate getTanggalMulai() { return tanggalMulai; }
    public void setTanggalMulai(LocalDate tanggalMulai) { this.tanggalMulai = tanggalMulai; }

    public LocalDate getTanggalBerakhir() { return tanggalBerakhir; }
    public void setTanggalBerakhir(LocalDate tanggalBerakhir) { this.tanggalBerakhir = tanggalBerakhir; }

    public PrioritasPengumuman getPrioritas() { return prioritas; }
    public void setPrioritas(PrioritasPengumuman prioritas) { this.prioritas = prioritas; }

    public StatusPengumuman getStatus() { return status; }
    public void setStatus(StatusPengumuman status) { this.status = status; }

    public String getDibuatOleh() { return dibuatOleh; }
    public void setDibuatOleh(String dibuatOleh) { this.dibuatOleh = dibuatOleh; }

    public LocalDateTime getTanggalDibuat() { return tanggalDibuat; }
    public void setTanggalDibuat(LocalDateTime tanggalDibuat) { this.tanggalDibuat = tanggalDibuat; }

    public LocalDateTime getTanggalDiupdate() { return tanggalDiupdate; }
    public void setTanggalDiupdate(LocalDateTime tanggalDiupdate) { this.tanggalDiupdate = tanggalDiupdate; }
}
