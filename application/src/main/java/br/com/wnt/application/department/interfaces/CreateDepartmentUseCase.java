package br.com.wnt.application.department.interfaces;

import br.com.wnt.application.department.request.DepartmentRequest;
import br.com.wnt.domain.department.Department;

public interface CreateDepartmentUseCase {

  Department execute(DepartmentRequest departmentRequest);
}
