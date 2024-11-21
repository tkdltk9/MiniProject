package miniHotelProject.controller;

import javax.naming.Binding;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import miniHotelProject.command.EmployeeCommand;
import miniHotelProject.service.AutoNumService;
import miniHotelProject.service.employee.EmployeeWriteService;


@RequestMapping("employee")
@Controller
public class EmployeeController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeWriteService employeeWriteService;
	@GetMapping("empRegist")
	public String empRegist(Model model) {
		String autoNum = autoNumService.execute("emp_", "emp_num", 5, "employees");
		EmployeeCommand employeeCommand = new EmployeeCommand();
		employeeCommand.setEmpNum(autoNum);
		model.addAttribute("employeeCommand", employeeCommand);
		return "thymeleaf/employee/empRegist";
	}
	@PostMapping("empRegist")
	public String empRegist(@Validated EmployeeCommand employeeCommand
			, BindingResult result) {
		if(result.hasErrors()) {
			System.out.println("hassErrors 초비상!!!");
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
	
}
