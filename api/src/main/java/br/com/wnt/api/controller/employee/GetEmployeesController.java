package br.com.wnt.api.controller.employee;

import br.com.wnt.application.employee.interfaces.GetEmployeesUseCase;
import br.com.wnt.application.employee.response.EmployeeResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GetEmployeesController extends BaseEmployeeController {

  private final GetEmployeesUseCase getEmployeesUseCase;

  public GetEmployeesController(GetEmployeesUseCase getEmployeesUseCase) {
    this.getEmployeesUseCase = getEmployeesUseCase;
  }

  @GetMapping
  public ResponseEntity<List<EmployeeResponse>> getAllEmployees() {
    return new ResponseEntity<>(
        EmployeeResponse.from(getEmployeesUseCase.execute()), HttpStatus.OK);
  }
}
