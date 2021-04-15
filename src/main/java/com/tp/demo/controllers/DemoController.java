package com.tp.demo.controllers;

import java.util.List;

import com.tp.demo.models.Demo;
import com.tp.demo.services.DemoServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    
    private final DemoServiceImpl demoService;

    public DemoController(DemoServiceImpl demoService) {
        this.demoService = demoService;
    }

    @GetMapping("/allDemo")
    public ResponseEntity<Object> getAllDemo() {
        List<Demo> aDemo;
        try {
            aDemo = demoService.getAllDemos();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        return ResponseEntity.ok(aDemo);
    }

    @GetMapping("/oneDemo")
    public ResponseEntity<Object> getDemoById(@RequestParam Integer demoId) {
        Demo aDemo;
        try {
            aDemo = demoService.getDemo(demoId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(e.getMessage());
        }
        return ResponseEntity.ok(aDemo);
    }

    @PostMapping("/demo")
    public ResponseEntity<Object> addDemo(@RequestBody Demo toSave) {
        Demo aDemo;
        try {
            aDemo = demoService.addDemo(toSave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
        return ResponseEntity.ok(aDemo);
    }
    
}
