package miniHotelProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.LoginCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.LoginMapper;


@Controller
@SpringBootApplication
public class MiniHotelProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniHotelProjectApplication.class, args);
	}
	@Autowired
	LoginMapper loginMapper;
	@GetMapping("/")
	public String index(LoginCommand loginCommand, Model model, HttpServletRequest req) {
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
        return "thymeleaf/index";
	}
	
}
