package hello.companysystem.api.service.employee;

import hello.companysystem.api.service.employee.request.EmployeeCreateServiceRequest;
import hello.companysystem.api.service.employee.response.EmployeeResponse;
import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.EmployeeRepository;
import hello.companysystem.domain.team.Team;
import hello.companysystem.domain.team.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static hello.companysystem.domain.employee.Role.MEMBER;
import static hello.companysystem.domain.team.TeamConst.UNDEFINED;
import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@SpringBootTest
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TeamRepository teamRepository;

    @BeforeEach
    void beforeEach() {
        employeeRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @AfterEach
    void tearDown() {
        employeeRepository.deleteAllInBatch();
        teamRepository.deleteAllInBatch();
    }

    @DisplayName("직원을 등록할 때는 직원 번호를 넣어야 함. 최초 등록 직원은 직원번호가 001이다.")
    @Test
    void createFirstEmployee() {
        //given
        Team team = Team.builder()
                .teamNumber("000")
                .teamName(UNDEFINED)
                .build();
        teamRepository.save(team);

        LocalDate joinDate = LocalDate.of(2024, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        EmployeeCreateServiceRequest request = EmployeeCreateServiceRequest.builder()
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();

        //when
        EmployeeResponse response = employeeService.createEmployee(request);

        //then
        assertThat(response)
                .extracting("employeeNumber", "name", "role", "joinDate", "birthday", "teamName")
                .contains("001", "nameA", MEMBER, joinDate, birthday, UNDEFINED);
    }

    @DisplayName("직원을 등록할 때는 직원 번호를 넣어야하며 마지막을 등록한 직원의 번호 + 1이 직원 번호로 저장된다.")
    @Test
    void createEmployee() {
        //given
        LocalDate joinDate = LocalDate.of(2024, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        Employee employee = Employee.builder()
                .employeeNumber("001")
                .name("prevEmployee")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .build();
        employeeRepository.save(employee);

        Team team = Team.builder()
                .teamNumber("000")
                .teamName(UNDEFINED)
                .build();
        teamRepository.save(team);

        EmployeeCreateServiceRequest request = EmployeeCreateServiceRequest.builder()
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();

        //when
        EmployeeResponse response = employeeService.createEmployee(request);

        //then
        assertThat(response)
                .extracting("employeeNumber", "name", "role", "joinDate", "birthday", "teamName")
                .contains("002", "nameA", MEMBER, joinDate, birthday, UNDEFINED);
    }

    @DisplayName("팀이 지정되지 않은 직원은 UNDEFINED로 팀을 저장한다.")
    @Test
    void createEmployeeUndefined() {
        //given
        Team team = Team.builder()
                .teamNumber("000")
                .teamName(UNDEFINED)
                .build();
        teamRepository.save(team);

        LocalDate joinDate = LocalDate.of(2024, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        EmployeeCreateServiceRequest request = EmployeeCreateServiceRequest.builder()
                .name("nameA")
                .role(MEMBER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();

        //when
        EmployeeResponse response = employeeService.createEmployee(request);

        //then
        assertThat(response)
                .extracting("employeeNumber", "name", "role", "joinDate", "birthday", "teamName")
                .contains("001", "nameA", MEMBER, joinDate, birthday, UNDEFINED);
    }

}