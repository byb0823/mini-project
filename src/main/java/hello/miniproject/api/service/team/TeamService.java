package hello.miniproject.api.service.team;

import hello.miniproject.api.service.team.request.TeamCreateServiceRequest;
import hello.miniproject.api.service.team.response.TeamResponse;
import hello.miniproject.domain.team.Team;
import hello.miniproject.domain.team.TeamRepository;
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
        if (isTeamNameExist(request.getTeamName())) {
            throw new IllegalArgumentException("중복된 팀 이름은 사용할 수 없습니다.");
        }
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

    private boolean isTeamNameExist(String teamName) {
        return teamRepository.findByTeamName(teamName) != null;
    }
}
