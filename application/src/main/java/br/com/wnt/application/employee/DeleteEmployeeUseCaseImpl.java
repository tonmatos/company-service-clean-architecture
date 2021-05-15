package br.com.wnt.application.employee;

import br.com.wnt.application.employee.interfaces.DeleteEmployeeUseCase;
import br.com.wnt.application.employee.interfaces.GetEmployeeByIdUseCase;
import br.com.wnt.domain.employee.Employee;
import br.com.wnt.domain.employee.EmployeeRepository;
import org.springframework.stereotype.Service;

@Service
public class DeleteEmployeeUseCaseImpl implements DeleteEmployeeUseCase {

  private final EmployeeRepository employeeRepository;
  private final GetEmployeeByIdUseCase getEmployeeByIdUseCase;

  public DeleteEmployeeUseCaseImpl(
      EmployeeRepository employeeRepository, GetEmployeeByIdUseCase getEmployeeByIdUseCase) {
    this.employeeRepository = employeeRepository;
    this.getEmployeeByIdUseCase = getEmployeeByIdUseCase;
  }

  @Override
  public void execute(long id) {
    Employee employee = getEmployeeByIdUseCase.execute(id);
    employeeRepository.delete(employee);
  }
}
