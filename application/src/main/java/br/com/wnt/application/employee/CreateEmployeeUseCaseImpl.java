package br.com.wnt.application.employee;

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase;
import br.com.wnt.application.employee.interfaces.CreateEmployeeUseCase;
import br.com.wnt.application.employee.request.EmployeeRequest;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.employee.Employee;
import br.com.wnt.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class CreateEmployeeUseCaseImpl implements CreateEmployeeUseCase {

  private final EmployeeRepository employeeRepository;
  private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;

  public CreateEmployeeUseCaseImpl(
      EmployeeRepository employeeRepository, GetDepartmentByIdUseCase getDepartmentByIdUseCase) {
    this.employeeRepository = employeeRepository;
    this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
  }

  @Override
  public Employee execute(EmployeeRequest employeeRequest) {
    Department department = getDepartmentByIdUseCase.execute(employeeRequest.getDepartmentId());
    Employee employee = employeeRequest.toDomain();
    employee.setDepartment(department);

    return employeeRepository.save(employee);
  }
}
