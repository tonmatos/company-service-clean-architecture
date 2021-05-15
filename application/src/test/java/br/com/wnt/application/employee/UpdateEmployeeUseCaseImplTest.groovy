package br.com.wnt.application.employee

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase
import br.com.wnt.application.employee.UpdateEmployeeUseCaseImpl
import br.com.wnt.application.employee.interfaces.GetEmployeeByIdUseCase
import br.com.wnt.application.employee.request.UpdateEmployeeRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

@CompileDynamic
class UpdateEmployeeUseCaseImplTest extends Specification {
    UpdateEmployeeUseCaseImpl updateEmployeeUseCase

    def employeeRepository = Mock(EmployeeRepository)
    def getEmployeeByIdUseCase = Mock(GetEmployeeByIdUseCase)
    def getDepartmentByIdUseCase = Mock(GetDepartmentByIdUseCase)
    def updateEmployeeRequest = Mock(UpdateEmployeeRequest)


    void setup() {
        updateEmployeeUseCase = new UpdateEmployeeUseCaseImpl(employeeRepository, getEmployeeByIdUseCase, getDepartmentByIdUseCase)
    }

    def "Test update employee"() {
        given:
        def employeeId = 1
        def employee = Mock(Employee)
        def updatedEmployee = Mock(Employee)

        when:
        Employee employeeFromDB = updateEmployeeUseCase.execute(employeeId, updateEmployeeRequest)

        then: "check calls"
        1 * getEmployeeByIdUseCase.execute(employeeId) >> employee
        1 * getDepartmentByIdUseCase.execute(updateEmployeeRequest.getDepartmentId()) >> Mock(Department)
        1 * employeeRepository.save(_) >> updatedEmployee

        and: "check the response code"
        updatedEmployee == employeeFromDB
    }
}
