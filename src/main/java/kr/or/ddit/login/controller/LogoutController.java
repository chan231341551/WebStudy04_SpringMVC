package kr.or.ddit.login.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController{
	
	@PostMapping("/login/logout.do")
	public String logout(HttpSession session) throws ServletException {
	
//		session.removeAttribute("authMember");
		session.invalidate();
		
		String viewName = "redirect:/";
		return viewName;
		
		
	}
}
