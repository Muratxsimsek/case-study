package com.casestudy.stockexchange.service;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import com.casestudy.stockexchange.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Transactional
    public StockEntity createStock(StockEntity stock) {
        return stockRepository.save(stock);
    }

    @Transactional
    public StockEntity updateStockPrice(StockEntity stock) {
        StockEntity existingStock = stockRepository.findById(stock.getId()).orElseThrow();
        existingStock.setCurrentPrice(stock.getCurrentPrice());
        existingStock.setLastUpdate(stock.getLastUpdate());
        return stockRepository.save(existingStock);
    }

    @Transactional
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}