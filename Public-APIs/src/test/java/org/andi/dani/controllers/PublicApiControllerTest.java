package org.andi.dani.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.andi.dani.dtos.CreatedUserDto;
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
public class PublicApiControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void allScenarioTest() throws Exception {
        // test create user
        Random rand = new Random();
        var randLastDigit = rand.nextInt(100 - 10 + 1) + 10;

        var result = mockMvc.perform(MockMvcRequestBuilders.post("/public-api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"name\": \"testUnitUser" + randLastDigit + "\"}"))
                .andExpect(status().isCreated())
                .andReturn();

        // test create listing
        String json = result.getResponse().getContentAsString();
        CreatedUserDto createdUserDto = new ObjectMapper().readValue(json, CreatedUserDto.class);

        mockMvc.perform(MockMvcRequestBuilders.post("/public-api/listings")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"user_id\": " + createdUserDto.getUser().getId() + ", \"listing_type\": \"SALE\", \"price\": 90000 }"))
                .andExpect(status().isCreated());

        // test get listings
        mockMvc.perform(get("/public-api/listings?page_num=1&page_size=20"))
                .andExpect(status().isOk());
    }

}
