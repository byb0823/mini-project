package hello.companysystem.api.controller.team;

import hello.companysystem.api.ApiResponse;
import hello.companysystem.api.controller.team.request.TeamCreateRequest;
import hello.companysystem.api.service.team.TeamService;
import hello.companysystem.api.service.team.response.TeamResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TeamController {

    private final TeamService teamService;

    @PostMapping("/teams")
    public ApiResponse<TeamResponse> creatTeam(@Valid @RequestBody TeamCreateRequest request) {
        return ApiResponse.ok(teamService.createTeam(request.toServiceRequest()));
    }

}
