package hello.companysystem.api.controller.team.request;

import hello.companysystem.api.service.team.request.TeamCreateServiceRequest;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TeamCreateRequest {

    @NotBlank(message = "팀 이름을 입력해주세요.")
    private String teamName;

    @Builder
    private TeamCreateRequest(String teamName) {
        this.teamName = teamName;
    }

    public TeamCreateServiceRequest toServiceRequest() {
        return TeamCreateServiceRequest.builder()
                .teamName(this.teamName)
                .build();
    }
}
