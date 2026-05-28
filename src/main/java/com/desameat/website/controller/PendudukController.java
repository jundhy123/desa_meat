package com.desameat.website.controller;

import com.desameat.website.model.Penduduk;
import com.desameat.website.service.PendudukService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/penduduk")
public class PendudukController {

    @Autowired
    private PendudukService pendudukService;

    @GetMapping("/admin/list")
    @ResponseBody
    public List<Penduduk> getCitizens() {
        return pendudukService.getAllPenduduk();
    }

    @PostMapping("/admin/add")
    @ResponseBody
    public Penduduk addCitizen(@RequestBody Penduduk p) {
        return pendudukService.savePenduduk(p);
    }

    @DeleteMapping("/admin/delete/{id}")
    @ResponseBody
    public String removeCitizen(@PathVariable("id") Long id) {
        try {
            pendudukService.deletePenduduk(id);
            return "SUCCESS";
        } catch (Exception e) {
            return "ERROR: " + e.getMessage();
        }
    }
}
