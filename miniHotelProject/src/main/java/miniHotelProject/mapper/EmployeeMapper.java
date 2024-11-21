package miniHotelProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO dto);

}
