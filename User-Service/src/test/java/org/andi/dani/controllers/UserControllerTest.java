package org.andi.dani.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andi.dani.dtos.UserDetailDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Random;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "localtest")
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allScenarioTest() throws Exception {
        // test create user
        Random rand = new Random();
        var randLastDigit = rand.nextInt(100 - 10 + 1) + 10;

        var createResult = mockMvc.perform(MockMvcRequestBuilders.post("/users")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("name=test"+randLastDigit))
                .andExpect(status().isCreated())
                .andReturn();

        String json = createResult.getResponse().getContentAsString();
        UserDetailDto userDetailDto = new ObjectMapper().readValue(json, UserDetailDto.class);

        // test get user detail by id
        mockMvc.perform(get("/users/"+userDetailDto.getUserDto().getId()))
                .andExpect(status().isOk());

        // test get all user
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk());
    }
}
