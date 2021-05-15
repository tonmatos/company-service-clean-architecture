package br.com.wnt.api.controller.department;

import br.com.wnt.application.department.interfaces.CreateDepartmentUseCase;
import br.com.wnt.application.department.request.DepartmentRequest;
import br.com.wnt.application.department.response.DepartmentResponse;
import br.com.wnt.domain.department.Department;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CreateDepartmentController extends BaseDepartmentController {

  private final CreateDepartmentUseCase createDepartmentUseCase;

  public CreateDepartmentController(CreateDepartmentUseCase createDepartmentUseCase) {
    this.createDepartmentUseCase = createDepartmentUseCase;
  }

  @PostMapping
  public ResponseEntity<DepartmentResponse> createDepartment(
      @RequestBody DepartmentRequest departmentRequest) {
    Department department = createDepartmentUseCase.execute(departmentRequest);
    return new ResponseEntity<>(
            DepartmentResponse.from(department), HttpStatus.CREATED);
  }
}
