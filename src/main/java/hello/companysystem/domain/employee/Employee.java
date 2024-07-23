package hello.companysystem.domain.employee;

import hello.companysystem.domain.team.Team;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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

}
