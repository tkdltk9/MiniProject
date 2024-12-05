package miniHotelProject.controller;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.LoginCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.LoginMapper;
import miniHotelProject.service.UserLoginService;


@RequestMapping("login")
@Controller
public class LoginController {
	@Autowired
	UserLoginService userLoginService;
	@Autowired
	LoginMapper loginMapper;
	@GetMapping("login")
	public String login(LoginCommand loginCommand, Model model, HttpServletRequest req) {
			//, @RequestParam(value="page", required = false, defaultValue = "1") Integer page
			//, Model model) {
		// mainGoodsListService.execute(page, model);
		Cookie [] cookies = req.getCookies();
        if(cookies != null && cookies.length > 0) {
        	for(Cookie cookie : cookies) {
        		if(cookie.getName().equals("userId")) {
        			AuthInfoDTO auth = loginMapper.loginSelectOne(cookie.getValue());
        			loginCommand.setUserId(auth.getUserId());
        			model.addAttribute("loginCommand", loginCommand);
        		}
        		if(cookie.getName().equals("autoLogin")) {
        			AuthInfoDTO auth = loginMapper.loginSelectOne(cookie.getValue());
	            	HttpSession session = req.getSession();
	            	session.setAttribute("auth", auth);
                }
            }
        }
        return "thymeleaf/login/login";
	}
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand
			, BindingResult result
			, HttpSession session
			, HttpServletResponse resp) {
		userLoginService.execute(loginCommand, result, session, resp);
		
		if(result.hasErrors()) {
			return "thymeleaf/login/login";
		}
		return "redirect:/";
	}
	@GetMapping("logout")
	public String logout(HttpSession session, HttpServletResponse resp) {
		//2. 쿠키생성
        Cookie cookie = new Cookie("autoLogin", ""); 
        cookie.setPath("/");
        cookie.setMaxAge(0); 
        // 웹브라우저로 전달
        resp.addCookie(cookie);
		
		session.invalidate();
		
		
		return "redirect:/";
	}
	@GetMapping("item.login")
	public String item(LoginCommand loginCommand) {
		return "thymeleaf/login";
	}
	@PostMapping("item.login")
	public void item(LoginCommand loginCommand,BindingResult result
			,HttpSession session, HttpServletResponse response) {
		userLoginService.execute(loginCommand, result, session, response);
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String str = "<script language='javascript'>";
			   str+= " opener.location.reload();";
			   str+= " window.self.close();";
		       str+= " </script>"; 
		System.out.println(str);
		out.print(str);
		out.close();
	}
}
