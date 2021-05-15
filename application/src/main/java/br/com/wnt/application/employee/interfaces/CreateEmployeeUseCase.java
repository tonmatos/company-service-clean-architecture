package br.com.wnt.application.employee.interfaces;

import br.com.wnt.application.employee.request.EmployeeRequest;
import br.com.wnt.domain.employee.Employee;

public interface CreateEmployeeUseCase {

  Employee execute(EmployeeRequest employeeRequest);
}
