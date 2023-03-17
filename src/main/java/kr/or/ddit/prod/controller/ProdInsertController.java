package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.InsertGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/prod/prodInsert.do")
@Controller
public class ProdInsertController  {
	@Inject
	private ProdService service;
	
	@ModelAttribute("prod")
	public ProdVO prod() {
		return new ProdVO();
	}
	
	@GetMapping
	public String prodInsert(HttpServletRequest req) {
		return "prod/prodForm";

	}
	
	
	@PostMapping
	public String insertProcess(
		@Validated(InsertGroup.class) @ModelAttribute("prod") ProdVO prod // 커맨더 객체
		,Errors errors
		,Model model
		) throws IOException, ServletException {
			
//	    1. 저
		
		boolean valid = !errors.hasErrors();
		log.info("valid : {}",valid);
		String viewName = "";
		if(valid) {
			ServiceResult result = service.createProd(prod);
			if(ServiceResult.OK == result) {
				viewName = "redirect:/prod";
			}
			else {
				model.addAttribute("message", "서버 오류, 쫌다 다시");
				viewName = "prod/prodForm";
			}
			
		}
		else {
			viewName = "prod/prodForm";
		}
		return viewName;
	}
		
}
	


