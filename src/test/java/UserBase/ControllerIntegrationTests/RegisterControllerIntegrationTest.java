package UserBase.ControllerIntegrationTests;

import UserBase.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class RegisterControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUser() throws Exception {
        String userCreationJson = """
                    {
                        "login": "Slavik",
                        "password": "password123!DDd",
                        "birthYear": "2000-01-01 00:00:00",
                        "about": "This is a test profile"
                    }
                """;

        mockMvc.perform(post("/api/v1/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value("Slavik"))
                .andExpect(jsonPath("$.password").value("password123!DDd"));
    }
}