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
    void updateUserTest() throws Exception {
        // Создание нового профиля (для того чтобы обновить его, нужно сначала создать)
        String createUserProfileJson = """
            {
                "birthYear": "2000-01-01T00:00:00Z",
                "about": "This is a test profile"
            }
        """;

        // Создаем профиль
        HttpRequest createRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(createUserProfileJson))
                .build();

        HttpResponse<String> createResponse = httpClient.send(createRequest, HttpResponse.BodyHandlers.ofString());
        assertEquals(200, createResponse.statusCode()); // Проверяем, что профиль создан

        // Извлекаем ID профиля из ответа (допустим, ответ содержит ID)
        String responseBody = createResponse.body();
        String profileId = responseBody.substring(responseBody.indexOf("\"id\":") + 5, responseBody.indexOf(","));

        // Новый JSON с обновленным полем
        String updateUserProfileJson = """
            {
                "birthYear": "1995-05-15T00:00:00Z",
                "about": "Updated test profile"
            }
        """;

        // Обновление профиля
        HttpRequest updateRequest = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/" + profileId)) // Используем ID, полученный после создания
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(updateUserProfileJson))
                .build();

        HttpResponse<String> updateResponse = httpClient.send(updateRequest, HttpResponse.BodyHandlers.ofString());

        // Проверка, что статус ответа 200 (OK)
        assertEquals(200, updateResponse.statusCode());

        // Проверка, что обновленные данные содержатся в ответе
        assertTrue(updateResponse.body().contains("\"about\":\"Updated test profile\""));
        assertTrue(updateResponse.body().contains("\"birthYear\":\"1995-05-15T00:00:00Z\""));
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

