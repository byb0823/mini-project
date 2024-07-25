package hello.companysystem.api.service.employee.request;

import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.Role;
import hello.companysystem.domain.team.Team;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class EmployeeCreateServiceRequest {

    private String name;
    private Role role;
    private LocalDate joinDate;
    private LocalDate birthday;
    private String teamName;

    @Builder
    private EmployeeCreateServiceRequest(String name, Role role, LocalDate joinDate, LocalDate birthday, String teamName) {
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthday = birthday;
        this.teamName = teamName;
    }

    public Employee toEntity(String nextEmployeeNumber, Team team) {
        return Employee.builder()
                .employeeNumber(nextEmployeeNumber)
                .name(this.name)
                .role(this.role)
                .joinDate(this.joinDate)
                .birthday(this.birthday)
                .team(team)
                .build();
    }
}
