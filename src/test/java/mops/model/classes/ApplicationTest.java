package mops.model.classes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ApplicationTest {
    Application application;
    Address address;
    Applicant applicant;
    Certificate certificate;

    @BeforeEach
    void setup() {
        Module module = Module.builder()
                .applicantDeadline(LocalDateTime.ofEpochSecond(100, 0, ZoneOffset.UTC))
                .name("Info4")
                .build();
        address = Address.builder()
                .zipcode("12345")
                .country("USA")
                .city("Düsseldorf")
                .street("Street")
                .houseNumber("999")
                .build();

        certificate = Certificate.builder()
                .name("Bachelor")
                .course("Informatik")
                .build();

        applicant = Applicant.builder()
                .uniserial("lolol420")
                .address(address)
                .gender("male")
                .firstName("Angelo")
                .surname("Merkel")
                .comment("Moin")
                .nationality("Russian")
                .birthplace("Deutschland")
                .birthday("32.32.9999")
                .course("Trivial")
                .status("irelevant")
                .application(application)
                .certs(certificate)
                .build();

        application = Application.builder()
                .minHours(2)
                .maxHours(4)
                .grade(1.3)
                .priority(Priority.VERYHIGH)
                .lecturer("Tester")
                .role(Role.PROOFREADER)
                .semester("WS2020")
                .module(module)
                .comment("")
                .build();
    }

    @Test
    void TestBuilder() {
        //Arrange in BeforeEach
        Module module = Module.builder()
                .applicantDeadline(LocalDateTime.ofEpochSecond(100, 0, ZoneOffset.UTC))
                .name("Info4")
                .build();

        assertThat(application)
                .hasFieldOrPropertyWithValue("minHours", 2)
                .hasFieldOrPropertyWithValue("maxHours", 4)
                .hasFieldOrPropertyWithValue("priority", Priority.VERYHIGH)
                .hasFieldOrPropertyWithValue("grade", 1.3)
                .hasFieldOrPropertyWithValue("lecturer", "Tester")
                .hasFieldOrPropertyWithValue("role", Role.PROOFREADER)
                .hasFieldOrPropertyWithValue("semester", "WS2020")
                .hasFieldOrPropertyWithValue("module", module)
                .hasFieldOrPropertyWithValue("comment", "");


    }

    @Test
    void testEquals() {
        Module module = Module.builder()
                .applicantDeadline(LocalDateTime.ofEpochSecond(100, 0, ZoneOffset.UTC))
                .name("Info4")
                .build();
        Application application1 = Application.builder()
                .minHours(5)
                .maxHours(10)
                .grade(1.3)
                .priority(Priority.VERYHIGH)
                .lecturer("Tester")
                .role(Role.PROOFREADER)
                .semester("WS2020")
                .module(module)
                .build();

        Application application2 = Application.builder()
                .minHours(5)
                .maxHours(10)
                .grade(1.3)
                .priority(Priority.VERYHIGH)
                .lecturer("Tester")
                .role(Role.PROOFREADER)
                .semester("WS2020")
                .module(module)
                .build();

        assertThat(application1).isEqualTo(application2);
    }

    @Test
    void testToString() {
        //Arrange in BeforeEach

        assertThat(application.toString()).isEqualTo(
                "Application(minHours=2, finalHours=0, maxHours=4, module=Module(name=Info4, applicantDeadlineDate=null," +
                        " applicantDeadlineTime=null, applicantDeadline=1970-01-01T00:01:40," +
                        " orgaDeadlineDate=null, orgaDeadlineTime=null, orgaDeadline=null," + " shortName=null, profSerial=null," +
                        " sevenHourLimit=null, nineHourLimit=null, seventeenHourLimit=null), priority=VERYHIGH," +
                        " grade=1.3, lecturer=Tester, semester=WS2020, role=PROOFREADER, comment=)");

    }

    @Test
    void testNoArgsConstructor() {
        Application emptyApplication = new Application();

        assertNull(emptyApplication.getComment());
        assertEquals(0, emptyApplication.getFinalHours());
        assertEquals(0, emptyApplication.getGrade());
        assertNull(emptyApplication.getLecturer());
        assertEquals(0, emptyApplication.getMaxHours());
        assertEquals(0, emptyApplication.getMinHours());
        assertNull(emptyApplication.getModule());
        assertNull(emptyApplication.getRole());
        assertNull(emptyApplication.getPriority());
        assertNull(emptyApplication.getSemester());

    }

}
