package hello.companysystem.api.service.team;

import hello.companysystem.api.service.team.request.TeamSaveServiceRequest;
import hello.companysystem.api.service.team.response.TeamResponse;
import hello.companysystem.domain.team.Team;
import hello.companysystem.domain.team.TeamRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @DisplayName("팀 등록 시 팀 번호와 이름을 지정해야 한다.")
    @Test
    void createTeamFirst() {
        //given
        TeamSaveServiceRequest request = TeamSaveServiceRequest.builder()
                .teamName("teamA")
                .build();

        //when
        TeamResponse teamResponse = teamService.createTeam(request);

        //then
        assertThat(teamResponse)
                .extracting("teamNumber", "teamName")
                .contains("001", "teamA");

    }
}