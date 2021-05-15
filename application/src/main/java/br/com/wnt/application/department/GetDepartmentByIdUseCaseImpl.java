package br.com.wnt.application.department;

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentRepository;
import br.com.wnt.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetDepartmentByIdUseCaseImpl implements GetDepartmentByIdUseCase {

  private final DepartmentRepository departmentRepository;

  public GetDepartmentByIdUseCaseImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Department execute(long id) {
    return departmentRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(Department.TABLE_NAME, String.valueOf(id)));
  }
}
