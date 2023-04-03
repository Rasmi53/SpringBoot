package com.fueiji.employee.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.fueiji.employee.bean.EmployeeAddressBean;
import com.fueiji.employee.bean.EmployeeBean;
import com.fueiji.employee.entity.EmployeeEntity;
import com.fueiji.employee.jpa.repository.EmployeeRepository;
import com.fueiji.employee.service.EmployeeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void save(EmployeeEntity employee) {
		employeeRepository.save(employee);
	}

	@Override
	public EmployeeEntity get(long id) {
		// EmployeeEntity employee =
		// EmployeeEntity.builder().id(101l).fullName("Maha").address("Bedngaluru").build();
		EmployeeEntity employee = employeeRepository.findById(id).get();
//		log.info("Emploee deatis {}", employee);
		return employee;
	}

	@Override
	public List<EmployeeEntity> getAll() {
//		List<EmployeeEntity> employees = new ArrayList<>();
//		employees.add(EmployeeEntity.builder().id(101l).fullName("Maha").address("Bedngaluru").build());
//		employees.add(EmployeeEntity.builder().id(102l).fullName("Shiv").address("Bedngaluru").build());
//		employees.add(EmployeeEntity.builder().id(103l).fullName("Raj").address("Bedngaluru").build());
//		employees.add(EmployeeEntity.builder().id(104l).fullName("Malhothra").address("Bedngaluru").build());
//		employees.add(EmployeeEntity.builder().id(105l).fullName("Rajiv").address("Bedngaluru").build());
//		log.info("Employees count {}", employeeRepository.count());
		List<EmployeeEntity> employees = employeeRepository.findAll();
		return employees;
	}

	@Override
	public void delete(long id) {
		employeeRepository.deleteById(id);
	}

	@Override
	public void update(EmployeeEntity employee) {
		employeeRepository.save(employee);
	}

	@Override
	public EmployeeEntity get(long id, String filter) {
		if (ObjectUtils.isEmpty(filter) || "default".equals(filter)) {
			throw new IllegalArgumentException("Resource is not found with filter");
		}
		return get(id);
		// return
		// EmployeeEntity.builder().id(101l).fullName("Maha").address("Bedngaluru").build();
	}

	@Override
	public EmployeeEntity searchByName(String fullName) {
		return employeeRepository.findByFullName(fullName).get();
	}

	@Override
	public EmployeeEntity searchByName(String fullName, Long id) {
		return employeeRepository.findUsingIdAndFullName(id, fullName).get();
	}

	@Override
	public EmployeeBean projectFullName(Long id) {
		return employeeRepository.projectFullName(id).get();
	}

	@Override
	public List<EmployeeAddressBean> projectEmployeeAndAddress() {
		return employeeRepository.projectEmployeeAndAddress().get();
	}

}
