package br.com.wnt.domain.department;

import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface DepartmentRepository extends CrudRepository<Department, Long> {

  @Override
  List<Department> findAll();
}
