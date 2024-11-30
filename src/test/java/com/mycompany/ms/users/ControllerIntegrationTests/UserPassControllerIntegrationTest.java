package com.mycompany.ms.users.ControllerIntegrationTests;

import com.mycompany.ms.users.AbstractIntegrationTest;
import com.mycompany.ms.users.entity.UserPass;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createUserTest() throws Exception {
        UserPass user = new UserPass();
        user.setLogin("KIRILL");
        user.setPassword("1234412312");

        String userCreationJson = objectMapper.writeValueAsString(user);

        mockMvc.perform(post("/api/v1/user_pass")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userCreationJson))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.login").value("KIRILL"));
    }


    @Test
    void deleteUserTest() throws Exception {
        mockMvc.perform(delete("/api/v1/user_pass/1"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(""));
    }
}
