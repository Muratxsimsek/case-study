package com.casestudy.stockexchange.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private BigDecimal currentPrice;

    private Timestamp lastUpdate;

    @ManyToMany(mappedBy = "stocks")
    private List<StockExchangeEntity> stockExchanges = new ArrayList<>();

}