package miniHotelProject.service.employee;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import miniHotelProject.domain.EmployeeDTO;
import miniHotelProject.mapper.EmployeeMapper;

@Service
public class EmployeeListService {
	@Autowired
	EmployeeMapper employeeMapper;
	public void execute(Model model) {
		List<EmployeeDTO> list = employeeMapper.employeeSelectAll();
		model.addAttribute("list", list);
		
	}
}
