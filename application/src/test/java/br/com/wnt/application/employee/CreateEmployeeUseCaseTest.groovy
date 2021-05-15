package br.com.wnt.application.employee

import br.com.wnt.application.department.interfaces.GetDepartmentByIdUseCase
import br.com.wnt.application.employee.request.EmployeeRequest
import br.com.wnt.domain.department.Department
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import groovy.transform.CompileDynamic
import spock.lang.Specification

import java.time.LocalDate

import static java.util.Objects.nonNull

@CompileDynamic
class CreateEmployeeUseCaseTest extends Specification {

    public static final String EMPLOYEE_NAME = "Joao"
    public static final int EMPLOYEE_AGE = 20
    public static final int DEPARTMENT_ID = 6
    CreateEmployeeUseCaseImpl createEmployeeUseCase

    def employeeRepository = Mock(EmployeeRepository)
    def getDepartmentByIdUseCase = Mock(GetDepartmentByIdUseCase)

    EmployeeRequest employeeRequest

    def setup() {
        createEmployeeUseCase = new CreateEmployeeUseCaseImpl(employeeRepository, getDepartmentByIdUseCase)

        employeeRequest = new EmployeeRequest()
        employeeRequest.setName(EMPLOYEE_NAME)
        employeeRequest.setAge(EMPLOYEE_AGE)
        employeeRequest.setDepartmentId(DEPARTMENT_ID)
        employeeRequest.setJoinedDate(LocalDate.now().minusYears(2))
    }


    def "Test create Employee with success"() {
        given:
        def departmentFromDB = Mock(Department)
        def employeeSaved = employeeRequest.toDomain()
        employeeSaved.setDepartment(departmentFromDB)

        when:
        Employee employeeResult = createEmployeeUseCase.execute(employeeRequest)

        then: "check calls"
        1 * getDepartmentByIdUseCase.execute(employeeRequest.getDepartmentId()) >> departmentFromDB
        1 * employeeRepository.save(_) >> employeeSaved

        then: "check if the returned object is the expected"
        nonNull(employeeResult.getId())
        employeeResult.getCreated()
        employeeResult.getName() == employeeSaved.getName()
        employeeResult.getAge() == employeeSaved.getAge()
        employeeResult.getJoinedDate() == employeeSaved.getJoinedDate()
        employeeResult.getDepartment() == employeeSaved.getDepartment()
    }
}
