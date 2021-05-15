package br.com.wnt.application.department

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase
import br.com.wnt.application.department.request.UpdateDepartmentRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class UpdateDepartmentUseCaseImplTest extends Specification {
    UpdateDepartmentUseCaseImpl updateDepartmentUseCase

    def departmentRepository = Mock(DepartmentRepository)
    def getDepartmentByIdUseCase = Mock(GetDepartmentByIdUseCase)
    def updateDepartmentRequest = Mock(UpdateDepartmentRequest)


    void setup() {
        updateDepartmentUseCase = new UpdateDepartmentUseCaseImpl(departmentRepository, getDepartmentByIdUseCase)
    }

    def "Test update department"() {
        given:
        def departmentId = 1
        def department = Mock(Department)
        def updatedDepartment = Mock(Department)

        when:
        Department departmentFromDB = updateDepartmentUseCase.execute(departmentId, updateDepartmentRequest)

        then: "check calls"
        1 * getDepartmentByIdUseCase.execute(departmentId) >> department
        1 * departmentRepository.save(department) >> updatedDepartment

        and: "check the response code"
        updatedDepartment == departmentFromDB
    }
}
