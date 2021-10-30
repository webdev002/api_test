package uz.pdp.apitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apitest.entity.Worker;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.WorkerDto;
import uz.pdp.apitest.repository.AddressRepository;
import uz.pdp.apitest.repository.DepartmentRepository;
import uz.pdp.apitest.repository.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {
    @Autowired
    WorkerRepository workerRepository;
    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Worker> getWorkers(){
        List<Worker> workers = workerRepository.findAll();
        return workers;
    }

    public Worker getWorkersById(Integer id){
        Optional<Worker> worker = workerRepository.findById(id);
        return worker.orElse(null);
    }

    public ApiResponse addWorker(WorkerDto workerDto){
        boolean existsByPhoneNumber = workerRepository.existsByPhoneNumber(workerDto.getPhoneNumber());
        if (existsByPhoneNumber){
            return new ApiResponse("Bunday ishchi mavjud!",false);
        }
        Worker worker = new Worker();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(addressRepository.getById(workerDto.getAddressId()));
        worker.setDepartment(departmentRepository.getById(workerDto.getDepartmentId()));
        workerRepository.save(worker);
        return new ApiResponse("Worker saqlandi!",true);
    }

    public ApiResponse editWorkers(Integer id , WorkerDto workerDto){
        boolean existsByPhoneNumberAndIdNot = workerRepository.existsByPhoneNumberAndIdNot(workerDto.getPhoneNumber(), id);
        if (existsByPhoneNumberAndIdNot){
            return new ApiResponse("Bunday worker mavjud emas!",false);
        }

        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isEmpty()){
            return new ApiResponse("Bunday Worker mavjud!",false);
        }
        Worker worker = optionalWorker.get();
        worker.setName(workerDto.getName());
        worker.setPhoneNumber(workerDto.getPhoneNumber());
        worker.setAddress(addressRepository.getById(workerDto.getAddressId()));
        worker.setDepartment(departmentRepository.getById(workerDto.getDepartmentId()));
        workerRepository.save(worker);
        return new ApiResponse("Worker tahrirlandi!",true);
    }

    public ApiResponse deleteWorker(Integer id){
        workerRepository.deleteById(id);
        return new ApiResponse("Worker ochirildi!",true);
    }
}
