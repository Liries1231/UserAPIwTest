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
public class RegisterControllerIntegrationTest extends AbstractIntegrationTest {

    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final String BASE_URL = "http://localhost:8080/api/register";

    @Test
    void createUserWithProfileTest() throws Exception {
        // JSON для создания пользователя
        String userCreationJson = """
                    {
                        "login": "testUser",
                        "password": "testPassword",
//                        "birthYear": "2000-01-01 00:00:00",
                        "about": "This is a test profile"
                    }
                """;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(userCreationJson))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(userCreationJson);

        assertEquals(200, response.statusCode());
        assertTrue(response.body().contains("\"login\":\"testUser\""));
        assertTrue(response.body().contains("\"about\":\"This is a test profile\""));
    }
}