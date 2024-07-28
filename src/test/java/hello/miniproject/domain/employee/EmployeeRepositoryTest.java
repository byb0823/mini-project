package hello.miniproject.domain.employee;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static hello.miniproject.domain.employee.Role.*;
import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@DataJpaTest
class EmployeeRepositoryTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAllInBatch();
    }

    @DisplayName("등록된 직원이 없으면 null을 반환한다.")
    @Test
    void findLatestTeamNumberWithoutEmployee() {
        //given //when
        String latestEmployeeNumber = employeeRepository.findLatestEmployeeNumber();

        //then
        assertThat(latestEmployeeNumber).isNull();
    }

    @DisplayName("가장 마지막에 등록된 직원의 직원 번호를 반환한다.")
    @Test
    void findLatestTeamNumber() {
        //given
        LocalDate joinDate = LocalDate.of(2020, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        Employee employee = Employee.builder()
                .employeeNumber("001")
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .team(null)
                .build();
        employeeRepository.save(employee);

        //when
        String latestEmployeeNumber = employeeRepository.findLatestEmployeeNumber();

        //then
        assertThat(latestEmployeeNumber).isEqualTo("001");
    }

}