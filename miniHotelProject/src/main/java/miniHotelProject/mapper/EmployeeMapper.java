package miniHotelProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import miniHotelProject.domain.EmployeeDTO;

@Mapper
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeSelectAll();
	public EmployeeDTO employeeSelectOne(String empNum);
	public Integer employeeUpdate(EmployeeDTO dto);
	public void employeeDelete(String empNum);
	public String getEmpNum(String empId);

}
