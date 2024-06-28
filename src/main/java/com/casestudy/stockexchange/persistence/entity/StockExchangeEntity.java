package com.casestudy.stockexchange.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class StockExchangeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private boolean liveInMarket;

    @ManyToMany
    @JoinTable(
            name = "stock_exchange_stock",
            joinColumns = @JoinColumn(name = "stock_exchange_id"),
            inverseJoinColumns = @JoinColumn(name = "stock_id")
    )
    private List<StockEntity> stocks = new ArrayList<>();

    public void addStock(StockEntity stock) {
        this.stocks.add(stock);
        updateLiveInMarketStatus();
    }

    public void removeStock(StockEntity stock) {
        this.stocks.remove(stock);
        updateLiveInMarketStatus();
    }

    private void updateLiveInMarketStatus() {
        this.liveInMarket = this.stocks.size() >= 5;
    }

}
