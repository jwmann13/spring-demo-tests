package com.tp.demo.services;

import java.util.List;

import com.tp.demo.models.Demo;

public interface DemoService {
    public Demo getDemo(Integer demoId);

    public Demo addDemo(Demo toSave);

    public List<Demo> getAllDemos();
}
