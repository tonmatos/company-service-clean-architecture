package br.com.wnt.application.department.interfaces;

import br.com.wnt.domain.department.Department;

import java.util.List;

public interface GetDepartmentsUseCase {

  List<Department> execute();
}
