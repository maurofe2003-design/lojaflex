package com.parfum.controller;

import com.parfum.model.Perfume;
import com.parfum.service.PerfumeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/perfumes")
@CrossOrigin
public class PerfumeController {

    private final PerfumeService perfumeService;

    public PerfumeController(PerfumeService perfumeService) {
        this.perfumeService = perfumeService;
    }

    @GetMapping
    public ResponseEntity<List<Perfume>> getAll() {
        return ResponseEntity.ok(perfumeService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Perfume> getById(@PathVariable Long id) {
        return ResponseEntity.ok(perfumeService.getById(id));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Perfume>> search(@RequestParam String q) {
        return ResponseEntity.ok(perfumeService.search(q));
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Perfume>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(perfumeService.getByCategory(category));
    }

    @GetMapping("/preco")
    public ResponseEntity<List<Perfume>> getByPriceRange(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        return ResponseEntity.ok(perfumeService.getByPriceRange(min, max));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Perfume> create(@RequestBody Perfume perfume) {
        return ResponseEntity.ok(perfumeService.save(perfume));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Perfume> update(@PathVariable Long id, @RequestBody Perfume perfume) {
        return ResponseEntity.ok(perfumeService.update(id, perfume));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        perfumeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}