package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.EmployeeCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.EmployeeDTO;
import miniHotelProject.mapper.EmployeeInfoMapper;

@Service
public class EmployeeMyUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;
	public String execute(HttpSession session, EmployeeCommand employeeCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpName(employeeCommand.getEmpName());
		dto.setEmpId(employeeCommand.getEmpId());
		dto.setEmpAddr(employeeCommand.getEmpAddr());
		dto.setEmpAddrDetail(employeeCommand.getEmpAddrDetail());
		dto.setEmpEmail(employeeCommand.getEmpEmail());
		dto.setEmpNum(employeeCommand.getEmpNum());
		dto.setEmpPhone(employeeCommand.getEmpPhone());
		dto.setEmpPost(employeeCommand.getEmpPost());
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String currentPw = auth.getUserPw();
		if(passwordEncoder.matches(employeeCommand.getEmpPw(), currentPw)) {
			employeeInfoMapper.employeeUpdate(dto);
			return "200";
		}else return "000";
	}
}
