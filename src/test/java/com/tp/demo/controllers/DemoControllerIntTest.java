package com.tp.demo.controllers;

import static org.assertj.core.api.Assertions.*;

import java.net.URL;
import java.util.List;

import com.tp.demo.models.Demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DemoControllerIntTest {
    
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port);
    }

    @Test
    public void getAllDemos() throws Exception {
        ResponseEntity<List> res = template.getForEntity(base.toString() + "/allDemo", List.class);
        assertThat(res.getBody())
        .isInstanceOf(List.class)
        .hasOnlyElementsOfType(Demo.class);
    }

    @Test
    public void getDemo() throws Exception {
        ResponseEntity<Demo> res = template.getForEntity(base.toString() + "/oneDemo", Demo.class);
        assertThat(res.getBody())
        .isInstanceOf(Demo.class);
    }

}
