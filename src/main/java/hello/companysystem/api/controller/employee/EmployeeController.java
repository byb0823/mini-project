package hello.companysystem.api.controller.employee;

import hello.companysystem.api.ApiResponse;
import hello.companysystem.api.controller.employee.request.EmployeeCreateRequest;
import hello.companysystem.api.service.employee.EmployeeService;
import hello.companysystem.api.service.employee.response.EmployeeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping("/employees")
    public ApiResponse<EmployeeResponse> createEmployee(@Valid @RequestBody EmployeeCreateRequest request) {
        return ApiResponse.ok(employeeService.createEmployee(request.toServiceRequest()));
    }

}
