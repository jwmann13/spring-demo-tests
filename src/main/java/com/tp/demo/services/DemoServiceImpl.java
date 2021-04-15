package com.tp.demo.services;

import java.util.List;

import com.tp.demo.models.Demo;
import com.tp.demo.repository.DemoRepository;

import org.springframework.stereotype.Service;

@Service
public class DemoServiceImpl implements DemoService {
    private final DemoRepository demoRepo;

    public DemoServiceImpl(DemoRepository demoRepo) {
        this.demoRepo = demoRepo;
    }

    @Override
    public Demo getDemo(Integer demoId) {
        return demoRepo.findById(demoId).get();
    }

    @Override
    public Demo addDemo(Demo toSave) {
        return demoRepo.save(toSave);
    }

    @Override
    public List<Demo> getAllDemos() {
        return demoRepo.findAll();
    }
    
}
