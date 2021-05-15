package br.com.wnt.api.controller.employee;

import br.com.wnt.application.employee.interfaces.UpdateEmployeeUseCase;
import br.com.wnt.application.employee.request.UpdateEmployeeRequest;
import br.com.wnt.application.employee.response.EmployeeResponse;
import br.com.wnt.domain.employee.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UpdateEmployeeController extends BaseEmployeeController {

  private final UpdateEmployeeUseCase updateEmployeeUseCase;

  public UpdateEmployeeController(UpdateEmployeeUseCase updateEmployeeUseCase) {
    this.updateEmployeeUseCase = updateEmployeeUseCase;
  }

  @PutMapping("/{id}")
  public ResponseEntity<EmployeeResponse> updateEmployee(
      @PathVariable("id") long id,
      @Valid @RequestBody UpdateEmployeeRequest updateEmployeeRequest) {
    Employee employee = updateEmployeeUseCase.execute(id, updateEmployeeRequest);

    return new ResponseEntity<>(EmployeeResponse.from(employee), HttpStatus.CREATED);
  }
}
