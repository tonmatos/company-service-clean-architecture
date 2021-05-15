package br.com.wnt.application.department;

import br.com.wnt.application.department.interfaces.CreateDepartmentUseCase;
import br.com.wnt.application.department.request.DepartmentRequest;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.department.DepartmentRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateDepartmentUseCaseImpl implements CreateDepartmentUseCase {

  private final DepartmentRepository departmentRepository;

  public CreateDepartmentUseCaseImpl(DepartmentRepository departmentRepository) {
    this.departmentRepository = departmentRepository;
  }

  @Override
  public Department execute(DepartmentRequest departmentRequest) {
    return departmentRepository.save(departmentRequest.toDomain());
  }
}
