package hello.miniproject.api.controller.team;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.miniproject.api.controller.team.request.TeamCreateRequest;
import hello.miniproject.api.service.team.TeamService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("test")
@WebMvcTest(controllers = TeamController.class)
class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private TeamService teamService;

    @DisplayName("팀 생성 시 팀 이름은 필수로 입력해야하며 입력하지 않을 시 예외가 발생한다.")
    @Test
    void createTeamWithoutTeamName() throws Exception {
        //given
        TeamCreateRequest request = TeamCreateRequest.builder()
                .build();

        //when //then
        mockMvc.perform(
                        post("/teams")
                                .content(objectMapper.writeValueAsString(request))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.status").value("BAD_REQUEST"))
                .andExpect(jsonPath("$.message").value("팀 이름을 입력해주세요."))
                .andExpect(jsonPath("$.data").isEmpty());
    }

}