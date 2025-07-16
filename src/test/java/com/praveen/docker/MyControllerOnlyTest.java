package com.praveen.docker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//controller only test should not have service injected in main controller
@WebMvcTest(MyControllerOnly.class)
public class MyControllerOnlyTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void testPost() throws Exception {
        mockMvc.perform(put("/controllerOnly/put")).andExpect(status().isOk())
                .andExpect(content().string("Test Put"));
    }

}
