package com.praveen.docker;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

//integration test
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {
    @Autowired
    MockMvc mockMvc;


    @Test
    public void testGet() throws Exception {
        ResponseDTO responseDTO = ResponseDTO.builder().message(HttpStatus.OK.name()).data("Test Get").status(HttpStatus.OK.value()).build();
        ObjectMapper mapper = new ObjectMapper();
        mockMvc.perform(get("/get")).andExpect(content().json(mapper.writeValueAsString(responseDTO)));
    }

}
