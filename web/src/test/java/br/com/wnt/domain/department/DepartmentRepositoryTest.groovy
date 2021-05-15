package br.com.wnt.domain.department

import br.com.wnt.domain.common.BaseRepositoryTest
import groovy.transform.CompileDynamic
import org.springframework.beans.factory.annotation.Autowired

import javax.transaction.Transactional

import static br.com.wnt.domain.mock.MockDepartment.createDepartment
import static org.testcontainers.shaded.org.apache.commons.lang.ArrayUtils.isNotEmpty

@CompileDynamic
@Transactional
class DepartmentRepositoryTest extends BaseRepositoryTest {

    @Autowired
    DepartmentRepository departmentRepository

    Department department

    void setup() {
        def newDepartment = createDepartment()
        department = departmentRepository.save(newDepartment)
    }

    void cleanup() {
        departmentRepository.deleteAll()
    }

    def "Test save Department"() {
        given:
        def newDepartment = createDepartment()

        when:
        Department savedDepartment = departmentRepository.save(newDepartment)

        then:
        savedDepartment
        savedDepartment == newDepartment
    }

    def "Test update Department"() {
        given:
        def departmentNewName = "departmentNewName"
        department.setName(departmentNewName)

        when:
        Department updatedDepartment = departmentRepository.save(department)

        then:
        updatedDepartment
        updatedDepartment == department
    }

    def "Test find Department by Id"() {
        when:
        Optional<Department> foundDepartment = departmentRepository.findById(department.getId())

        then:
        foundDepartment.isPresent()
        foundDepartment.get() == department
    }

    def "Test find all Departments"() {
        given:
        def existingDepartment = departmentRepository.save(createDepartment())

        when:
        List<Department> departments = departmentRepository.findAll()

        then:
        isNotEmpty(departments)
        departments.contains(department)
        departments.contains(existingDepartment)
    }

    def "Test delete Department"() {
        when:
        departmentRepository.deleteById(department.getId())

        then:
        departmentRepository.findById(department.getId()).isEmpty()
    }
}
