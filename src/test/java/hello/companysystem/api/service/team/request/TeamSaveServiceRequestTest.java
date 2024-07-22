package hello.companysystem.api.service.team.request;

import hello.companysystem.domain.team.Team;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class TeamSaveServiceRequestTest {

    @DisplayName("팀 번호를 받아 팀 엔티티를 생성한다.")
    @Test
    void toEntity() {
        //given
        String nextTeamNumber = "001";
        TeamSaveServiceRequest request = TeamSaveServiceRequest.builder()
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