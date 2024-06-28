package com.casestudy.stockexchange.persistence.repository;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockEntity, Long> {
}
