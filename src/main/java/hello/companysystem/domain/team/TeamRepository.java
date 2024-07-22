package hello.companysystem.domain.team;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeamRepository extends JpaRepository<Team, Long> {

    @Query(value = "select team_number from team order by id desc limit 1", nativeQuery = true)
    String findLatestTeamNumber();

}
