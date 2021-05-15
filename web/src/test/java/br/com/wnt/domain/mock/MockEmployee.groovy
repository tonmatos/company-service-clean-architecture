package br.com.wnt.domain.mock


import br.com.wnt.domain.employee.Employee
import groovy.transform.CompileDynamic

import java.time.LocalDate
import java.time.LocalDateTime

@CompileDynamic
class MockEmployee {

    public static final String EMPLOYEE_NAME = "EMPLOYEE_NAME"

    static Employee createEmployee(long id = 1, name = EMPLOYEE_NAME) {
        return Employee.builder()
                .id(id)
                .name(name)
                .joinedDate(LocalDate.now())
                .age(20)
                .created(LocalDateTime.now())
                .build()
    }

}
