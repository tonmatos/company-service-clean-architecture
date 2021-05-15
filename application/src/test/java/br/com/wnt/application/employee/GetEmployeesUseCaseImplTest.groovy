package br.com.wnt.application.employee

import br.com.wnt.application.employee.GetEmployeesUseCaseImpl
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import br.com.wnt.domain.exception.NotFoundException
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class GetEmployeesUseCaseImplTest extends Specification {
    GetEmployeesUseCaseImpl getEmployeesUseCase

    def employeeRepository = Mock(EmployeeRepository)

    def setup() {
        getEmployeesUseCase = new GetEmployeesUseCaseImpl(employeeRepository)
    }

    def "Test Get Employees with success"() {
        given:
        def employees = [Mock(Employee), Mock(Employee), Mock(Employee)]

        when:
        def employeesFromDB = getEmployeesUseCase.execute()

        then: "check calls"
        1 * employeeRepository.findAll() >> employees

        then: "check if the returned object is the expected"
        employeesFromDB == employees
    }

    def "Test Get Employees and not finding any"() {
        given:
        def employees = []

        when:
        getEmployeesUseCase.execute()

        then: "check calls"
        1 * employeeRepository.findAll() >> employees

        then: "check if the returned exception is the expected"
        def notFoundException = thrown(NotFoundException)
        notFoundException.getResourceName() == Employee.TABLE_NAME
    }
}
