package hello.companysystem.api.service.team.request;

import hello.companysystem.domain.team.Team;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TeamSaveServiceRequest {

    private String teamName;

    @Builder
    private TeamSaveServiceRequest(String teamName) {
        this.teamName = teamName;
    }

    public Team toEntity(String nextTeamNumber) {
        return Team.builder()
                .teamNumber(nextTeamNumber)
                .teamName(this.teamName)
                .build();
    }
}
