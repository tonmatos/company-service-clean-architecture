package br.com.wnt.application.employee.interfaces;

import br.com.wnt.application.employee.request.UpdateEmployeeRequest;
import br.com.wnt.domain.employee.Employee;

public interface UpdateEmployeeUseCase {

  Employee execute(long id, UpdateEmployeeRequest updateEmployeeRequest);
}
