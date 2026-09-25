package com.example.demojava17spring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class DemoJava17SpringApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void helloReturnsGreeting() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(new HelloController()).build();

        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello from Java 17 Spring Boot!"));
    }

}
