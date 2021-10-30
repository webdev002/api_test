package uz.pdp.apitest.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apitest.entity.Address;
import uz.pdp.apitest.payload.AddressDto;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.service.AddressService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/address")
    public ResponseEntity<List<Address>>  getAddress(){
        List<Address> address = addressService.getAddress();
        return ResponseEntity.ok(address);
    }
    @GetMapping("/address/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Integer id){
        Address addressById = addressService.getAddressById(id);
        return ResponseEntity.ok(addressById);
    }

    @PostMapping("/address")
    public HttpEntity<ApiResponse>  addAddress(@RequestBody AddressDto addressDto){
        ApiResponse apiResponse = addressService.addAddress(addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/address/{id}")
    public HttpEntity<ApiResponse> editAddress(@PathVariable Integer id, @RequestBody AddressDto addressDto ){
        ApiResponse apiResponse = addressService.editAddress(id, addressDto);
        return ResponseEntity.status(apiResponse.isSuccess()?HttpStatus.ACCEPTED:HttpStatus.CONFLICT).body(apiResponse);

    }

    @DeleteMapping("/address/{id}")
    public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Integer id){
        ApiResponse apiResponse = addressService.deleteAddress(id);
        return ResponseEntity.status(apiResponse.isSuccess()?2002:409).body(apiResponse);
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
