package br.com.wnt.application.employee;

import br.com.wnt.application.employee.interfaces.GetEmployeesUseCase;
import br.com.wnt.domain.employee.Employee;
import br.com.wnt.domain.employee.EmployeeRepository;
import br.com.wnt.domain.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetEmployeesUseCaseImpl implements GetEmployeesUseCase {

  private final EmployeeRepository employeeRepository;

  public GetEmployeesUseCaseImpl(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public List<Employee> execute() {
    List<Employee> employees = employeeRepository.findAll();

    if (employees.isEmpty()) throw new NotFoundException(Employee.TABLE_NAME, null);

    return employees;
  }
}
