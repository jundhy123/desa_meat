package com.desameat.website.dto;

public class DashboardStatsDTO {

    private long totalBerita;
    private long beritaPublished;
    private long beritaDraft;
    private long totalPengumuman;
    private long pengumumanAktif;
    private long pengumumanNonaktif;
    private long beritaKategoriBerita;
    private long beritaKategoriPengumuman;
    private long beritaKategoriAcara;
    private long beritaKategoriInformasi;

    public DashboardStatsDTO() {}

    public long getTotalBerita() { return totalBerita; }
    public void setTotalBerita(long totalBerita) { this.totalBerita = totalBerita; }

    public long getBeritaPublished() { return beritaPublished; }
    public void setBeritaPublished(long beritaPublished) { this.beritaPublished = beritaPublished; }

    public long getBeritaDraft() { return beritaDraft; }
    public void setBeritaDraft(long beritaDraft) { this.beritaDraft = beritaDraft; }

    public long getTotalPengumuman() { return totalPengumuman; }
    public void setTotalPengumuman(long totalPengumuman) { this.totalPengumuman = totalPengumuman; }

    public long getPengumumanAktif() { return pengumumanAktif; }
    public void setPengumumanAktif(long pengumumanAktif) { this.pengumumanAktif = pengumumanAktif; }

    public long getPengumumanNonaktif() { return pengumumanNonaktif; }
    public void setPengumumanNonaktif(long pengumumanNonaktif) { this.pengumumanNonaktif = pengumumanNonaktif; }

    public long getBeritaKategoriBerita() { return beritaKategoriBerita; }
    public void setBeritaKategoriBerita(long v) { this.beritaKategoriBerita = v; }

    public long getBeritaKategoriPengumuman() { return beritaKategoriPengumuman; }
    public void setBeritaKategoriPengumuman(long v) { this.beritaKategoriPengumuman = v; }

    public long getBeritaKategoriAcara() { return beritaKategoriAcara; }
    public void setBeritaKategoriAcara(long v) { this.beritaKategoriAcara = v; }

    public long getBeritaKategoriInformasi() { return beritaKategoriInformasi; }
    public void setBeritaKategoriInformasi(long v) { this.beritaKategoriInformasi = v; }
}
