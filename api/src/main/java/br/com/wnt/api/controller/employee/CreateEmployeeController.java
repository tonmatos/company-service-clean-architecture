package br.com.wnt.api.controller.employee;

import br.com.wnt.application.employee.interfaces.CreateEmployeeUseCase;
import br.com.wnt.application.employee.request.EmployeeRequest;
import br.com.wnt.application.employee.response.EmployeeResponse;
import br.com.wnt.domain.employee.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CreateEmployeeController extends BaseEmployeeController {

  private final CreateEmployeeUseCase createEmployeeUseCase;

  public CreateEmployeeController(CreateEmployeeUseCase createEmployeeUseCase) {
    this.createEmployeeUseCase = createEmployeeUseCase;
  }

  @PostMapping
  public ResponseEntity<EmployeeResponse> createEmployee(
      @Valid @RequestBody EmployeeRequest employeeRequest) {
    Employee employee = createEmployeeUseCase.execute(employeeRequest);

    return new ResponseEntity<>(EmployeeResponse.from(employee), HttpStatus.CREATED);
  }
}
