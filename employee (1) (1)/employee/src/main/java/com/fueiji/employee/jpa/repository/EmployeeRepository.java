package com.fueiji.employee.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fueiji.employee.bean.EmployeeAddressBean;
import com.fueiji.employee.bean.EmployeeBean;
import com.fueiji.employee.entity.EmployeeEntity;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

	Optional<EmployeeEntity> findByFullName(String fullName);

	Optional<EmployeeEntity> findByIdAndFullName(Long id, String fullName);

	// @Query(value = "select * from employee_data where id = ?1 and full_name =
	// ?2", nativeQuery = true)
	@Query(value = "select * from employee_data where id = :id and full_name = :fullName", nativeQuery = true)
	Optional<EmployeeEntity> findUsingIdAndFullName(@Param(value = "id") Long id,
			@Param(value = "fullName") String fullName);

	// Optional<EmployeeEntity> findByFullNameAndAge(String fullName, int age);

	int countByFullName(String fullName);

	@Query(value = "select full_name AS fullName from employee_data where id = :id", nativeQuery = true)
	Optional<EmployeeBean> projectFullName(@Param(value = "id") Long id);

	@Query(value = "select ed.full_name as fullName, ea.address,ea.pin_code AS pincode from employee_data ed,employee_address ea where ed.address_id=ea.id", nativeQuery = true)
	Optional<List<EmployeeAddressBean>> projectEmployeeAndAddress();
	
	
	
	
	
	

}
