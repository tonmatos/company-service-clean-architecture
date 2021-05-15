package br.com.wnt.application.department;

import br.com.wnt.application.department.interfaces.GetDepartmentsUseCase;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentRepository;
import br.com.wnt.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetDepartmentsUseCaseImpl implements GetDepartmentsUseCase {

  private final DepartmentRepository departmentRepository;

  public GetDepartmentsUseCaseImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public List<Department> execute() {
    List<Department> departments = departmentRepository.findAll();

    if (departments.isEmpty()) throw new NotFoundException(Department.TABLE_NAME, null);

    return departments;
  }
}
