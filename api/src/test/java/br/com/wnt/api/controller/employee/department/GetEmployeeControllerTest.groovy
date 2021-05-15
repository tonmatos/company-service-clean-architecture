package br.com.wnt.api.controller.employee.employee

import br.com.wnt.api.controller.employee.GetEmployeesController
import br.com.wnt.application.employee.interfaces.GetEmployeesUseCase
import br.com.wnt.application.employee.response.EmployeeResponse
import br.com.wnt.domain.employee.Employee
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.hamcrest.Matchers.containsInAnyOrder
import static spock.util.matcher.HamcrestSupport.that

@CompileDynamic
class GetEmployeeControllerTest extends Specification {

    GetEmployeesController getEmployeeController

    GetEmployeesUseCase getEmployeeUseCase = Mock()


    void setup() {
        getEmployeeController = new GetEmployeesController(getEmployeeUseCase)
    }

    void cleanup() {
    }

    def "Test getAll employees"() {
        given:
        def employee1 = Mock(Employee)
        def employee2 = Mock(Employee)
        def employees = [employee1, employee2]

        when:
        def employeesResponse = getEmployeeController.getAllEmployees()

        then: "check calls"
        1 * getEmployeeUseCase.execute() >> employees

        and: "check the response code"
        employeesResponse.getStatusCode() == HttpStatus.OK

        that employeesResponse.getBody(), containsInAnyOrder(EmployeeResponse.from(employee1), EmployeeResponse.from(employee2))
    }
}
