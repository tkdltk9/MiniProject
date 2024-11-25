package miniHotelProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import miniHotelProject.command.EmployeeCommand;
import miniHotelProject.service.AutoNumService;
import miniHotelProject.service.employee.EmployeeDeleteService;
import miniHotelProject.service.employee.EmployeeDetailService;
import miniHotelProject.service.employee.EmployeeListService;
import miniHotelProject.service.employee.EmployeeUpdateService;
import miniHotelProject.service.employee.EmployeeWriteService;



@RequestMapping("employee")
@Controller
public class EmployeeController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeWriteService employeeWriteService;
	@Autowired
	EmployeeListService employeeListService;
	@Autowired
	EmployeeDetailService employeeDetailService;
	@Autowired
	EmployeeUpdateService employeeUpdateService;
	@Autowired
	EmployeeDeleteService employeeDeleteService;
	@GetMapping("empRegist")
	public String empRegist(Model model) {
		String autoNum = autoNumService.execute("host_", "emp_num", 6, "employees");
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(autoNum);
		model.addAttribute("employeeCommand", employeeCommand);
		return "thymeleaf/employee/empRegist";
	}
	@PostMapping("empRegist")
	public String empRegist(@Validated EmployeeCommand employeeCommand
			, BindingResult result) {
		if (result.hasErrors()) {
		    result.getAllErrors().forEach(error -> {
		        System.out.println("Error: " + error.getDefaultMessage());
		    });
		    return "thymeleaf/employee/empRegist";
		}
		if(!employeeCommand.isEmpPwEqualEmpPwCon()) {
			System.out.println("비밀번호 불일치");
			result.rejectValue("empPwCon", "employeeCommand.empPwCon", "비밀번호가 일치하지 않습니다.");
			return "thymeleaf/employee/empRegist";
		}
		employeeWriteService.execute(employeeCommand);
		return "redirect:/";
	}
	@GetMapping("empList")
	public String empList(Model model) {
		employeeListService.execute(model);
		return "thymeleaf/employee/empList";
	}
	
	
	@GetMapping("empDetail")
	public String empDetail(@RequestParam("empNum") String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/empDetail";
	}
	
	@GetMapping("empUpdate")
	public String empUpdate(@RequestParam("empNum") String empNum, Model model) {
		employeeDetailService.execute(empNum, model);
		return "thymeleaf/employee/empUpdate";
	}
	@PostMapping("empModify")
	public String empModify(
			@Validated EmployeeCommand employeeCommand
			, BindingResult result) {
		if (result.hasErrors()) {
			return "thymeleaf/employee/empUpdate";
		}
		employeeUpdateService.execute(employeeCommand);
		return "redirect:empDetail?empNum=" + employeeCommand.getEmpNum();
	}
	@GetMapping("empDelete")
	public String empDelete(String empNum) {
		employeeDeleteService.execute(empNum);
		return "redirect:empList";
	}
	
	
	
}
