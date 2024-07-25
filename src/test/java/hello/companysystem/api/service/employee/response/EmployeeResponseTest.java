package hello.companysystem.api.service.employee.response;

import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.Role;
import hello.companysystem.domain.team.Team;
import hello.companysystem.domain.team.TeamConst;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static hello.companysystem.domain.employee.Role.*;
import static hello.companysystem.domain.team.TeamConst.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class EmployeeResponseTest {

    @DisplayName("직원을 등록하면 등록된 직원의 정보를 반환한다.")
    @Test
    void of() {
        //given
        Team team = Team.builder()
                .teamNumber("000")
                .teamName(UNDEFINED)
                .build();
        LocalDate joinDate = LocalDate.of(2020, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        Employee employee = Employee.builder()
                .employeeNumber("001")
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .team(team)
                .build();

        //when
        EmployeeResponse employeeResponse = EmployeeResponse.of(employee);

        //then
        assertThat(employeeResponse)
                .extracting("employeeNumber", "name", "role", "joinDate", "birthday", "teamName")
                .contains("001", "nameA", MEMBER, joinDate, birthday, UNDEFINED);
    }

}