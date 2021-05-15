package br.com.wnt.api.controller.employee.employee

import br.com.wnt.api.controller.employee.UpdateEmployeeController
import br.com.wnt.application.employee.interfaces.UpdateEmployeeUseCase
import br.com.wnt.application.employee.request.UpdateEmployeeRequest
import br.com.wnt.domain.department.Department
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDate

@CompileDynamic
class UpdateEmployeeControllerTest extends Specification {

    UpdateEmployeeController updateEmployeeController
    UpdateEmployeeUseCase updateEmployeeUseCase = Mock()

    UpdateEmployeeRequest updateEmployeeRequest
    static final int EMPLOYEE_AGE = 25
    static final int DEPARTMENT_ID = 2


    void setup() {
        updateEmployeeController = new UpdateEmployeeController(updateEmployeeUseCase)

        updateEmployeeRequest = UpdateEmployeeRequest.builder()
                .name("EmployeeNameUpdated")
                .age(EMPLOYEE_AGE)
                .joinedDate(LocalDate.now().minusYears(EMPLOYEE_AGE))
                .departmentId(DEPARTMENT_ID)
                .build()
    }

    def "Test update employee"() {
        given:
        def employeeId = 1
        def employee = updateEmployeeRequest.toDomain(Mock(Department))

        when:
        def employeeResponse = updateEmployeeController
                .updateEmployee(employeeId, updateEmployeeRequest)

        then: "check calls"
        1 * updateEmployeeUseCase.execute(employeeId, updateEmployeeRequest) >> employee

        and: "check the response code"
        employeeResponse.getStatusCode() == HttpStatus.CREATED

        and: "check if the body contains all the expected information"
        def responseBody = employeeResponse.getBody()
        responseBody.getId() == employee.getId()
        responseBody.getName() == employee.getName()
        responseBody.getAge() == employee.getAge()
        responseBody.getJoinedDate() == employee.getJoinedDate()
        responseBody.getDepartment() == employee.getDepartment()
        responseBody.getCreated() == employee.getCreated()
    }
}
