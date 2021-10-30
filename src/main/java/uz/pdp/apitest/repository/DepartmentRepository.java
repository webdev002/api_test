package uz.pdp.apitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apitest.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department,Integer> {
    boolean existsByNameAndIdNot(String name, Integer id);
}
