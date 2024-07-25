package hello.companysystem.api.controller.team.request;

import hello.companysystem.api.service.team.request.TeamCreateServiceRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@ActiveProfiles("test")
class TeamCreateRequestTest {

    @DisplayName("컨트롤러에서 사용하는 dto는 서비스에서 사용하는 객체로 바꿔줘야 한다.")
    @Test
    void toServiceRequest() {
        //given
        TeamCreateRequest request = TeamCreateRequest.builder()
                .teamName("teamA")
                .build();

        //when
        TeamCreateServiceRequest serviceRequest = request.toServiceRequest();

        //then
        assertThat(serviceRequest.getTeamName()).isEqualTo("teamA");
    }

}