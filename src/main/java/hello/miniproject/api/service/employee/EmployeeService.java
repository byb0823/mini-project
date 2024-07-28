package hello.miniproject.api.service.employee;

import hello.miniproject.api.service.employee.request.EmployeeCreateServiceRequest;
import hello.miniproject.api.service.employee.response.EmployeeResponse;
import hello.miniproject.domain.employee.Employee;
import hello.miniproject.domain.employee.EmployeeRepository;
import hello.miniproject.domain.team.Team;
import hello.miniproject.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hello.miniproject.domain.team.TeamConst.*;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TeamRepository teamRepository;

    @Transactional
    public EmployeeResponse createEmployee(EmployeeCreateServiceRequest request) {
        String nextEmployeeNumber = getNextEmployeeNumber();

        Team team = findTeamByTeamName(request.getTeamName());
        if (team == null) {
            throw new IllegalArgumentException("존재하지 않는 팀 이름입니다.");
        }

        Employee employee = request.toEntity(nextEmployeeNumber, team);
        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeResponse.of(savedEmployee);
    }

    private String getNextEmployeeNumber() {
        String latestEmployeeNumber = employeeRepository.findLatestEmployeeNumber();
        if (latestEmployeeNumber == null) {
            return "001";
        }
        int nextEmployeeNumber = Integer.parseInt(latestEmployeeNumber) + 1;
        return String.format("%03d", nextEmployeeNumber);
    }

    private Team findTeamByTeamName(String teamName) {
        return (teamName == null)
                ? teamRepository.findByTeamName(UNDEFINED)
                : teamRepository.findByTeamName(teamName);
    }

}
