package uz.pdp.apitest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apitest.entity.Company;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.CompanyDto;
import uz.pdp.apitest.service.CompanyService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping("/company")
    public ResponseEntity<List<Company>> getCompany(){
        List<Company> company = companyService.getCompany();
        return ResponseEntity.ok(company);
    }

    @GetMapping("/company/{id}")
    public HttpEntity<Company> getCompanyById(@PathVariable Integer id){
        Company companyById = companyService.getCompanyById(id);
        return ResponseEntity.ok(companyById);
    }

    @PostMapping("/company")
    public HttpEntity<ApiResponse> addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.addCompany(companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/company/{id}")
    public HttpEntity<ApiResponse> editCompany(@PathVariable Integer id, @RequestBody CompanyDto companyDto){
        ApiResponse apiResponse = companyService.editCompany(id, companyDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/company/{id}")
    public ResponseEntity<ApiResponse> deleteCompany(@PathVariable Integer id){
        ApiResponse apiResponse = companyService.deleteCompany(id);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.OK:HttpStatus.CONFLICT).body(apiResponse);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
