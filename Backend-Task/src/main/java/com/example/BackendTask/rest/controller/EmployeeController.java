package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.handler.EmployeeHandler;
import com.example.BackendTask.rest.handler.VacationLeaveHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
@RestController
@RequestMapping("/employee")
@AllArgsConstructor
@Tag(name = "employee", description = "Rest Api For Employee")
public class EmployeeController {

    private EmployeeHandler employeeHandler;
    private VacationLeaveHandler vacationLeaveHandler;


    @GetMapping
    @Operation(summary = "get all", description = "this API to get all employee in pages")
    public ResponseEntity<?> getAll(@RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return employeeHandler.getAll( page, size);

    }

    @GetMapping("/{id}")
    @Operation(summary = "get employee by id", description = "this API to get employee by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return employeeHandler.getById(id);
    }

    @PostMapping
    @Operation(summary = "add employee", description = "this API to add new employee")
    public ResponseEntity<?> save(@RequestBody EmployeeDto employeeDto) {
        return employeeHandler.save(employeeDto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update employee", description = "this API to update an existing employee")
    public ResponseEntity<?> update(@RequestBody EmployeeDto employeeDto,
                                    @PathVariable Integer id) {
        return employeeHandler.update(id, employeeDto);
    }




    @DeleteMapping("/{id}")
    @Operation(summary = "delete employee ", description = "this API to delete employee")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return employeeHandler.delete(id);
    }




    @GetMapping("/{id}/pdf-report")
    @Operation(summary = "generate PDF Report for Employee")
    @ResponseBody
    public void generatePDFReport(@RequestParam(value = "employee", required = false) Integer employeeId,
                                       @RequestParam(value = "language", required = false) String language,
                                       HttpServletResponse response) {
        employeeHandler.generatePDFReport(response, employeeId, language);
    }

    @GetMapping("/{id}/word-report")
    @Operation(summary = "generate Word Report for Employee")
    @ResponseBody
    public void generateWordReport(@RequestParam(value = "employee", required = false) Integer employeeId,
                                   @RequestParam(value = "language", required = false) String language,
                                       HttpServletResponse response) {
        employeeHandler.generateWordReport(response, employeeId, language);
    }
    /////////////////////////////////////////Vacation Leave ////////////////////////////


    @GetMapping("/{id}/vacationLeave")
    @Operation(summary = "get all", description = "this API to get all Vacation Leave in pages")
    public ResponseEntity<?> getAllVacationLeaveById(@PathVariable(value = "id") Integer employeeId, @RequestParam(value = "page", defaultValue = "0") Integer page, @RequestParam(value = "size", defaultValue = "10") Integer size) {
        return vacationLeaveHandler.getAllVacationByEmployee(employeeId, page, size);

    }

}