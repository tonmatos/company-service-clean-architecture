package br.com.wnt.application.department

import br.com.wnt.application.department.request.DepartmentRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class CreateDepartmentUseCaseTest extends Specification {

    CreateDepartmentUseCaseImpl createDepartmentUseCase

    def departmentRepository = Mock(DepartmentRepository)
    def departmentRequest = Mock(DepartmentRequest)

    def setup() {
        createDepartmentUseCase = new CreateDepartmentUseCaseImpl(departmentRepository)
    }

    def "Test create Department with success"() {
        given:
        def departmentSaved = Mock(Department)

        when:
        Department departmentFromDB = createDepartmentUseCase.execute(departmentRequest)

        then: "check calls"
        1 * departmentRepository.save(departmentRequest.toDomain()) >> departmentSaved

        then: "check if the returned object is the expected"
        departmentFromDB == departmentSaved
    }
}
