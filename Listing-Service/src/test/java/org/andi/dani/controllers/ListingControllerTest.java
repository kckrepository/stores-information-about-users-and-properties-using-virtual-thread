package org.andi.dani.controllers;

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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles(profiles = "localtest")
public class ListingControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void allScenarioTest() throws Exception {
        // test create
        Random rand = new Random();
        var randLastDigit = rand.nextInt(100 - 10 + 1) + 10;

        mockMvc.perform(MockMvcRequestBuilders.post("/listings")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content("user_id=" + randLastDigit + "&listing_type=RENT&price=500000"))
                .andExpect(status().isCreated());

        // test get list
        mockMvc.perform(get("/listings?"))
                .andExpect(status().isOk());
    }
}
