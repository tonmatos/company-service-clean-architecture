package br.com.wnt.application.department.response;

import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentCategory;
import br.com.wnt.domain.employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentResponse {

  private Long id;
  private String name;
  private DepartmentCategory category;
  private List<Employee> employees;
  private LocalDateTime created;

  public static DepartmentResponse from(Department department) {
    return DepartmentResponse.builder()
        .id(department.getId())
        .name(department.getName())
        .category(department.getCategory())
        .employees(department.getEmployees())
        .created(department.getCreated())
        .build();
  }

  public static List<DepartmentResponse> from(List<Department> departments) {
    return departments.stream().map(DepartmentResponse::from).collect(Collectors.toList());
  }
}
