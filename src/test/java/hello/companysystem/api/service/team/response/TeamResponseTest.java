package hello.companysystem.api.service.team.response;

import hello.companysystem.domain.team.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles("test")
class TeamResponseTest {

    @DisplayName("팀을 저장하면 팀과 관련된 정보를 반환한다.")
    @Test
    void of() {
        //given
        Team team = Team.builder()
                .teamNumber("001")
                .teamName("teamA")
                .build();

        //when
        TeamResponse teamResponse = TeamResponse.of(team);

        //then
        assertThat(teamResponse)
                .extracting("teamNumber", "teamName")
                .contains("001", "teamA");
    }

}