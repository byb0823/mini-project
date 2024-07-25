package hello.companysystem.domain.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    @Query(value = "select employee_number from employee order by id desc limit 1", nativeQuery = true)
    String findLatestEmployeeNumber();

}
