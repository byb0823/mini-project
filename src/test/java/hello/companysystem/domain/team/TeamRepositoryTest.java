package hello.companysystem.domain.team;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
class TeamRepositoryTest {

    @Autowired
    private TeamRepository teamRepository;

    @AfterEach
    void tearDown() {
        teamRepository.deleteAllInBatch();
    }

    @DisplayName("현재 등록된 팀이 없을 경우 null을 반환한다.")
    @Test
    void findLatestTeamNumberWithoutAnyTeam() {
        //given //when
        String latestTeamNumber = teamRepository.findLatestTeamNumber();

        //then
        assertThat(latestTeamNumber).isNull();
    }

    @DisplayName("현재 등록 되어 있는 팀 중 가장 마지막으로 생성된 팀의 teamNumber를 반환한다.")
    @Test
    void findLatestTeamNumber() {
        //given
        Team team1 = Team.builder()
                .teamNumber("001")
                .teamName("teamA")
                .build();
        Team team2 = Team.builder()
                .teamNumber("002")
                .teamName("teamB")
                .build();
        teamRepository.saveAll(List.of(team1, team2));

        //when
        String latestTeamNumber = teamRepository.findLatestTeamNumber();

        //then
        assertThat(latestTeamNumber).isEqualTo("002");
    }

    @DisplayName("팀 이름으로 팀을 찾아서 반환한다.")
    @Test
    void findByTeamName() {
        //given
        String targetTeamName = "teamA";
        Team team = Team.builder()
                .teamNumber("001")
                .teamName(targetTeamName)
                .build();
        teamRepository.save(team);

        //when
        Team targetTeam = teamRepository.findByTeamName(targetTeamName);

        //then
        assertThat(targetTeam)
                .extracting("teamNumber", "teamName")
                .contains("001", targetTeamName);
    }

    @DisplayName("찾는 팀 이름을 가진 팀이 없다면 null을 반환한다.")
    @Test
    void findByTeamNameWithoutTeam() {
        //given
        String targetTeamName = "teamA";

        //when
        Team targetTeam = teamRepository.findByTeamName(targetTeamName);

        //then
        assertThat(targetTeam).isNull();
    }

}