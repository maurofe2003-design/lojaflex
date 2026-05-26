package com.parfum.repository;

import com.parfum.model.Perfume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PerfumeRepository extends JpaRepository<Perfume, Long> {

    List<Perfume> findByCategory(String category);

    List<Perfume> findByBrand(String brand);

    @Query("SELECT p FROM Perfume p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :query, '%')) " +
            "OR LOWER(p.brand) LIKE LOWER(CONCAT('%', :query, '%'))")
    List<Perfume> search(String query);

    List<Perfume> findByPriceBetween(java.math.BigDecimal min, java.math.BigDecimal max);
}