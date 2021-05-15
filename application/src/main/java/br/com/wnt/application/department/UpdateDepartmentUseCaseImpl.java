package br.com.wnt.application.department;

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase;
import br.com.wnt.application.department.interfaces.UpdateDepartmentUseCase;
import br.com.wnt.application.department.request.UpdateDepartmentRequest;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateDepartmentUseCaseImpl implements UpdateDepartmentUseCase {

  private final DepartmentRepository departmentRepository;
  private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;

  public UpdateDepartmentUseCaseImpl(
      DepartmentRepository departmentRepository,
      GetDepartmentByIdUseCase getDepartmentByIdUseCase) {
    this.departmentRepository = departmentRepository;
    this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
  }

  @Override
  public Department execute(long id, UpdateDepartmentRequest updateDepartmentRequest) {
    Department department = getDepartmentByIdUseCase.execute(id);
    department.updateDepartment(updateDepartmentRequest.toDomain());

    return departmentRepository.save(department);
  }
}
