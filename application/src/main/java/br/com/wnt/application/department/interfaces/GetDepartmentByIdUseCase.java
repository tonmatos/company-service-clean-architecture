package br.com.wnt.application.department.interfaces;

import br.com.wnt.domain.department.Department;

public interface GetDepartmentByIdUseCase {

  Department execute(long id);
}
