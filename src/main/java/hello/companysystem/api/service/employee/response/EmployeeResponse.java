package hello.companysystem.api.service.employee.response;

import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EmployeeResponse {

    private String employeeNumber;
    private String name;
    private Role role;
    private LocalDate joinDate;
    private LocalDate birthday;
    private String teamName;

    @Builder
    private EmployeeResponse(String employeeNumber, String name, Role role, LocalDate joinDate, LocalDate birthday, String teamName) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthday = birthday;
        this.teamName = teamName;
    }

    public static EmployeeResponse of(Employee employee) {
        return EmployeeResponse.builder()
                .employeeNumber(employee.getEmployeeNumber())
                .name(employee.getName())
                .role(employee.getRole())
                .joinDate(employee.getJoinDate())
                .birthday(employee.getBirthday())
                .teamName(employee.getTeam().getTeamName())
                .build();
    }

}
