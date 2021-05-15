package br.com.wnt.api.controller.department;

import br.com.wnt.application.department.interfaces.DeleteDepartmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeleteDepartmentController extends BaseDepartmentController {

  private final DeleteDepartmentUseCase deleteDepartmentUseCase;

  public DeleteDepartmentController(DeleteDepartmentUseCase deleteDepartmentUseCase) {
    this.deleteDepartmentUseCase = deleteDepartmentUseCase;
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteDepartment(@PathVariable("id") long id) {
    deleteDepartmentUseCase.execute(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
