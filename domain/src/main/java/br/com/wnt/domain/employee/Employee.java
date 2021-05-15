package br.com.wnt.domain.employee;

import br.com.wnt.domain.department.Department;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static br.com.wnt.domain.department.Department.DEPARTMENT_FK;
import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

  public static final String TABLE_NAME = "employee";
  public static final int INVALID_AGE = 0;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  private String name;
  private int age;

  @Column(name = "joined_date")
  private LocalDate joinedDate;

  private LocalDateTime created;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = DEPARTMENT_FK)
  private Department department;

  public void updateFields(Employee employee) {
    if (isNotBlank(employee.getName())) this.name = employee.getName();

    if (employee.getAge() > INVALID_AGE) this.age = employee.getAge();

    if (nonNull(employee.getJoinedDate())) this.joinedDate = employee.getJoinedDate();

    if (nonNull(employee.getDepartment())) this.department = employee.getDepartment();
  }
}
