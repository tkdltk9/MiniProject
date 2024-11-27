package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.EmployeeInfoMapper;

@Service
public class EmployeeDropService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;
	public String execute(HttpSession session, String empPw) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(empPw, auth.getUserPw())) {
			employeeInfoMapper.employeeDelete(auth.getUserId());
			return "200";
		}
		else return "000";
	}
}
