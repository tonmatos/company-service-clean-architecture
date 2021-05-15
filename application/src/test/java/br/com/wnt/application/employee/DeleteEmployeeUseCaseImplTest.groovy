package br.com.wnt.application.employee

import br.com.wnt.application.employee.DeleteEmployeeUseCaseImpl
import br.com.wnt.application.employee.interfaces.GetEmployeeByIdUseCase
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class DeleteEmployeeUseCaseImplTest extends Specification {
    DeleteEmployeeUseCaseImpl deleteEmployeeUseCase

    def employeeRepository = Mock(EmployeeRepository)
    def getEmployeeByIdUseCase = Mock(GetEmployeeByIdUseCase)

    def setup() {
        deleteEmployeeUseCase = new DeleteEmployeeUseCaseImpl(employeeRepository, getEmployeeByIdUseCase)
    }

    def "Test delete Employee"() {
        given:
        def employeeId = Math.random() as long
        def employee = Mock(Employee)

        when:
        deleteEmployeeUseCase.execute(employeeId)

        then: "check calls"
        1 * getEmployeeByIdUseCase.execute(employeeId) >> employee
        1 * employeeRepository.delete(employee)
    }
}
