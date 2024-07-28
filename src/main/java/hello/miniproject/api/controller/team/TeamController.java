package hello.miniproject.api.controller.team;

import hello.miniproject.api.ApiResponse;
import hello.miniproject.api.controller.team.request.TeamCreateRequest;
import hello.miniproject.api.service.team.TeamService;
import hello.miniproject.api.service.team.response.TeamResponse;
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
