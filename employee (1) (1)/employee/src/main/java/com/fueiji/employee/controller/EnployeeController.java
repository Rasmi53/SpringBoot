package com.fueiji.employee.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fueiji.employee.bean.EmployeeAddressBean;
import com.fueiji.employee.bean.EmployeeBean;
import com.fueiji.employee.entity.EmployeeEntity;
import com.fueiji.employee.exception.ResourceNotFoundException;
import com.fueiji.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value = "/employee")
public class EnployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> save(@Valid @RequestBody EmployeeEntity employee) {
		employeeService.save(employee);
//		log.info("Saved Employee {}", employee);
		return ResponseEntity.ok().body("Employee details are saved");
	}

	@PutMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> update(@Valid EmployeeEntity employee) {
		employeeService.update(employee);
//		log.info("Updated Employee {}", employee);
		return ResponseEntity.ok().body("Employee details are updated");
	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> delete(@PathVariable(value = "id") long id) {
//		log.info("Employee {} record is deleted ", id);
		employeeService.delete(id);
		return ResponseEntity.ok().body("Employee record deleted");
	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeEntity> get(@PathVariable(value = "id") long id,
			@RequestParam(value = "filter", defaultValue = "xyz", required = false) String filter) {
//		log.info("Filter value {}", filter);
		return ResponseEntity.ok().body(employeeService.get(id, filter));
	}

	@GetMapping(value = "/project/fullname/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeBean> projectFullName(@PathVariable(value = "id") long id) {
//		log.info("Project full name with id {}", id);
		return ResponseEntity.ok().body(employeeService.projectFullName(id));
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeEntity>> getAll() {
//		log.info("Fetching all the employees");
		List<EmployeeEntity> employees = employeeService.getAll();
//		log.info("Fetched employees {}", employees);
		return ResponseEntity.ok().body(employees);
	}
	
	@GetMapping(value = "/emp/address/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<EmployeeAddressBean>> projectEmployeeAndAddress() {
//		log.info("Fetching all the employees");
		List<EmployeeAddressBean> employees = employeeService.projectEmployeeAndAddress();
//		log.info("Fetched employees {}", employees);
		return ResponseEntity.ok().body(employees);
	}

	@GetMapping(value = "/searchbyname/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeEntity> searchByName(@PathVariable(value = "name") String fullName) {
//		log.info("Search value {}", fullName);
		return ResponseEntity.ok().body(employeeService.searchByName(fullName));
	}

	@GetMapping(value = "/searchbynameandid/{name}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeEntity> searchByNameAndId(@PathVariable(value = "name") String fullName,
			@PathVariable(value = "id") Long id) {
//		log.info("Search value {}", fullName);
		return ResponseEntity.ok().body(employeeService.searchByName(fullName, id));
	}

	@GetMapping(value = "/rest/test/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeEntity> rest(@PathVariable(value = "id") long id,
			@RequestParam(value = "filter", defaultValue = "default", required = false) String filter) {

		// URl information
		String url = "http://localhost:8082/api/employee/" + id;
//		log.info("URL - " + url);

		// Headers details
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		// Input entity
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);

		ResponseEntity<EmployeeEntity> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				EmployeeEntity.class);
//		log.info("Status {}", responseEntity.getStatusCode());

		if (responseEntity.getStatusCode().is2xxSuccessful()) {
//			log.info("Executed successfully and output is {}", responseEntity.getBody());
			return ResponseEntity.ok().body(responseEntity.getBody());
		} else {
//			log.error("Not successful " + responseEntity.getStatusCode());
			throw new ResourceNotFoundException("Something went wrong");
		}

	}

}
