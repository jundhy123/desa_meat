package com.desameat.website.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desameat.website.model.Penduduk;
import com.desameat.website.repository.PendudukRepository;

@Service
public class PendudukService {

    @Autowired
    private PendudukRepository pendudukRepository;

    // ─── CRUD ────────────────────────────────────────────────

    public List<Penduduk> getAllPenduduk() {
        return pendudukRepository.findAll();
    }

    public Optional<Penduduk> getById(Long id) {
        return pendudukRepository.findById(id);
    }

    public List<Penduduk> cariByNama(String nama) {
        return pendudukRepository.findByNamaContainingIgnoreCase(nama);
    }

    public void simpan(Penduduk penduduk) {
        pendudukRepository.save(penduduk);
    }

    public void hapus(Long id) {
        pendudukRepository.deleteById(id);
    }

    public boolean nikSudahAda(String nik) {
        return pendudukRepository.existsByNik(nik);
    }

    // ─── STATISTIK ───────────────────────────────────────────

    public long totalPenduduk() {
        return pendudukRepository.count();
    }

    public long totalLakiLaki() {
        return pendudukRepository.countByJenisKelamin(Penduduk.JenisKelamin.LAKI_LAKI);
    }

    public long totalPerempuan() {
        return pendudukRepository.countByJenisKelamin(Penduduk.JenisKelamin.PEREMPUAN);
    }

    public Map<String, Object> getStatistik() {
        Map<String, Object> stat = new LinkedHashMap<>();

        stat.put("total", totalPenduduk());
        stat.put("lakiLaki", totalLakiLaki());
        stat.put("perempuan", totalPerempuan());

        stat.put("agama", toMap(pendudukRepository.countByAgama()));
        stat.put("pendidikan", toMap(pendudukRepository.countByPendidikan()));
        stat.put("pekerjaan", toMap(pendudukRepository.countByPekerjaan()));
        stat.put("statusPernikahan", toMap(pendudukRepository.countByStatusPernikahan()));
        stat.put("kelompokUsia", toMap(pendudukRepository.countByKelompokUsia()));

        return stat;
    }

    // Helper: convert Object[] query result → LinkedHashMap<label, count>
    private Map<String, Long> toMap(List<Object[]> rows) {
        Map<String, Long> map = new LinkedHashMap<>();
        for (Object[] row : rows) {
            map.put(String.valueOf(row[0]), ((Number) row[1]).longValue());
        }
        return map;
    }
}