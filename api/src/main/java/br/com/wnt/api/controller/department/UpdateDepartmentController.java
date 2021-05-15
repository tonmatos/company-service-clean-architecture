package br.com.wnt.api.controller.department;

import br.com.wnt.application.department.interfaces.UpdateDepartmentUseCase;
import br.com.wnt.application.department.request.UpdateDepartmentRequest;
import br.com.wnt.application.department.response.DepartmentResponse;
import br.com.wnt.domain.department.Department;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateDepartmentController extends BaseDepartmentController {

  private final UpdateDepartmentUseCase updateDepartmentUseCase;

  public UpdateDepartmentController(UpdateDepartmentUseCase updateDepartmentUseCase) {
    this.updateDepartmentUseCase = updateDepartmentUseCase;
  }

  @PutMapping("/{id}")
  public ResponseEntity<DepartmentResponse> updateDepartment(
      @PathVariable("id") long id, @RequestBody UpdateDepartmentRequest updateDepartmentRequest) {
    Department department = updateDepartmentUseCase.execute(id, updateDepartmentRequest);
    return new ResponseEntity<>(DepartmentResponse.from(department), HttpStatus.OK);
  }
}
