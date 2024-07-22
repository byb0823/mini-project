package hello.companysystem.domain.employee;

import jakarta.persistence.*;

import java.time.LocalDate;

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

    private LocalDate birthDay;

}
