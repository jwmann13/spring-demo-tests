package com.tp.demo.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.tp.demo.models.Demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class DemoRepositoryIntTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private DemoRepository demoRepository;

    private Demo testDemo;

    @BeforeEach
    public void setup() {
        testDemo = new Demo();
        testDemo.setDemo("Test");
        entityManager.persist(testDemo);
    }

    @Test
    public void save() {
        Demo addDemo = new Demo();
        addDemo.setDemo("Test 2");

        Demo saved = demoRepository.save(addDemo);

        assertThat(saved)
        .isNotNull()
        .isInstanceOf(Demo.class)
        .hasFieldOrPropertyWithValue("demoId", 2)
        .hasFieldOrPropertyWithValue("demo", "Test 2");
    }

    @Test
    public void saveThrows() {
        assertThrows(InvalidDataAccessApiUsageException.class, ()-> demoRepository.save(null));
    }

    @Test
    public void findAll() {
        List<Demo> allDemos = demoRepository.findAll();

        assertThat(allDemos)
        .isInstanceOf(List.class)
        .isNotEmpty()
        .contains(testDemo)
        .allSatisfy(demo -> {
            assertThat(demo.getDemo()).isEqualTo("Test");
            assertThat(demo.getDemoId()).isEqualTo(1);
        });
    }

    @Test
    public void delete() {
        demoRepository.delete(testDemo);

        assertTrue(demoRepository.findAll().isEmpty());
    }


}
