package UserBase.ControllerIntegrationTests;

import UserBase.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class UserPassControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUserTest() throws Exception {
        String userCreationJson = """
                    {
                        "login": "KIRILL",
                        "password": "1234412312"
                    }
                """;

        mockMvc.perform(post("/api/v1/user_pass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value("KIRILL"))
                .andExpect(jsonPath("$.password").value("1234412312"));
    }



    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/v1/user_pass/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
}
