package hello.companysystem.api.service.team;

import hello.companysystem.api.service.team.request.TeamCreateServiceRequest;
import hello.companysystem.api.service.team.response.TeamResponse;
import hello.companysystem.domain.team.Team;
import hello.companysystem.domain.team.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class TeamService {

    private final TeamRepository teamRepository;

    @Transactional
    public TeamResponse createTeam(TeamCreateServiceRequest request) {
        String nextTeamNumber = getNextTeamNumber();
        Team team = request.toEntity(nextTeamNumber);
        Team savedTeam = teamRepository.save(team);
        return TeamResponse.of(savedTeam);
    }

    private String getNextTeamNumber() {
        String latestTeamNumber = teamRepository.findLatestTeamNumber();
        if (latestTeamNumber == null) {
            return "001";
        }
        int nextTeamNumber = Integer.parseInt(latestTeamNumber) + 1;
        return String.format("%03d", nextTeamNumber);
    }
}
