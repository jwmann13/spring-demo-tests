package com.tp.demo.repository;

import com.tp.demo.models.Demo;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DemoRepository extends JpaRepository<Demo, Integer> {
    
}
