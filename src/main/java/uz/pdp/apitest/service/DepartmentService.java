package uz.pdp.apitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apitest.entity.Department;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.DepartmentDto;
import uz.pdp.apitest.repository.CompanyRepository;
import uz.pdp.apitest.repository.DepartmentRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    @Autowired
    DepartmentRepository departmentRepository;
    @Autowired
    CompanyRepository companyRepository;

    public List<Department> getDepartment(){
        List<Department> departmentList = departmentRepository.findAll();
        return departmentList;
    }

    public Department getDepartmentById(Integer id){
        Optional<Department> department = departmentRepository.findById(id);
        return department.orElse(null);
    }

    public ApiResponse addDepartment(DepartmentDto departmentDto){
        Department department = new Department();
        department.setName(departmentDto.getName());
        department.setCompany(companyRepository.getById(departmentDto.getCompanyId()));
        departmentRepository.save(department);
        return new ApiResponse("Department Saqlandi!",true);
    }

    public ApiResponse editDepartment(Integer id , DepartmentDto departmentDto){
        boolean existsByNameAndIdNot = departmentRepository.existsByNameAndIdNot(departmentDto.getName(), id);
        if (existsByNameAndIdNot){
            return new ApiResponse("Bunday department mavjud emas!",false);
        }
        Optional<Department> departmentOptional = departmentRepository.findById(id);
        if (departmentOptional.isEmpty()){
            return new ApiResponse("Bunday department mavjud!",false);
        }
        Department department = departmentOptional.get();
        department.setName(departmentDto.getName());
        department.setCompany(companyRepository.getById(departmentDto.getCompanyId()));
        departmentRepository.save(department);
        return new ApiResponse("Department tahrirlandi",true);
    }


    public ApiResponse deleteDepartment(Integer id){
        departmentRepository.deleteById(id);
        return new ApiResponse("Department ochirildi!",true);
    }
}
