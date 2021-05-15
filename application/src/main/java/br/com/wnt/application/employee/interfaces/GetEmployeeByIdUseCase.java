package br.com.wnt.application.employee.interfaces;

import br.com.wnt.domain.employee.Employee;

public interface GetEmployeeByIdUseCase {

  Employee execute(long id);
}
