package com.casestudy.stockexchange.service;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import com.casestudy.stockexchange.persistence.entity.StockExchangeEntity;
import com.casestudy.stockexchange.persistence.repository.StockExchangeRepository;
import com.casestudy.stockexchange.persistence.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class StockExchangeService {

    @Autowired
    private StockExchangeRepository stockExchangeRepository;

    @Autowired
    private StockRepository stockRepository;

    public StockExchangeEntity getStockExchangeByName(String name) {
        return stockExchangeRepository.findByName(name).orElseThrow();
    }

    @Transactional
    public StockExchangeEntity addStockToExchange(String name, StockEntity stock) {
        StockExchangeEntity stockExchange = stockExchangeRepository.findByName(name).orElseThrow();
        stockExchange.addStock(stock);
        stockRepository.save(stock);
        return stockExchangeRepository.save(stockExchange);
    }

    @Transactional
    public StockExchangeEntity deleteStockFromExchange(String name, StockEntity stock) {
        StockExchangeEntity stockExchange = stockExchangeRepository.findByName(name).orElseThrow();
        stockExchange.removeStock(stock);
        return stockExchangeRepository.save(stockExchange);
    }
}
