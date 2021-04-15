package com.tp.demo.controllers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import java.util.List;

import com.tp.demo.models.Demo;
import com.tp.demo.services.DemoServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(DemoController.class)
public class DemoControllerUnitTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private DemoServiceImpl demoService;

    @Test
    public void getAllDemos() throws Exception {
        when(demoService.getAllDemos()).thenReturn(List.of(new Demo(1, "Test")));
        mvc.perform(get("/allDemo")).andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
