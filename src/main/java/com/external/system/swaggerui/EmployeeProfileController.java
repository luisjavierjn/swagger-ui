package com.external.system.swaggerui;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApiOperation(value = "profile/v1/employee", tags = "Employee Profile Controller")
@RestController
@RequestMapping("profile/v1/employee")
public class EmployeeProfileController {

    List<Employee> employees = Arrays.asList(
            new Employee(12345L,"John","Electrical",1234567890L),
            new Employee(22345L,"Sam","Computer",1234567890L),
            new Employee(32345L,"Jack","Electronics",1234567890L),
            new Employee(42345L,"Mark","Mechanical",1234567890L)
    );

    @ApiOperation(value = "fetch All Employees", response = Iterable.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "SUCCESS", response = Employee.class, responseContainer = "List"),
            @ApiResponse(code = 401, message = "UNAUTHORIZED", response = ErrorResponse.class),
            @ApiResponse(code = 403, message = "FORBIDDEN", response = ErrorResponse.class),
            @ApiResponse(code = 404, message = "NOT FOUND")
    })
    @GetMapping(value = "/fetch/all")
    public List<Employee> fetchAllEmployees() { return employees; }

    @ApiOperation(value = "Fetch Employee by Name", response = Employee.class)
    @GetMapping(value = "/fetch/n/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee fetchEmployeeByName(@ApiParam(value = "Employee Name") @PathVariable(value = "name") String name) {
        return employees.stream().filter(x -> x.getName().equalsIgnoreCase(name)).findFirst().get();
    }

    @ApiOperation(value = "Get Employee by Department", response = Employee.class)
    @GetMapping(value = "/fetch/d/{department}")
    public List<Employee> fetchEmployeeByDepartment(@ApiParam(value = "Department Name", required = true) @PathVariable(value = "department") String department) {
        return employees.stream().filter(x -> x.getDepartment().equalsIgnoreCase(department)).collect(Collectors.toList());
    }

    @ApiOperation(value = "Insert Employee Record", response = Employee.class)
    @PostMapping
    public Employee insertEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee) {
        return employee;
    }

    @ApiOperation(value = "Update Employee Details", response = Employee.class)
    @PutMapping
    public Employee updateEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee ) {
        return employee;
    }

    @ApiOperation(value = "Delete an Employee", response = Employee.class)
    @DeleteMapping
    public Employee deleteEmployee(@ApiParam(value = "Employee") @RequestBody Employee employee) {
        return employee;
    }

    @ApiOperation(value = "Partial Update a specific Student in the System", response = Employee.class)
    @PatchMapping
    public Object patchEmployee(@ApiParam(value = "Employee") @RequestBody Map<String, Object> partialUpdate) {
        return partialUpdate;
    }
}