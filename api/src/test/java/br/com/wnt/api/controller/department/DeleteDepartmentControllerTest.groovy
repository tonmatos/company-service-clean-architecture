package br.com.wnt.api.controller.department

import br.com.wnt.application.department.interfaces.DeleteDepartmentUseCase
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

@CompileDynamic
class DeleteDepartmentControllerTest extends Specification {

    DeleteDepartmentController deleteDepartmentController

    DeleteDepartmentUseCase deleteDepartmentUseCase = Mock()


    void setup() {
        deleteDepartmentController = new DeleteDepartmentController(deleteDepartmentUseCase)
    }

    void cleanup() {
    }

    def "Test delete department"() {
        given:
        def departmentId = 1

        when:
        def departmentResponse = deleteDepartmentController.deleteDepartment(departmentId)

        then: "check calls"
        1 * deleteDepartmentUseCase.execute(departmentId)

        and: "check the response code"
        departmentResponse.getStatusCode() == HttpStatus.OK
    }
}
