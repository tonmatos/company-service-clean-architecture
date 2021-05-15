package br.com.wnt.application.employee;

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase;
import br.com.wnt.application.employee.interfaces.GetEmployeeByIdUseCase;
import br.com.wnt.application.employee.interfaces.UpdateEmployeeUseCase;
import br.com.wnt.application.employee.request.UpdateEmployeeRequest;
import br.com.wnt.domain.department.Department;
import br.com.wnt.domain.employee.Employee;
import br.com.wnt.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeUseCaseImpl implements UpdateEmployeeUseCase {

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;
  private final GetDepartmentByIdUseCase getDepartmentByIdUseCase;

  public UpdateEmployeeUseCaseImpl(
      EmployeeRepository employeeRepository,
      GetEmployeeByIdUseCase getEmployeeByIdUseCase,
      GetDepartmentByIdUseCase getDepartmentByIdUseCase) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
    this.getDepartmentByIdUseCase = getDepartmentByIdUseCase;
  }

  @Override
  public Employee execute(long id, UpdateEmployeeRequest updateEmployeeRequest) {
    Employee employee = getEmployeeByIdUseCase.execute(id);
    Department department =
        getDepartmentByIdUseCase.execute(updateEmployeeRequest.getDepartmentId());

    Employee updateEmployee = updateEmployeeRequest.toDomain(department);
    employee.updateFields(updateEmployee);

    return employeeRepository.save(employee);
  }
}
