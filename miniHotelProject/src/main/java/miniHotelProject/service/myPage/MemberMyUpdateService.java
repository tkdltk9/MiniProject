package miniHotelProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.MemberCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.domain.MemberDTO;
import miniHotelProject.mapper.MemberInfoMapper;

@Service
public class MemberMyUpdateService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	MemberInfoMapper memberInfoMapper;
	public String execute(HttpSession session, MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberPhone1(memberCommand.getMemberPhone1());
		dto.setMemberPost(memberCommand.getMemberPost());
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String currentPw = auth.getUserPw();
		if(passwordEncoder.matches(memberCommand.getMemberPw(), currentPw)) {
			memberInfoMapper.memberUpdate(dto);
			return "200";
		}else return "000";
	}
}
