package br.com.wnt.domain.mock

import br.com.wnt.domain.department.Department
import br.com.wnt.domain.department.DepartmentCategory
import groovy.transform.CompileDynamic

import java.time.LocalDateTime

@CompileDynamic
class MockDepartment {

    public static final String DEPARTMENT_NAME = "DEPARTMENT_NAME"

    static Department createDepartment(long id = 1, name = DEPARTMENT_NAME, category = DepartmentCategory.IT) {
        return Department.builder()
                .id(id)
                .name(name)
                .category(category)
                .created(LocalDateTime.now())
                .build()
    }

}
