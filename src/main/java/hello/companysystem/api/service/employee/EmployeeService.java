package hello.companysystem.api.service.employee;

import hello.companysystem.api.service.employee.request.EmployeeCreateServiceRequest;
import hello.companysystem.api.service.employee.response.EmployeeResponse;
import hello.companysystem.domain.employee.Employee;
import hello.companysystem.domain.employee.EmployeeRepository;
import hello.companysystem.domain.team.Team;
import hello.companysystem.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static hello.companysystem.domain.team.TeamConst.*;

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
