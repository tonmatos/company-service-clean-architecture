package br.com.wnt.domain.department;

import br.com.wnt.domain.employee.Employee;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.Objects.nonNull;
import static org.apache.logging.log4j.util.Strings.isNotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Department {

  public static final String TABLE_NAME = "department";
  public static final String DEPARTMENT_FK = "department_id";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name;

  private DepartmentCategory category;

  @JsonIgnore
  @OneToMany(mappedBy = "department")
  private List<Employee> employees;

  private LocalDateTime created;

  public void updateDepartment(Department department) {
    if (isNotBlank(department.getName())) this.name = department.getName();
    if (nonNull(category)) this.category = department.getCategory();
  }
}
