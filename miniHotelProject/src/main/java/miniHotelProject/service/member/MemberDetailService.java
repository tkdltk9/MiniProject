package miniHotelProject.service.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import miniHotelProject.domain.MemberDTO;
import miniHotelProject.mapper.MemberMapper;

@Service
public class MemberDetailService {
	@Autowired
	MemberMapper memberMapper;
	public void execute(String memberNum, Model model) {
		MemberDTO dto = memberMapper.memberSelectOne(memberNum);
		model.addAttribute("memberCommand", dto);
	}
}
