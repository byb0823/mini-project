package hello.companysystem.domain.employee;

import hello.companysystem.domain.team.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String employeeNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDate joinDate;

    private LocalDate birthday;

    @ManyToOne
    private Team team;

    @Builder
    private Employee(String employeeNumber, String name, Role role, LocalDate joinDate, LocalDate birthday, Team team) {
        this.employeeNumber = employeeNumber;
        this.name = name;
        this.role = role;
        this.joinDate = joinDate;
        this.birthday = birthday;
        this.team = team;
    }

}
