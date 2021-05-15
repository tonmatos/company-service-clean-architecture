package br.com.wnt.application.employee.request;

import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateEmployeeRequest {

  @NotEmpty(message = "Name is required.")
  private String name;

  @NotNull private int age;

  @NotNull private LocalDate joinedDate;

  @NotNull private int departmentId;

  public Employee toDomain(Department department) {
    return Employee.builder()
        .name(this.name)
        .age(this.age)
        .joinedDate(this.joinedDate)
        .department(department)
        .created(LocalDateTime.now())
        .build();
  }
}
