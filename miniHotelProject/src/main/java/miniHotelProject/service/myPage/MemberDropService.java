package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.MemberInfoMapper;

@Service
public class MemberDropService {
	@Autowired
	MemberInfoMapper memberInfoMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	public String execute(HttpSession session, String memberPw) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(passwordEncoder.matches(memberPw, auth.getUserPw())) {
			memberInfoMapper.memberDelete(auth.getUserId());
			return "200";
		}
		else return "000";
	}
}
