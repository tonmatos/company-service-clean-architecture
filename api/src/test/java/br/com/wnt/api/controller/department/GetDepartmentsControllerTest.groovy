package br.com.wnt.api.controller.department

import br.com.wnt.application.department.interfaces.GetDepartmentsUseCase
import br.com.wnt.application.department.response.DepartmentResponse
import br.com.wnt.domain.department.Department
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

import static org.hamcrest.Matchers.containsInAnyOrder
import static spock.util.matcher.HamcrestSupport.that

@CompileDynamic
class GetDepartmentsControllerTest extends Specification {

    GetDepartmentsController getDepartmentController

    GetDepartmentsUseCase getDepartmentUseCase = Mock()


    void setup() {
        getDepartmentController = new GetDepartmentsController(getDepartmentUseCase)
    }

    void cleanup() {
    }

    def "Test getAll departments"() {
        given:
        def department1 = Mock(Department)
        def department2 = Mock(Department)
        def departments = [department1, department2]

        when:
        def departmentsResponse = getDepartmentController.getAllDepartments()

        then: "check calls"
        1 * getDepartmentUseCase.execute() >> departments

        and: "check the response code"
        departmentsResponse.getStatusCode() == HttpStatus.OK

        that departmentsResponse.getBody(), containsInAnyOrder(DepartmentResponse.from(department1), DepartmentResponse.from(department2))
    }
}
