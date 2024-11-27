package com.example.UserBase.ControllerIntegrationTests;

import com.example.UserBase.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserPassControllerIntegrationTest extends AbstractIntegrationTest {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/user_pass";

    @Test
    void createUserTest() throws Exception {
        // JSON для создания пользователя
        String userCreationJson = """
                    {
                        "login": "KIRILL",
                        "password": "1234412312"
                    }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userCreationJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверка, что статус ответа 201 (Created)
        assertEquals(201, response.statusCode());

        // Проверка, что в ответе присутствуют правильные данные
        assertTrue(response.body().contains("\"login\":\"KIRILL\""));
        assertTrue(response.body().contains("\"password\":\"1234412312\""));
    }



    @Test
    void deleteUserTest() throws Exception {
        // Удаление пользователя с ID 1
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/1")) // ID пользователя
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверка, что статус ответа 200 (OK)
        assertEquals(200, response.statusCode());

        // Проверка, что тело ответа содержит сообщение об успешном удалении
        assertTrue(response.body().contains("deleted"));
    }
}
