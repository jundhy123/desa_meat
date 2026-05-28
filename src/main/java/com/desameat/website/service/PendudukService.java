package com.desameat.website.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desameat.website.model.Penduduk;
import com.desameat.website.repository.PendudukRepository;

@Service
public class PendudukService {

    @Autowired
    private PendudukRepository pendudukRepository;

    public List<Penduduk> getAllPenduduk() {
        return pendudukRepository.findAll();
    }

    public Penduduk getById(Long id) {
        return pendudukRepository.findById(id).orElse(null);
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

    public Map<String, Object> getStatistik() {
        Map<String, Object> stat = new LinkedHashMap<>();
        stat.put("total", pendudukRepository.count());
        stat.put("lakiLaki", pendudukRepository.countByJenisKelamin("Laki-laki"));
        stat.put("perempuan", pendudukRepository.countByJenisKelamin("Perempuan"));
        return stat;
    }
}