package hello.companysystem.api.controller.employee.request;

import hello.companysystem.api.service.employee.request.EmployeeCreateServiceRequest;
import hello.companysystem.domain.employee.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
public class EmployeeCreateRequest {

    @NotBlank(message = "이름을 입력해주세요.")
    private String name;

    @NotNull(message = "매니저 여부를 입력해 주세요.")
    private Role role;

    @NotNull(message = "회사에 들어온 일자를 입력해주세요.")
    private LocalDate joinDate;

    @NotNull(message = "생일을 입력해주세요.")
    private LocalDate birthday;

    private String teamName;

    @Builder
    private EmployeeCreateRequest(String name, Role role, LocalDate joinDate, LocalDate birthday, String teamName) {
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthday = birthday;
        this.teamName = teamName;
    }

    public EmployeeCreateServiceRequest toServiceRequest() {
        return EmployeeCreateServiceRequest.builder()
                .name(this.name)
                .role(this.role)
                .joinDate(this.joinDate)
                .birthday(this.birthday)
                .teamName(this.teamName)
                .build();
    }
}
