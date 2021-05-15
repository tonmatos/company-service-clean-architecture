package br.com.wnt.application.employee.request;

import br.com.wnt.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeRequest {

  @NotBlank(message = "Name is required.")
  private String name;

  @NotNull private int age;

  private LocalDate joinedDate;

  @NotNull private int departmentId;

  public Employee toDomain() {
    return Employee.builder()
        .name(this.name)
        .age(this.age)
        .joinedDate(this.joinedDate)
        .created(LocalDateTime.now())
        .build();
  }
}
