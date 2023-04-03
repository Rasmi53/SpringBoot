package com.fueiji.employee.service;

import java.util.List;

import javax.validation.Valid;

import com.fueiji.employee.bean.EmployeeAddressBean;
import com.fueiji.employee.bean.EmployeeBean;
import com.fueiji.employee.entity.EmployeeEntity;

public interface EmployeeService {

	public void save(EmployeeEntity employee);

	public EmployeeEntity get(long id);

	public EmployeeEntity get(long id, String filter);

	public List<EmployeeEntity> getAll();

	public void delete(long id);

	public void update(@Valid EmployeeEntity employee);

	public EmployeeEntity searchByName(String fullName);

	public EmployeeEntity searchByName(String fullName, Long id);

	EmployeeBean projectFullName(Long id);

	List<EmployeeAddressBean> projectEmployeeAndAddress();

}
