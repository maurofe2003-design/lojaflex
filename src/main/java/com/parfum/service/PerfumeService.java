package com.parfum.service;

import com.parfum.model.Perfume;
import com.parfum.repository.PerfumeRepository;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class PerfumeService {

    private final PerfumeRepository perfumeRepository;

    public PerfumeService(PerfumeRepository perfumeRepository) {
        this.perfumeRepository = perfumeRepository;
    }

    public List<Perfume> getAll() {
        return perfumeRepository.findAll();
    }

    public Perfume getById(Long id) {
        return perfumeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Perfume não encontrado."));
    }

    public List<Perfume> search(String query) {
        return perfumeRepository.search(query);
    }

    public List<Perfume> getByCategory(String category) {
        return perfumeRepository.findByCategory(category);
    }

    public List<Perfume> getByPriceRange(BigDecimal min, BigDecimal max) {
        return perfumeRepository.findByPriceBetween(min, max);
    }

    public Perfume save(Perfume perfume) {
        return perfumeRepository.save(perfume);
    }

    public Perfume update(Long id, Perfume data) {
        Perfume p = getById(id);
        p.setName(data.getName());
        p.setBrand(data.getBrand());
        p.setDescription(data.getDescription());
        p.setPrice(data.getPrice());
        p.setCategory(data.getCategory());
        p.setVolume(data.getVolume());
        p.setImageUrl(data.getImageUrl());
        p.setStock(data.getStock());
        return perfumeRepository.save(p);
    }

    public void delete(Long id) {
        perfumeRepository.deleteById(id);
    }
}