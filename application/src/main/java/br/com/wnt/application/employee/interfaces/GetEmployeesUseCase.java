package br.com.wnt.application.employee.interfaces;

import br.com.wnt.domain.employee.Employee;

import java.util.List;

public interface GetEmployeesUseCase {

  List<Employee> execute();
}
