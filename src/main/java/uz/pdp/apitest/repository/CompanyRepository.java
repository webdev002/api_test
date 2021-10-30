package uz.pdp.apitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apitest.entity.Company;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByDirectorNameAndIdNot(String directorName, Integer id);
}
