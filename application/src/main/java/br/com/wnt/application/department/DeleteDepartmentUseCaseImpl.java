package br.com.wnt.application.department;

import br.com.wnt.application.department.interfaces.DeleteDepartmentUseCase;
import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase;
import br.com.wnt.domain.department.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteDepartmentUseCaseImpl implements DeleteDepartmentUseCase {

  private final DepartmentRepository departmentRepository;
  private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;

  public DeleteDepartmentUseCaseImpl(
      DepartmentRepository departmentRepository,
      GetDepartmentByIdUseCase getDepartmentByIdUseCase) {
    this.departmentRepository = departmentRepository;
    this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
  }

  @Override
  public void execute(long id) {
    departmentRepository.delete(getDepartmentByIdUseCase.execute(id));
  }
}
