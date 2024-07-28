package hello.miniproject.api.service.team;

import hello.miniproject.api.service.team.request.TeamCreateServiceRequest;
import hello.miniproject.api.service.team.response.TeamResponse;
import hello.miniproject.domain.team.Team;
import hello.miniproject.domain.team.TeamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
@SpringBootTest
class TeamServiceTest {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamRepository teamRepository;

    @AfterEach
    void tearDown() {
        teamRepository.deleteAllInBatch();
    }

    @DisplayName("팀을 저장할 때 등록된 팀이 없다면 팀 번호는 001이며 팀 이름을 지정해야 한다.")
    @Test
    void createTeamFirst() {
        //given
        TeamCreateServiceRequest request = TeamCreateServiceRequest.builder()
                .teamName("teamA")
                .build();

        //when
        TeamResponse teamResponse = teamService.createTeam(request);

        //then
        assertThat(teamResponse)
                .extracting("teamNumber", "teamName")
                .contains("001", "teamA");

    }

    @DisplayName("팀 등록 시 팀 번호는 마지막으로 등록된 팀 번호에 1을 더한 값이며 이름을 지정해야 한다.")
    @Test
    void createTeam() {
        //given
        Team team = Team.builder()
                .teamNumber("001")
                .teamName("teamA")
                .build();
        teamRepository.save(team);

        TeamCreateServiceRequest request = TeamCreateServiceRequest.builder()
                .teamName("teamB")
                .build();

        //when
        TeamResponse teamResponse = teamService.createTeam(request);

        //then
        assertThat(teamResponse)
                .extracting("teamNumber", "teamName")
                .contains("002", "teamB");
    }

    @DisplayName("팀 이름이 중복되어서는 안된다.")
    @Test
    void createTeamNameDuplicate() {
        //given
        Team team = Team.builder()
                .teamNumber("001")
                .teamName("teamA")
                .build();
        teamRepository.save(team);

        TeamCreateServiceRequest request = TeamCreateServiceRequest.builder()
                .teamName("teamA")
                .build();

        //when //then
        assertThatThrownBy(() -> teamService.createTeam(request))
                .hasMessage("중복된 팀 이름은 사용할 수 없습니다.")
                .isInstanceOf(IllegalArgumentException.class);
    }
}