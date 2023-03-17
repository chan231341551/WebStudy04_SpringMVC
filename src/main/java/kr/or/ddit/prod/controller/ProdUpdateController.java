package kr.or.ddit.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.validate.UpdateGroup;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.ProdVO;

//    /prod/prodUpdate.do(GET, POST)

@Controller
@RequestMapping("/prod/prodUpdate.do")
public class ProdUpdateController {
	@Inject
	private ProdService service;

	@GetMapping
	public String updateForm(
			@RequestParam("what") String prodId
			, Model model
			) {
		ProdVO prod = service.retriveProd(prodId);
		model.addAttribute("prod", prod);

		return "prod/prodForm";
	}

	@PostMapping
	public String updateProd(
			@Validated(UpdateGroup.class) @ModelAttribute("prod") ProdVO prod
			, @RequestParam("what") String prodId
			, Model model
			, BindingResult errors
			) throws IOException {

		

		String viewName = null;

		boolean valid = !errors.hasErrors();

		if(valid) {
			ServiceResult result = service.modifyProd(prod);
			if(ServiceResult.OK == result) {
				viewName = "redirect:/prod/" + prod.getProdId();
			}else {
				model.addAttribute("message", "서버오류");
				viewName = "prod/prodForm";
			}
		}else {
			viewName = "prod/prodForm";
		}
		return viewName;


	}
}