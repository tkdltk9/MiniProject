package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.EmployeeDTO;
import miniHotelProject.mapper.EmployeeInfoMapper;

@Service
public class EmployeeMyInfoService {
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empId = auth.getUserId();
		EmployeeDTO dto = employeeInfoMapper.employeeSelectOne(empId);
		model.addAttribute("employeeCommand", dto);
	}
}
