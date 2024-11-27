package miniHotelProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import miniHotelProject.command.LoginCommand;
import miniHotelProject.domain.AuthInfoDTO;
import miniHotelProject.mapper.LoginMapper;

@Service
public class UserLoginService {
	@Autowired
	   LoginMapper loginMapper;
	   @Autowired
	   PasswordEncoder passwordEncoder;
	   public int execute(LoginCommand loginCommand
			   , BindingResult result
			   , HttpSession session
			   , HttpServletResponse resp) {
		   int i = 0;
	      AuthInfoDTO auth = loginMapper.loginSelectOne(loginCommand.getUserId());
	      if(auth != null) {
	         System.out.println("아이디가 존재합니다.");
	         //본문(Command)의 비밀번호, 암호문(DB)의의 비밀번호
	         if(passwordEncoder.matches(loginCommand.getUserPw()
	        		 , auth.getUserPw())) { 
	            System.out.println("비밀번호가 일치합니다.");
	            session.setAttribute("auth", auth);
	            
	            //쿠키
	            boolean storeId = loginCommand.isIdStore(); // 1. 요청
	            boolean autoLogin = loginCommand.isAutoLogin();
	            if(autoLogin == true) {
		               //2. 쿠키생성
		               Cookie cookie = new Cookie("autoLogin", auth.getUserId()); 
		               cookie.setPath("/");
		               cookie.setMaxAge(60*60*24*30); 
		               //3. 웹브라우저로 전달
		               
		               resp.addCookie(cookie);
	            }
	            if(storeId == true) {
	               //2. 쿠키생성
	               Cookie cookie = new Cookie("storeId", auth.getUserId()); 
	               cookie.setPath("/");
	               cookie.setMaxAge(60*60*24*30); 
	               //3. 웹브라우저로 전달
	               resp.addCookie(cookie);
	            }else {
	               // 쿠키 삭제
	               Cookie cookie = new Cookie("storeId", ""); 
	               cookie.setPath("/");
	               cookie.setMaxAge(0); 
	               // 웹브라우저로 전달
	               resp.addCookie(cookie);
	            }
	            
	            i = 1;
	            
	         } else {
	        	 result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 일치하지않습니다.");
	             System.out.println("비밀번호가 일치하지않습니다.");
	         }
	      } else {
	    	  result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
	          System.out.println("아이디가 존재하지 않습니다.");
	      }
	      return i;
	   }
}
