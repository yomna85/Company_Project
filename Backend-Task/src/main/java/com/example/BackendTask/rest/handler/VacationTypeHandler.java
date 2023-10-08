package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.VacationType;
import com.example.BackendTask.rest.dto.VacationTypeDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.VacationTypeMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.VacationTypeService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.util.List;

@Component
@AllArgsConstructor
public class VacationTypeHandler {
    private VacationTypeService vacationTypeService;
    private VacationTypeMapper vacationTypeMapper;
    private PaginationMapper paginationMapper;


    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<VacationType> vacationTypePage = vacationTypeService.getAll(page, size);
        List<VacationTypeDto> vacationTypeDtoList = vacationTypeMapper.toDto(vacationTypePage.getContent());
        PaginatedResultDto<VacationTypeDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(vacationTypeDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(vacationTypePage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> getById(Integer id) {
        VacationType vacationType = vacationTypeService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(VacationType.class.getSimpleName(), id));
        VacationTypeDto vacationTypeDto = vacationTypeMapper.toDto(vacationType);
        return ResponseEntity.ok(vacationTypeDto);
    }

    public ResponseEntity<?> save(VacationTypeDto dto) {
        VacationType vacationType = vacationTypeMapper.toEntity(dto);
        vacationTypeService.save(vacationType);
        VacationTypeDto vacationTypeDto = vacationTypeMapper.toDto(vacationType);
        return ResponseEntity.created(URI.create("/vacation-type/" + vacationType.getId())).body(vacationTypeDto);
    }

    public ResponseEntity<?> update(VacationTypeDto vacationTypeDto, Integer id) {
        VacationType vacationType = vacationTypeService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(VacationType.class.getSimpleName(), id));
        vacationTypeMapper.updateEntityFromDto(vacationTypeDto, vacationType);
        vacationTypeService.update(vacationType);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<?> delete(Integer id) {
        VacationType vacationType = vacationTypeService.getById(id).
                orElseThrow(() -> new ResourceNotFoundException(VacationType.class.getSimpleName(), id));
        try {
            vacationTypeService.delete(vacationType);
        } catch (Exception exception) {
            throw new ResourceRelatedException(VacationType.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }

}
