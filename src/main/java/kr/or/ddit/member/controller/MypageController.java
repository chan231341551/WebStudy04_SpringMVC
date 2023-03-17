package kr.or.ddit.member.controller;


import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.member.service.MemberService;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.MemberVOWrapper;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MypageController{
	
	private final MemberService service;
	
	@RequestMapping("/mypage.do")
	public String myPage(
		Model model
		,MemberVOWrapper principal) {
	
//		principal = (MemberVOWrapper) req.getUserPrincipal();
		MemberVO authMember = principal.getRealMember();
		MemberVO member = service.retrieveMember(authMember.getMemId());
		
		model.addAttribute("member",member);
				
		String viewName = "member/memberView"; // logical view name
		
		return viewName;
	}
}
