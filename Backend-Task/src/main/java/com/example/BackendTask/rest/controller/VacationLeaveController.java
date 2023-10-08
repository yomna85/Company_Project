package com.example.BackendTask.rest.controller;

import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.handler.VacationLeaveHandler;
import com.example.BackendTask.rest.validation.InsertValidation;
import com.example.BackendTask.rest.validation.UpdateValidation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vacation_leave")
@AllArgsConstructor
@Tag(name = "vacation_leave", description = "Rest Api For Vacation Leave")
public class VacationLeaveController {
    private VacationLeaveHandler vacationLeaveHandler;

    @PostMapping
    @Operation(summary = "add vacation", description = "this API to add new vacation")
    public ResponseEntity<?> save( @RequestBody VacationLeaveDto vacationLeaveDto) {
        return vacationLeaveHandler.save(vacationLeaveDto);
    }



    @GetMapping("/{id}")
    @Operation(summary = "get Vacation Leave by id", description = "this API to get Vacation Leave by id")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return vacationLeaveHandler.getById(id);
    }

    @PutMapping("/{id}/vacation_leave")
    @Operation(summary = "update vacation Leave", description = "this API to update an existing vacation Leave")
    public ResponseEntity<?> update( @RequestBody VacationLeaveDto vacationLeaveDto,
                                    @PathVariable Integer id) {
        return vacationLeaveHandler.update(id, vacationLeaveDto);
    }



    @DeleteMapping("/{id}")
    @Operation(summary = "delete vacation Leave ", description = "this API to delete vacation Leave")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        return vacationLeaveHandler.delete(id);
    }
}
