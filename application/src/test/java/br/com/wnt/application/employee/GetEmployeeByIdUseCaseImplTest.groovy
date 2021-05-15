package br.com.wnt.application.employee

import br.com.wnt.application.employee.GetEmployeeByIdUseCaseImpl
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import br.com.wnt.domain.exception.NotFoundException
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class GetEmployeeByIdUseCaseImplTest extends Specification {
    GetEmployeeByIdUseCaseImpl getEmployeeByIdUseCase

    def employeeRepository = Mock(EmployeeRepository)

    def setup() {
        getEmployeeByIdUseCase = new GetEmployeeByIdUseCaseImpl(employeeRepository)
    }

    def "Test Get Employee By Id with success"() {
        given:
        def employeeId = Math.random() as long
        def employee = Mock(Employee)

        when:
        def employeeFromDB = getEmployeeByIdUseCase.execute(employeeId)

        then: "check calls"
        1 * employeeRepository.findById(employeeId) >> Optional.of(employee)

        then: "check if the returned object is the expected"
        employeeFromDB == employee
    }

    def "Test Get Employee By Id and not finding it"() {
        given:
        def employeeId = Math.random() as long

        when:
        getEmployeeByIdUseCase.execute(employeeId)

        then: "check calls"
        1 * employeeRepository.findById(employeeId) >> Optional.empty()

        then: "check if the returned exception is the expected"
        def notFoundException = thrown(NotFoundException)
        notFoundException.getResourceName() == Employee.TABLE_NAME
        notFoundException.getId() == String.valueOf(employeeId)
    }
}
