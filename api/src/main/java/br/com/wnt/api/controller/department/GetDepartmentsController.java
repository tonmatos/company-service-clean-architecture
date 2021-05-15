package br.com.wnt.api.controller.department;

import br.com.wnt.application.department.interfaces.GetDepartmentsUseCase;
import br.com.wnt.application.department.response.DepartmentResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetDepartmentsController extends BaseDepartmentController {

  private final GetDepartmentsUseCase getDepartmentsUseCase;

  public GetDepartmentsController(GetDepartmentsUseCase getDepartmentsUseCase) {
    this.getDepartmentsUseCase = getDepartmentsUseCase;
  }

  @GetMapping
  public ResponseEntity<List<DepartmentResponse>> getAllDepartments() {
    return new ResponseEntity<>(
        DepartmentResponse.from(getDepartmentsUseCase.execute()), HttpStatus.OK);
  }
}
