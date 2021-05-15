package br.com.wnt.domain.employee;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

  @Override
  List<Employee> findAll();

  List<Employee> findAllByIdIn(List<Long> ids);
}
