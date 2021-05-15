package br.com.wnt.application.department.request;

import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentRequest {

  @NotBlank private String name;

  @NotNull private DepartmentCategory category;

  public Department toDomain() {
    return Department.builder()
        .name(this.name)
        .category(this.category)
        .created(LocalDateTime.now())
        .build();
  }
}
