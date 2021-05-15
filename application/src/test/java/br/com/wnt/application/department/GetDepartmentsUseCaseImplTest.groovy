package br.com.wnt.application.department

import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentRepository
import br.com.wnt.domain.exception.NotFoundException
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class GetDepartmentsUseCaseImplTest extends Specification {
    GetDepartmentsUseCaseImpl getDepartmentsUseCase

    def departmentRepository = Mock(DepartmentRepository)

    def setup() {
        getDepartmentsUseCase = new GetDepartmentsUseCaseImpl(departmentRepository)
    }

    def "Test Get Departments with success"() {
        given:
        def departments = [Mock(Department), Mock(Department), Mock(Department)]

        when:
        def departmentsFromDB = getDepartmentsUseCase.execute()

        then: "check calls"
        1 * departmentRepository.findAll() >> departments

        then: "check if the returned object is the expected"
        departmentsFromDB == departments
    }

    def "Test Get Departments and not finding any"() {
        given:
        def departments = []

        when:
        getDepartmentsUseCase.execute()

        then: "check calls"
        1 * departmentRepository.findAll() >> departments

        then: "check if the returned exception is the expected"
        def notFoundException = thrown(NotFoundException)
        notFoundException.getResourceName() == Department.TABLE_NAME
    }
}
