package com.casestudy.stockexchange.persistence.repository;

import com.casestudy.stockexchange.persistence.entity.StockExchangeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockExchangeRepository extends JpaRepository<StockExchangeEntity, Long> {
    Optional<StockExchangeEntity> findByName(String name);
}
