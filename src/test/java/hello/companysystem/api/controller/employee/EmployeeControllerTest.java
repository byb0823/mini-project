package hello.companysystem.api.controller.employee;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.companysystem.api.controller.employee.request.EmployeeCreateRequest;
import hello.companysystem.api.service.employee.EmployeeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static hello.companysystem.domain.employee.Role.MANAGER;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(controllers = EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private EmployeeService employeeService;

    @DisplayName("직원을 등록하면 해당 직원에 대한 정보가 반환된다.")
    @Test
    void createEmployee() throws Exception {
        //given
        LocalDate joinDate = LocalDate.of(2020, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name("nameA")
                .role(MANAGER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();

        //when //then
        mockMvc.perform(
                        post("/employees")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.message").value("OK"));
    }

    @DisplayName("직원 등록 시 이름, 매니저 여부, 회사에 들어온 일자, 생일을 필수로 입력해야 하며 그렇지 않으면 예외가 발생한다.")
    @Test
    void createEmployeeWithoutRequired() throws Exception {
        //given
        LocalDate joinDate = LocalDate.of(2020, 1, 1);
        LocalDate birthday = LocalDate.of(2001, 1, 1);
        EmployeeCreateRequest request = EmployeeCreateRequest.builder()
                .name(null)
                .role(MANAGER)
                .joinDate(joinDate)
                .birthday(birthday)
                .teamName(null)
                .build();

        //when //then
        mockMvc.perform(
                        post("/employees")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("이름을 입력해주세요."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

}