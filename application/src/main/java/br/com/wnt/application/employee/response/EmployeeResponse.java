package br.com.wnt.application.employee.response;

import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeResponse {

  private Long id;
  private String name;
  private int age;
  private LocalDate joinedDate;
  private LocalDateTime created;
  private Department department;

  public static EmployeeResponse from(Employee employee) {
    return EmployeeResponse.builder()
        .id(employee.getId())
        .name(employee.getName())
        .age(employee.getAge())
        .joinedDate(employee.getJoinedDate())
        .department(employee.getDepartment())
        .created(employee.getCreated())
        .build();
  }

  public static List<EmployeeResponse> from(List<Employee> employees) {
    return employees.stream().map(EmployeeResponse::from).collect(Collectors.toList());
  }
}
