package com.example.BackendTask.rest.handler;

import com.example.BackendTask.entity.Employee;
import com.example.BackendTask.rest.dto.EmployeeDto;
import com.example.BackendTask.rest.dto.common.PaginatedResultDto;
import com.example.BackendTask.rest.entitymapper.EmployeeMapper;
import com.example.BackendTask.rest.entitymapper.common.PaginationMapper;
import com.example.BackendTask.rest.exception.ErrorCodes;
import com.example.BackendTask.rest.exception.ResourceAlreadyExistsException;
import com.example.BackendTask.rest.exception.ResourceNotFoundException;
import com.example.BackendTask.rest.exception.ResourceRelatedException;
import com.example.BackendTask.service.EmployeeService;
import com.example.BackendTask.support.ReportName;
import com.example.BackendTask.utils.JasperReportUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.*;

@Component
@AllArgsConstructor
public class EmployeeHandler {
    private EmployeeService employeeService;
    private JasperReportUtil jasperReportUtil;
    private EmployeeMapper employeeMapper;
    private PaginationMapper paginationMapper;

    public ResponseEntity<?> getById(Integer id) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        return ResponseEntity.ok(employeeMapper.toDto(employee));
    }


    public ResponseEntity<?> getAll(Integer page, Integer size) {
        Page<Employee> employeesPage = employeeService.getAll(page, size);
        List<EmployeeDto> employeesDtoList = employeeMapper.toDto(employeesPage.getContent());
        PaginatedResultDto<EmployeeDto> paginatedResult = new PaginatedResultDto<>();
        paginatedResult.setData(employeesDtoList);
        paginatedResult.setPagination(paginationMapper.toPaginationDto(employeesPage));
        return ResponseEntity.ok(paginatedResult);
    }

    public ResponseEntity<?> save(EmployeeDto dto) {
        Optional<Employee> existedByCode = employeeService.getByEmployeeCode(dto.getEmployeeCode());

        if (existedByCode.isPresent()) {
            throw new ResourceAlreadyExistsException(Employee.class.getSimpleName(),
                    "employeeCode", dto.getEmployeeCode(), ErrorCodes.DUPLICATE_EMPLOYEE_CODE);
        }
        Employee employee = employeeMapper.toEntity(dto);
        employeeService.save(employee);
        EmployeeDto employeeDto = employeeMapper.toDto(employee);
        return ResponseEntity.created(URI.create("/employee/" + employee.getId())).body(employeeDto);
    }

    public ResponseEntity<?> update(Integer id, EmployeeDto dto) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));

        Employee entity = employeeMapper.updateEntityFromDto(dto, employee);
        employeeService.update(entity);
        return ResponseEntity.ok().build();

    }

    public ResponseEntity<?> delete(Integer id) {
        Employee employee = employeeService.getById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Employee.class.getSimpleName(), id));
        try {
            employeeService.delete(employee);
        } catch (Exception exception) {
            throw new ResourceRelatedException(Employee.class.getSimpleName(), "Id", id.toString(), ErrorCodes.RELATED_RESOURCE);
        }
        return ResponseEntity.noContent().build();
    }



    public void generatePDFReport(HttpServletResponse response,Integer emp_id, String language) {
    Map<String, Object> params = new HashMap<>();
    params.put("emp_id", emp_id);
    params.put("language", language);
    String reportName = "AllReport";
    List<String> subReports = Arrays.asList("myReport", "ArabicReport");

    jasperReportUtil.generatePdfReport(response,reportName, params,subReports);


}
    public void generateWordReport(HttpServletResponse response,Integer emp_id, String language) {
        Map<String, Object> params = new HashMap<>();
        params.put("emp_id", emp_id);
        params.put("language", language);
        String reportName = "AllReport";
        List<String> subReports = Arrays.asList("myReport", "ArabicReport");

        jasperReportUtil.generateWordReport(response,reportName, params,subReports);


    }






}
