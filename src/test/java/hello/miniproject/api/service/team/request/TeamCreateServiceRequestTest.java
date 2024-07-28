package hello.miniproject.api.service.team.request;

import hello.miniproject.domain.team.Team;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
class TeamCreateServiceRequestTest {

    @DisplayName("팀 번호를 받아 팀 엔티티를 생성한다.")
    @Test
    void toEntity() {
        //given
        String nextTeamNumber = "001";
        TeamCreateServiceRequest request = TeamCreateServiceRequest.builder()
                .teamName("teamA")
                .build();

        //when
        Team team = request.toEntity(nextTeamNumber);

        //then
        assertThat(team)
                .extracting("teamNumber", "teamName")
                .contains("001", "teamA");
    }

}