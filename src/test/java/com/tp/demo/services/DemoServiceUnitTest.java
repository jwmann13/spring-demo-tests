package com.tp.demo.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import com.tp.demo.models.Demo;
import com.tp.demo.repository.DemoRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DemoServiceUnitTest {
    @Mock
    private DemoRepository demoRepo;

    @InjectMocks
    private DemoServiceImpl demoService;

    @Test
    public void addDemo() {
        Demo tDemo = new Demo(1, "Test");
        when(demoRepo.save(any(Demo.class))).thenReturn(tDemo); // Arrange
        Demo savedDemo = demoService.addDemo(tDemo); // Act

        assertThat(savedDemo)
        .isNotNull()
        .isInstanceOf(Demo.class)
        .hasFieldOrPropertyWithValue("demoId", 1)
        .hasFieldOrPropertyWithValue("demo", "Test");
    }

    @Test
    public void getAllDemo() {
        when(demoRepo.findAll()).thenReturn(List.of(new Demo(1, "Test")));
        List<Demo> demos = demoService.getAllDemos();

        assertThat(demos)
        .isNotNull()
        .isNotEmpty()
        .allSatisfy(demo -> {
            assertThat(demo.getDemoId()).isEqualTo(1);
            assertThat(demo.getDemo()).isEqualTo("Test");
        });
    }

    @Test
    public void getDemo() {
        int id = 1;
        when(demoRepo.findById(id)).thenReturn(Optional.of(new Demo(id, "Test")));
        Demo demo = demoService.getDemo(id);

        assertThat(demo)
        .isNotNull()
        .isInstanceOf(Demo.class)
        .hasFieldOrPropertyWithValue("demoId", 1)
        .hasFieldOrPropertyWithValue("demo", "Test");
    }
}