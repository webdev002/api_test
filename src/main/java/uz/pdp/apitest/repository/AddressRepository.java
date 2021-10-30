package uz.pdp.apitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apitest.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
    boolean existsByHomeNumberAndIdNot(Integer homeNumber, Integer id);
}
