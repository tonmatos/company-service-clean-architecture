package br.com.wnt.application.department

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class DeleteDepartmentUseCaseImplTest extends Specification {
    DeleteDepartmentUseCaseImpl deleteDepartmentUseCase

    def departmentRepository = Mock(DepartmentRepository)
    def getDepartmentByIdUseCase = Mock(GetDepartmentByIdUseCase)

    def setup() {
        deleteDepartmentUseCase = new DeleteDepartmentUseCaseImpl(departmentRepository, getDepartmentByIdUseCase)
    }

    def "Test delete Department"() {
        given:
        def departmentId = Math.random() as long
        def department = Mock(Department)

        when:
        deleteDepartmentUseCase.execute(departmentId)

        then: "check calls"
        1 * getDepartmentByIdUseCase.execute(departmentId) >> department
        1 * departmentRepository.delete(department)
    }
}
