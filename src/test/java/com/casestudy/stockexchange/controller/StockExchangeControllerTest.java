package com.casestudy.stockexchange.controller;

import com.casestudy.stockexchange.persistence.entity.StockExchangeEntity;
import com.casestudy.stockexchange.persistence.repository.StockExchangeRepository;
import com.casestudy.stockexchange.service.StockExchangeService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class StockExchangeControllerTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
    }

    @Autowired
    private StockExchangeRepository stockExchangeRepository;
    @Autowired
    private StockExchangeService stockExchangeService;

    @Test
    public void testGetStockExchangeByName() {

        StockExchangeEntity stockExchange = new StockExchangeEntity();
        stockExchange.setName("Global Exchange");
        stockExchangeRepository.save(stockExchange);

        given()
                .pathParam("name", "Global Exchange")
                .when()
                .get("/api/v1/stock-exchange/{name}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("Global Exchange"));
    }

    @Test
    public void testAddStockToExchange() {
        StockExchangeEntity stockExchange = new StockExchangeEntity();
        stockExchange.setName("Global Exchange");
        stockExchangeRepository.save(stockExchange);

        String stockJson = "{\"name\":\"TechCorp\",\"description\":\"A technology company\",\"currentPrice\":150.00,\"lastUpdate\":\"2024-06-27T10:00:00\"}";

        given()
                .pathParam("name", "Global Exchange")
                .contentType(ContentType.JSON)
                .body(stockJson)
                .when()
                .post("/api/v1/stock-exchange/{name}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("stocks", hasSize(greaterThanOrEqualTo(1)));
    }

    @Test
    public void testDeleteStockFromExchange() {
        StockExchangeEntity stockExchange = new StockExchangeEntity();
        stockExchange.setName("Global Exchange");
        stockExchangeRepository.save(stockExchange);

        String stockJson = "{\"id\":1,\"name\":\"TechCorp\",\"description\":\"A technology company\",\"currentPrice\":150.00,\"lastUpdate\":\"2024-06-27T10:00:00\"}";

        given()
                .pathParam("name", "Global Exchange")
                .contentType(ContentType.JSON)
                .body(stockJson)
                .when()
                .delete("/api/v1/stock-exchange/{name}")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("stocks", hasSize(lessThanOrEqualTo(4)));
    }
}
