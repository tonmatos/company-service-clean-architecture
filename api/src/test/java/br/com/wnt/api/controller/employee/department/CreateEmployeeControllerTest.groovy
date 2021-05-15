package br.com.wnt.api.controller.employee.employee

import br.com.wnt.api.controller.employee.CreateEmployeeController
import br.com.wnt.application.employee.interfaces.CreateEmployeeUseCase
import br.com.wnt.application.employee.request.EmployeeRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.employee.Employee
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDate

@CompileDynamic
class CreateEmployeeControllerTest extends Specification {

    CreateEmployeeController createEmployeeController
    CreateEmployeeUseCase createEmployeeUseCase = Mock()

    EmployeeRequest employeeRequest
    static final int EMPLOYEE_AGE = 20
    static final int DEPARTMENT_ID = 1


    void setup() {
        createEmployeeController = new CreateEmployeeController(createEmployeeUseCase)
        employeeRequest = EmployeeRequest.builder()
                .name("EmployeeName")
                .age(EMPLOYEE_AGE)
                .joinedDate(LocalDate.now().minusYears(EMPLOYEE_AGE))
                .departmentId(DEPARTMENT_ID)
                .build()
    }

    def "Test create employee"() {
        given:
        Employee employee = employeeRequest.toDomain()
        employee.setDepartment(Mock(Department))

        when:
        def employeeResponse = createEmployeeController.createEmployee(employeeRequest);

        then: "check calls"
        1 * createEmployeeUseCase.execute(employeeRequest) >> employee

        and: "check the response code and body"
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
