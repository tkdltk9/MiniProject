package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.EmployeeDTO;

@Mapper
public interface EmployeeInfoMapper {
	EmployeeDTO employeeSelectOne(String empId);
	Integer employeeUpdate(EmployeeDTO dto);
	Integer employeeDelete(String empId);

}
