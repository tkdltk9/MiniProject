package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.MemberDTO;
import miniHotelProject.mapper.MemberInfoMapper;

@Service
public class MemberMyInfoService {
	@Autowired
	MemberInfoMapper memberInfoMapper;
	public void execute(HttpSession session, Model model) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String memberId = auth.getUserId();
		MemberDTO dto = memberInfoMapper.memberSelectOne(memberId);
		model.addAttribute("memberCommand", dto);
	}
}
