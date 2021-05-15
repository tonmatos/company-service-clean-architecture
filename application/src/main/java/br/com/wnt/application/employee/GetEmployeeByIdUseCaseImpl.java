package br.com.wnt.application.employee;

import br.com.wnt.application.employee.interfaces.GetEmployeeByIdUseCase;
import br.com.wnt.domain.employee.Employee;
import br.com.wnt.domain.employee.EmployeeRepository;
import br.com.wnt.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class GetEmployeeByIdUseCaseImpl implements GetEmployeeByIdUseCase {

  private final EmployeeRepository employeeRepository;

  public GetEmployeeByIdUseCaseImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public Employee execute(long id) {
    return employeeRepository
        .findById(id)
        .orElseThrow(() -> new NotFoundException(Employee.TABLE_NAME, String.valueOf(id)));
  }
}
