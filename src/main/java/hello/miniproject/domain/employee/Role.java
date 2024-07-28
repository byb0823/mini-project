package hello.miniproject.domain.employee;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    MEMBER("멤버"),
    MANAGER("매니저");

    private final String text;

}
