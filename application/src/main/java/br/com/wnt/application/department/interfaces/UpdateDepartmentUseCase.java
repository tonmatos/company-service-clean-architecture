package br.com.wnt.application.department.interfaces;

import br.com.wnt.application.department.request.UpdateDepartmentRequest;
import br.com.wnt.domain.department.Department;

public interface UpdateDepartmentUseCase {

  Department execute(long id, UpdateDepartmentRequest updateDepartmentRequest);
}
