package uz.pdp.apitest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apitest.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker, Integer> {
    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
