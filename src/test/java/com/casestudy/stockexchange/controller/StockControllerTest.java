package com.casestudy.stockexchange.controller;

import com.casestudy.stockexchange.persistence.repository.StockRepository;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@Transactional
public class StockControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private StockRepository stockRepository;

    @BeforeEach
    public void setup() {
        RestAssured.port = port;
        stockRepository.deleteAll();
    }


    @Test
    public void testCreateStock() {
        String stockJson = "{\"name\":\"HealthInc\",\"description\":\"A healthcare company\",\"currentPrice\":120.00,\"lastUpdate\":\"2024-06-27T10:00:00\"}";

        given()
                .contentType(ContentType.JSON)
                .body(stockJson)
                .when()
                .post("/api/v1/stock")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("name", equalTo("HealthInc"));
    }

    @Test
    public void testUpdateStockPrice() {

        String initialStockJson = "{\"name\":\"MSFT\",\"description\":\"Microsoft Corporation\",\"currentPrice\":125.00,\"lastUpdate\":\"2024-06-27T09:00:00\"}";

        Response initialResponse = given()
                .contentType(ContentType.JSON)
                .body(initialStockJson)
                .when()
                .post("/api/v1/stock");

        String id = initialResponse.jsonPath().getString("id");

        String updatedStockJson = "{\"id\":" +id+",\"name\":\"MSFT\",\"description\":\"Microsoft Corporation\",\"currentPrice\":130.00,\"lastUpdate\":\"2024-06-27T10:05:00\"}";

        given()
                .contentType(ContentType.JSON)
                .body(updatedStockJson)
                .when()
                .put("/api/v1/stock")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("currentPrice", equalTo(130.00f));
    }

    @Test
    public void testDeleteStock() {

        String initialStockJson = "{\"name\":\"MSFT\",\"description\":\"Microsoft Corporation\",\"currentPrice\":125.00,\"lastUpdate\":\"2024-06-27T09:00:00\"}";

        Response initialResponse = given()
                .contentType(ContentType.JSON)
                .body(initialStockJson)
                .when()
                .post("/api/v1/stock");

        String id = initialResponse.jsonPath().getString("id");


        given()
                .pathParam("id", id)
                .when()
                .delete("/api/v1/stock/{id}")
                .then()
                .statusCode(204);
    }
}
