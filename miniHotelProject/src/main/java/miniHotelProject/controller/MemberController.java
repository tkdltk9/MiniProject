package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import miniHotelProject.command.MemberCommand;
import miniHotelProject.service.AutoNumService;
import miniHotelProject.service.member.MemberWriteService;
import org.springframework.web.bind.annotation.RequestParam;




@RequestMapping("member")
@Controller
public class MemberController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@GetMapping("memRegist")
	public String memRegist(Model model) {
		String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		MemberCommand memberCommand = new MemberCommand();
		memberCommand.setMemberNum(autoNum);
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/member/memRegist";
	}
	@PostMapping("memRegist")
	public String memRegist(@Validated MemberCommand memberCommand
			, BindingResult result) {
		if (result.hasErrors()) {
		    result.getAllErrors().forEach(error -> {
		        System.out.println("Error: " + error.getDefaultMessage());
		    });
		    return "thymeleaf/member/memRegist";
		}
		if(!memberCommand.isMemberPwEqualMemberPwCon()) {
			System.out.println("비밀번호 불일치");
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/member/memRegist";
		}
		memberWriteService.execute(memberCommand);
		return "redirect:/";
	}
	@GetMapping("memDetail")
	public String memDetail() {
		return "thymeleaf/member/memDetail";
	}
	
}
