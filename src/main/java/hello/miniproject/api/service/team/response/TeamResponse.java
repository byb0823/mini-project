package hello.miniproject.api.service.team.response;

import hello.miniproject.domain.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamResponse {

    private String teamNumber;
    private String teamName;

    @Builder
    private TeamResponse(String teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
    }

    public static TeamResponse of(Team savedTeam) {
        return TeamResponse.builder()
                .teamNumber(savedTeam.getTeamNumber())
                .teamName(savedTeam.getTeamName())
                .build();
    }
}
