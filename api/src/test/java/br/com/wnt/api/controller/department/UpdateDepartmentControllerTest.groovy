package br.com.wnt.api.controller.department

import br.com.wnt.application.department.interfaces.UpdateDepartmentUseCase
import br.com.wnt.application.department.request.UpdateDepartmentRequest
import br.com.wnt.domain.department.DepartmentCategory
import groovy.transform.CompileDynamic
import org.springframework.http.HttpStatus
import spock.lang.Specification

@CompileDynamic
class UpdateDepartmentControllerTest extends Specification {

    UpdateDepartmentController updateDepartmentController

    UpdateDepartmentUseCase updateDepartmentUseCase = Mock()
    UpdateDepartmentRequest updateDepartmentRequest


    void setup() {
        updateDepartmentController = new UpdateDepartmentController(updateDepartmentUseCase)

        updateDepartmentRequest = UpdateDepartmentRequest.builder()
                .name("DepartmentNameUpdated")
                .category(DepartmentCategory.HR)
                .build()
    }

    void cleanup() {
    }

    def "Test update department"() {
        given:
        def departmentId = 1
        def department = updateDepartmentRequest.toDomain()

        when:
        def departmentResponse = updateDepartmentController
                .updateDepartment(departmentId, updateDepartmentRequest)

        then: "check calls"
        1 * updateDepartmentUseCase.execute(departmentId, updateDepartmentRequest) >> department

        and: "check the response code"
        departmentResponse.getStatusCode() == HttpStatus.OK

        and: "check if the body contains all the expected information"
        def responseBody = departmentResponse.getBody()

        responseBody.getId() == department.getId()
        responseBody.getName() == department.getName()
        responseBody.getCategory() == department.getCategory()
        responseBody.getEmployees() == department.getEmployees()
        responseBody.getCreated() == department.getCreated()
    }
}
