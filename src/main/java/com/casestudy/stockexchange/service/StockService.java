package com.casestudy.stockexchange.service;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import com.casestudy.stockexchange.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    public StockEntity createStock(StockEntity stock) {
        return stockRepository.save(stock);
    }

    public StockEntity updateStockPrice(StockEntity stock) {
        StockEntity existingStock = stockRepository.findById(stock.getId()).orElseThrow();
        existingStock.setCurrentPrice(stock.getCurrentPrice());
        existingStock.setLastUpdate(stock.getLastUpdate());
        return stockRepository.save(existingStock);
    }

    public void deleteStock(StockEntity stock) {
        stockRepository.deleteById(stock.getId());
    }
}