package com.example.UserBase;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class UserProfileControllerIntegrationTest extends AbstractIntegrationTest {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/user_profile";
    @Test
    void createUser() throws Exception {
        String userProfileJson = """
                {
                    "birthYear": "2000-01-01T00:00:00Z",
                     "about": "This is a test profile"
                }
            """;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userProfileJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"about\":\"This is a test profile\""));
        assertTrue(response.body().contains("\"birthYear\":\"2000-01-01T00:00:00Z\""));
    }
    @Test
    void deleteUserTest() throws Exception {
        // Удаление пользователя с ID 1
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/1")) // ID пользователя
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // Проверка, что статус ответа 204 (No Content)
        assertEquals(204, response.statusCode());

        // Проверка, что тело ответа содержит сообщение об успешном удалении
        assertTrue(response.body().isEmpty()); // Тело ответа должно быть пустым для кода 204
    }

}

