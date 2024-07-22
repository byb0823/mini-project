package hello.companysystem.domain.team;

import hello.companysystem.domain.employee.Employee;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamNumber;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees = new ArrayList<>();

    @Builder
    private Team(String teamNumber, String teamName) {
        this.teamNumber = teamNumber;
        this.teamName = teamName;
    }

}
