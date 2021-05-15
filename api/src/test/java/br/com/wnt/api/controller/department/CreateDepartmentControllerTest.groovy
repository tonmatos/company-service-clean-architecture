package br.com.wnt.api.controller.department

import br.com.wnt.application.department.interfaces.CreateDepartmentUseCase
import br.com.wnt.application.department.request.DepartmentRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentCategory
import br.com.wnt.domain.employee.Employee
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

@CompileDynamic
class CreateDepartmentControllerTest extends Specification {

    public static final int DEPARTMENT_ID = 1
    CreateDepartmentController createDepartmentController

    CreateDepartmentUseCase createDepartmentUseCase = Mock()
    DepartmentRequest departmentRequest


    void setup() {
        createDepartmentController = new CreateDepartmentController(createDepartmentUseCase)
        departmentRequest = DepartmentRequest.builder()
                .name("DepartmentName")
                .category(DepartmentCategory.FINANCE)
                .build()
    }

    void cleanup() {
    }

    def "Test create department"() {
        given:
        Department department = departmentRequest.toDomain()
        department.setId(DEPARTMENT_ID)
        department.setEmployees([Mock(Employee), Mock(Employee)])

        when:
        def departmentResponse = createDepartmentController.createDepartment(departmentRequest);

        then: "check calls"
        1 * createDepartmentUseCase.execute(departmentRequest) >> department

        and: "check the response code and body"
        departmentResponse.getStatusCode() == HttpStatus.CREATED

        and: "check if the body contains all the expected information"
        def responseBody = departmentResponse.getBody()

        responseBody.getId() == department.getId()
        responseBody.getName() == department.getName()
        responseBody.getCategory() == department.getCategory()
        responseBody.getEmployees() == department.getEmployees()
        responseBody.getCreated() == department.getCreated()


    }
}
