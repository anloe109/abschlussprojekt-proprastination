package mops.services;

import mops.repositories.ModuleRepository;
import mops.services.dbServices.ModuleService;
import org.apache.tomcat.jni.Local;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import mops.model.classes.Module;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ModuleService.class})
class ModuleServiceTest {


    @MockBean
    ModuleRepository repo;

    @Autowired
    private ModuleService service;

    Module m1;
    List<Module> modules;

    @BeforeEach
    void init() {
        LocalDateTime deadline = LocalDateTime.now();
        LocalDateTime deadline2 = LocalDateTime.now();
        m1 = Module.builder()
                .id(01)
                .name("Programmier Praktikum")
                .shortName("ProPra")
                .profSerial("Jens")
                .applicantDeadlineDate("2020-01-01")
                .applicantDeadlineTime("09:41")
                .applicantDeadline(deadline)
                .orgaDeadlineDate("2020-01-01")
                .orgaDeadlineTime("09:42")
                .orgaDeadline(deadline2)
                .sevenHourLimit("2")
                .nineHourLimit("3")
                .seventeenHourLimit("2")
                .build();


        modules = new ArrayList<>();
        modules.add(m1);

        Mockito.when(repo.findAll()).thenReturn(modules);
        Mockito.when(repo.findDistinctByName(m1.getName())).thenReturn(m1);
        Mockito.when(repo.findById(m1.getId())).thenReturn(java.util.Optional.ofNullable(m1));
    }

    @Test
    void getModulesTest() {
        List<Module> readmodules = service.getModules();

        assertThat(readmodules).isEqualTo(modules);
    }

    @Test
    void findModuleByNameTest() {
        Module module = service.findModuleByName(m1.getName());

        assertThat(module).isEqualTo(module);
    }

    @Test
    void findById() {
        Module module = service.findById(m1.getId());

        assertThat(module).isEqualTo(m1);
    }

    @Test
    void save() {
        service.save(m1);

        verify(repo, times(1)).save(m1);

    }

    @Test
    void deleteModule() {
        service.deleteModule(m1.getName());

        verify(repo, times(1)).deleteById(m1.getId());
    }

    @Test
    void deleteAll() {
        service.deleteAll();

        verify(repo, times(1)).deleteAll();
    }
}