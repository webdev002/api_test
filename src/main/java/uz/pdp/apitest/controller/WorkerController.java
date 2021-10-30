package uz.pdp.apitest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apitest.entity.Worker;
import uz.pdp.apitest.payload.ApiResponse;
import uz.pdp.apitest.payload.WorkerDto;
import uz.pdp.apitest.service.WorkerService;

import java.nio.file.WatchService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WorkerController {
    @Autowired
    WorkerService workerService;

    @GetMapping("/api/workers")
    public HttpEntity<List<Worker>> getWorkers(){
        List<Worker> workers = workerService.getWorkers();
        return ResponseEntity.ok(workers);
    }

    @GetMapping("/api/workers/{id}")
    public ResponseEntity<Worker> getWorkerById(@PathVariable Integer id){
        Worker workersById = workerService.getWorkersById(id);
        return ResponseEntity.ok(workersById);
    }

    @PostMapping("/api/workers")
    public HttpEntity<ApiResponse> addWorker(@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.addWorker(workerDto);
        return ResponseEntity.status(apiResponse.isSuccess()? HttpStatus.CREATED:HttpStatus.CONFLICT).body(apiResponse);
    }

    @PutMapping("/api/workers/{id}")
    public ApiResponse editWorkers(@PathVariable Integer id,@RequestBody WorkerDto workerDto){
        ApiResponse apiResponse = workerService.editWorkers(id, workerDto);
        return apiResponse;
    }

    @DeleteMapping("/api/workers/{id}")
    public HttpEntity<ApiResponse> deleteWorker(@PathVariable Integer id){
        ApiResponse apiResponse = workerService.deleteWorker(id);
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
