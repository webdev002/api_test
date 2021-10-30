package uz.pdp.apitest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apitest.entity.Address;
import uz.pdp.apitest.entity.Company;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.CompanyDto;
import uz.pdp.apitest.repository.AddressRepository;
import uz.pdp.apitest.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    @Autowired
    AddressRepository addressRepository;


    public List<Company> getCompany(){
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    public Company getCompanyById(Integer id){
        Optional<Company> byId = companyRepository.findById(id);
        return byId.orElse(null);
    }

    public ApiResponse addCompany(CompanyDto companyDto){
        Company company = new Company();
        company.setCorpName(companyDto.getCorpName());
        company.setDirectorName(companyDto.getDirectorName());
        company.setAddress(addressRepository.getById(companyDto.getAddressId()));
        companyRepository.save(company);
        return new ApiResponse("Company saqlandi!",true);

    }


    public ApiResponse editCompany(Integer id , CompanyDto companyDto){
        boolean existsByDirectorNameAndIdNot = companyRepository.existsByDirectorNameAndIdNot(companyDto.getDirectorName(), id);
        if (existsByDirectorNameAndIdNot){
            return new  ApiResponse("Bunday idlik companiya mavjud!",false);
        }
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isEmpty()){
            return new ApiResponse("Bunday idlik company mavjud emas!",false);
        }
        Company company = optionalCompany.get();
        company.setDirectorName(company.getDirectorName());
        company.setCorpName(company.getCorpName());
        company.setAddress(addressRepository.getById(companyDto.getAddressId()));
        companyRepository.save(company);
        return new ApiResponse("Company Tahrirlandi",true);

    }


    public ApiResponse deleteCompany(Integer id){
        companyRepository.deleteById(id);
        return new  ApiResponse("Company ochirildi",true);

    }
}
