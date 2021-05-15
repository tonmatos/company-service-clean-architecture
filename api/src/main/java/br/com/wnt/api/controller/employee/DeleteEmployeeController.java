package br.com.wnt.api.controller.employee;

import br.com.wnt.application.employee.interfaces.DeleteEmployeeUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteEmployeeController extends BaseEmployeeController {

  private final DeleteEmployeeUseCase deleteEmployeeUseCase;

  public DeleteEmployeeController(DeleteEmployeeUseCase deleteEmployeeUseCase) {
    this.deleteEmployeeUseCase = deleteEmployeeUseCase;
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteEmployee(@PathVariable("id") long id) {
    deleteEmployeeUseCase.execute(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
