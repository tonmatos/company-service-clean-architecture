package br.com.wnt.application.department


import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentRepository
import br.com.wnt.domain.exception.NotFoundException
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class GetDepartmentByIdUseCaseImplTest extends Specification {
    GetDepartmentByIdUseCaseImpl getDepartmentByIdUseCase

    def departmentRepository = Mock(DepartmentRepository)

    def setup() {
        getDepartmentByIdUseCase = new GetDepartmentByIdUseCaseImpl(departmentRepository)
    }

    def "Test Get Department By Id with success"() {
        given:
        def departmentId = Math.random() as long
        def department = Mock(Department)

        when:
        def departmentFromDB = getDepartmentByIdUseCase.execute(departmentId)

        then: "check calls"
        1 * departmentRepository.findById(departmentId) >> Optional.of(department)

        then: "check if the returned object is the expected"
        departmentFromDB == department
    }

    def "Test Get Department By Id and not finding it"() {
        given:
        def departmentId = Math.random() as long

        when:
        getDepartmentByIdUseCase.execute(departmentId)

        then: "check calls"
        1 * departmentRepository.findById(departmentId) >> Optional.empty()

        then: "check if the returned exception is the expected"
        def notFoundException = thrown(NotFoundException)
        notFoundException.getResourceName() == Department.TABLE_NAME
        notFoundException.getId() == String.valueOf(departmentId)
    }
}
