package com.casestudy.stockexchange.controller;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import com.casestudy.stockexchange.persistence.entity.StockExchangeEntity;
import com.casestudy.stockexchange.service.StockExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock-exchange")
public class StockExchangeController {

    @Autowired
    private StockExchangeService stockExchangeService;

    @GetMapping("/{name}")
    public ResponseEntity<StockExchangeEntity> getStockExchangeByName(@PathVariable String name) {
        StockExchangeEntity stockExchange = stockExchangeService.getStockExchangeByName(name);
        return ResponseEntity.ok(stockExchange);
    }

    @PostMapping("/{name}")
    public ResponseEntity<StockExchangeEntity> addStockToExchange(@PathVariable String name, @RequestBody StockEntity stock) {
        StockExchangeEntity stockExchange = stockExchangeService.addStockToExchange(name, stock);
        return ResponseEntity.ok(stockExchange);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<StockExchangeEntity> deleteStockFromExchange(@PathVariable String name, @RequestBody StockEntity stock) {
        StockExchangeEntity stockExchange = stockExchangeService.deleteStockFromExchange(name, stock);
        return ResponseEntity.ok(stockExchange);
    }
}