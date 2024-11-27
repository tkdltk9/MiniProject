package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.EmployeeCommand;
import miniHotelProject.command.MemberCommand;
import miniHotelProject.service.myPage.EmployeeDropService;
import miniHotelProject.service.myPage.EmployeeMyInfoService;
import miniHotelProject.service.myPage.EmployeeMyUpdateService;
import miniHotelProject.service.myPage.MemberDropService;
import miniHotelProject.service.myPage.MemberMyInfoService;
import miniHotelProject.service.myPage.MemberMyUpdateService;





@RequestMapping("myPage")
@Controller
public class MyPageController {
	@Autowired
	MemberMyInfoService memberMyInfoService;
	@Autowired
	MemberMyUpdateService memberMyUpdateService;
	@Autowired
	MemberDropService memberDropService;
	@Autowired
	EmployeeMyInfoService employeeMyInfoService;
	@Autowired
	EmployeeMyUpdateService employeeMyUpdateService;
	@Autowired
	EmployeeDropService employeeDropService;
	@RequestMapping("memMyPage")
	public String memMyPage(HttpSession session, Model model) {
		memberMyInfoService.execute(session, model);
		return "thymeleaf/myPage/memMyPage";
	}
	@GetMapping("memUpdate")
	public String memUpdate(HttpSession session, Model model) {
		memberMyInfoService.execute(session, model);
		return "thymeleaf/myPage/memModify";
	}
	@PostMapping("memModify")
	public String memModify(HttpSession session, MemberCommand memberCommand) {
		String result = memberMyUpdateService.execute(session, memberCommand);
		if(result == "200") {
			return "redirect:memMyPage";
		}else return "thymeleaf/myPage/memModify";
		
	}
	@GetMapping("memDrop")
	public String memDrop() {
		return "thymeleaf/myPage/memDrop";
	}
	@PostMapping("memDropOk")
	public String memDropOk(HttpSession session, String memberPw) {
		String result = memberDropService.execute(session, memberPw);
		if(result == "200") {
			return "redirect:/login/logout";
		}else return "thymeleaf/myPage/memDrop";
	}
	@RequestMapping("empMyPage")
	public String empMyPage(HttpSession session, Model model) {
		employeeMyInfoService.execute(session, model);
		return "thymeleaf/myPage/empMyPage";
	}
	@GetMapping("empUpdate")
	public String empModify(HttpSession session, Model model) {
		employeeMyInfoService.execute(session, model);
		return "thymeleaf/myPage/empModify";
	}
	@PostMapping("empModify")
	public String empModify(HttpSession session, EmployeeCommand employeeCommand) {
		String result = employeeMyUpdateService.execute(session, employeeCommand);
		if(result == "200") {
			return "redirect:empMyPage";
		}else return "thymeleaf/myPage/empModify";
	}
	@GetMapping("empDrop")
	public String empDrop() {
		return "thymeleaf/myPage/empDrop";
	}
	@PostMapping("empDropOk")
	public String empDropOk(HttpSession session, String empPw) {
		String result = employeeDropService.execute(session, empPw);
		if(result == "200") {
			return "redirect:/login/logout";
		}else return "thymeleaf/myPage/empDrop";
	}
	
}
