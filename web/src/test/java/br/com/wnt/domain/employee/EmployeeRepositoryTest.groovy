package br.com.wnt.domain.employee

import br.com.wnt.domain.common.BaseRepositoryTest
import br.com.wnt.domain.employee.Employee
import br.com.wnt.domain.employee.EmployeeRepository
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Autowired

import javax.transaction.Transactional

import static br.com.wnt.domain.mock.MockEmployee.createEmployee
import static org.testcontainers.shaded.org.apache.commons.lang.ArrayUtils.isNotEmpty

@CompileDynamic
@Transactional
class EmployeeRepositoryTest extends BaseRepositoryTest {

    @Autowired
    EmployeeRepository employeeRepository

    Employee employee

    void setup() {
        def newEmployee = createEmployee()
        employee = employeeRepository.save(newEmployee)
    }

    void cleanup() {
        employeeRepository.deleteAll()
    }

    def "Test save Employee"() {
        given:
        def newEmployee = createEmployee()

        when:
        Employee savedEmployee = employeeRepository.save(newEmployee)

        then:
        savedEmployee
        savedEmployee == newEmployee
    }

    def "Test update Employee"() {
        given:
        def employeeNewName = "employeeNewName"
        employee.setName(employeeNewName)

        when:
        Employee updatedEmployee = employeeRepository.save(employee)

        then:
        updatedEmployee
        updatedEmployee == employee
    }

    def "Test find Employee by Id"() {
        when:
        Optional<Employee> foundEmployee = employeeRepository.findById(employee.getId())

        then:
        foundEmployee.isPresent()
        foundEmployee.get() == employee
    }

    def "Test find Employee by Id In"() {
        given:
        def existingEmployee1 = employeeRepository.save(createEmployee())
        def existingEmployee2 = employeeRepository.save(createEmployee())
        def existingEmployee3 = employeeRepository.save(createEmployee())

        def employeeIds = [existingEmployee1.getId(), existingEmployee2.getId()]

        when:
        List<Employee> employees = employeeRepository.findAllByIdIn(employeeIds)

        then:
        isNotEmpty(employees)
        employees.size() == employeeIds.size()
        employees.containsAll([existingEmployee1, existingEmployee2])
    }

    def "Test find all Employees"() {
        given:
        def existingEmployee = employeeRepository.save(createEmployee())

        when:
        List<Employee> employees = employeeRepository.findAll()

        then:
        isNotEmpty(employees)
        employees.contains(employee)
        employees.contains(existingEmployee)
    }

    def "Test delete Employee"() {
        when:
        employeeRepository.deleteById(employee.getId())

        then:
        employeeRepository.findById(employee.getId()).isEmpty()
    }
}
