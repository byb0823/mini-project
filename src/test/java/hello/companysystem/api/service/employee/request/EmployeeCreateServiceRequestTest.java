package hello.companysystem.api.service.employee.request;

import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.Role;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static hello.companysystem.domain.employee.Role.*;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class EmployeeCreateServiceRequestTest {

    @DisplayName("등록할 직원 번호를 받으면 직원 엔티티를 생성한다.")
    @Test
    void toEntity() {
        //given
        LocalDate joinDate = LocalDate.of(2020, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        EmployeeCreateServiceRequest request = EmployeeCreateServiceRequest.builder()
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();
        String nextEmployeeNumber = "001";

        //when
        Employee employee = request.toEntity(nextEmployeeNumber, null);

        //then
        assertThat(employee)
                .extracting("employeeNumber", "name", "role", "joinDate", "birthday")
                .contains("001", "nameA", MEMBER, joinDate, birthday);
    }

}