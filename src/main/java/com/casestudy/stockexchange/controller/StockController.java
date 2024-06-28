package com.casestudy.stockexchange.controller;

import com.casestudy.stockexchange.persistence.entity.StockEntity;
import com.casestudy.stockexchange.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @PostMapping
    public ResponseEntity<StockEntity> createStock(@RequestBody StockEntity stock) {
        StockEntity createdStock = stockService.createStock(stock);
        return ResponseEntity.ok(createdStock);
    }

    @PutMapping
    public ResponseEntity<StockEntity> updateStockPrice(@RequestBody StockEntity stock) {
        StockEntity updatedStock = stockService.updateStockPrice(stock);
        return ResponseEntity.ok(updatedStock);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable("id") Long id) {
        stockService.deleteStock(id);
        return ResponseEntity.noContent().build();
    }
}