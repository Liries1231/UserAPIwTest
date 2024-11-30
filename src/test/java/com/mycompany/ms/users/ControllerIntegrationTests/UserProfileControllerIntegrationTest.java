package com.mycompany.ms.users.ControllerIntegrationTests;

import com.mycompany.ms.users.AbstractIntegrationTest;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
public class UserProfileControllerIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void createUpdateAndDeleteUserTest() throws Exception {
        String createUserProfileJson = """
            {
                "birthYear": "2000-01-01T00:00:00Z",
                "about": "This is a test profile"
            }
        """;

        MvcResult createResult = mockMvc.perform(post("/api/v1/user_profile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createUserProfileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthYear").value("2000-01-01T00:00:00Z"))
                .andExpect(jsonPath("$.about").value("This is a test profile"))
                .andReturn();

        String responseBody = createResult.getResponse().getContentAsString();
        String profileId = JsonPath.read(responseBody, "$.id").toString();

        String updateUserProfileJson = """
            {
                "about": "Updated test profile"
            }
        """;

        mockMvc.perform(put("/api/v1/user_profile/" + profileId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateUserProfileJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.birthYear").value("2000-01-01T00:00:00Z"))
                .andExpect(jsonPath("$.about").value("Updated test profile"));

        mockMvc.perform(delete("/api/v1/user_profile/" + profileId))
                .andExpect(status().isNoContent());
    }
}