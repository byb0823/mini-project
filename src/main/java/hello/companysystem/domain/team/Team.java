package hello.companysystem.domain.team;

import hello.companysystem.domain.employee.Employee;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamNumber;

    private String teamName;

    @OneToMany(mappedBy = "team")
    private List<Employee> employees = new ArrayList<>();

 }
