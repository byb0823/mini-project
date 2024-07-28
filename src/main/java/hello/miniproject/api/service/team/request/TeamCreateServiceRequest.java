package hello.miniproject.api.service.team.request;

import hello.miniproject.domain.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamCreateServiceRequest {

    private String teamName;

    @Builder
    private TeamCreateServiceRequest(String teamName) {
        this.teamName = teamName;
    }

    public Team toEntity(String nextTeamNumber) {
        return Team.builder()
                .teamNumber(nextTeamNumber)
                .teamName(this.teamName)
                .build();
    }
}
