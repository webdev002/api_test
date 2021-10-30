package uz.pdp.apitest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apitest.entity.Department;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.DepartmentDto;
import uz.pdp.apitest.service.DepartmentService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

    @GetMapping("/api/department")
    public HttpEntity<List<Department>> getDepartment(){
        List<Department> department = departmentService.getDepartment();
        return ResponseEntity.ok(department);
    }

    @GetMapping("/api/department/{id}")
    public HttpEntity<Department> getDepartmentById(@PathVariable Integer id){
        Department departmentById = departmentService.getDepartmentById(id);
        return ResponseEntity.ok(departmentById);
    }

    @PostMapping("/api/department")
    public ResponseEntity<ApiResponse> addDepartment(@RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.addDepartment(departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/department/{id}")
    public ResponseEntity<ApiResponse> editDepartment(@PathVariable Integer id , @RequestBody DepartmentDto departmentDto){
        ApiResponse apiResponse = departmentService.editDepartment(id, departmentDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @DeleteMapping("/api/department/{id}")
    public HttpEntity<ApiResponse> deleteDepartment(@PathVariable Integer id){
        ApiResponse apiResponse = departmentService.deleteDepartment(id);
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
