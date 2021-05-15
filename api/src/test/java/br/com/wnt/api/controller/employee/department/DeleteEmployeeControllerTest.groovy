package br.com.wnt.api.controller.employee.employee

import br.com.wnt.api.controller.employee.DeleteEmployeeController
import br.com.wnt.application.employee.interfaces.DeleteEmployeeUseCase
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

@CompileDynamic
class DeleteEmployeeControllerTest extends Specification {

    DeleteEmployeeController deleteEmployeeController

    DeleteEmployeeUseCase deleteEmployeeUseCase = Mock()


    void setup() {
        deleteEmployeeController = new DeleteEmployeeController(deleteEmployeeUseCase)
    }

    void cleanup() {
    }

    def "Test delete employee"() {
        given:
        def employeeId = 1

        when:
        def employeeResponse = deleteEmployeeController.deleteEmployee(employeeId)

        then: "check calls"
        1 * deleteEmployeeUseCase.execute(employeeId)

        and: "check the response code"
        employeeResponse.getStatusCode() == HttpStatus.OK
    }
}
