package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.VacationLeave;
import com.example.BackendTask.rest.dto.VacationLeaveDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.EmployeeMapper;
import com.example.BackendTask.rest.entitymapper.VacationLeaveMapper;
import com.example.BackendTask.rest.entitymapper.VacationTypeMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceAlreadyExistsException;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.service.VacationLeaveService;
import com.example.BackendTask.service.VacationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class VacationLeaveHandler {
    private VacationLeaveMapper vacationLeaveMapper;
    private VacationLeaveService vacationLeaveService;
    private PaginationMapper paginationMapper;


    public ResponseEntity<?> getById(Integer id) {
        VacationLeave vacationLeave = vacationLeaveService.getById(id).orElseThrow(() -> new ResourceNotFoundException(VacationLeave.class.getSimpleName(), id));
        VacationLeaveDto vacationLeaveDto = vacationLeaveMapper.toDto(vacationLeave);
        return ResponseEntity.ok(vacationLeaveDto);
    }

    public ResponseEntity<?> getAllVacationByEmployee(Integer employeeId, Integer page, Integer size) {
        Page<VacationLeave> vacationLeavePage = vacationLeaveService.getAllByEmployee(employeeId, page, size);
        List<VacationLeaveDto> vacationLeaveDtoList = vacationLeaveMapper.toDto(vacationLeavePage.getContent());
        PaginatedResultDto<VacationLeaveDto> paginatedResultDto = new PaginatedResultDto<>();
        paginatedResultDto.setData(vacationLeaveDtoList);
        paginatedResultDto.setPagination(paginationMapper.toPaginationDto(vacationLeavePage));
        return ResponseEntity.ok(paginatedResultDto);
    }
    public ResponseEntity<?> save( VacationLeaveDto vacationLeaveDto) {

        VacationLeave vacationLeave = vacationLeaveMapper.toEntity(vacationLeaveDto);
        vacationLeaveService.save(vacationLeave);
        VacationLeaveDto dto = vacationLeaveMapper.toDto(vacationLeave);
        return ResponseEntity.created(URI.create("/vacation-leave/" + vacationLeave.getId())).body(dto);
    }


    public ResponseEntity<?> delete(Integer id) {
        VacationLeave vacationLeave = vacationLeaveService.getById(id).orElseThrow(() -> new ResourceNotFoundException(VacationLeave.class.getSimpleName(), id));
        try {
            vacationLeaveService.delete(vacationLeave);
        } catch (Exception exception) {
            throw new ResourceRelatedException(VacationLeave.class.getSimpleName(), "id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<?> update(Integer id, VacationLeaveDto dto) {
        VacationLeave vacationLeave = vacationLeaveService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(VacationLeave.class.getSimpleName(), id));

        VacationLeave entity = vacationLeaveMapper.updateEntityFromDto(dto, vacationLeave);
        vacationLeaveService.update(entity);
        return ResponseEntity.ok().build();
    }


}
