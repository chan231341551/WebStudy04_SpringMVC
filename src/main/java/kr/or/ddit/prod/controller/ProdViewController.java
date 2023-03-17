package kr.or.ddit.prod.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.prod.service.ProdService;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;

/**
 * 상품 상세 조회 시, 해당 거래처의 모든 정보 함께 조회함.
 * 상품 상세 조회 시, 구매자 리스트(회원아이디, 회원명, 휴대폰, 이메일, 마일리지) 함께 조회.
 * 분류명도 함께 조회함.
 *
 */
@RequiredArgsConstructor
@Controller
public class ProdViewController {
   private final ProdService service;
   
   @GetMapping("/prod/{prodId}")
   public String prodView(
         @PathVariable("prodId") String prodId
         , Model model
   ){
//      String prodId = req.getParameter("what");
//      if(StringUtils.isBlank(prodId)) {
//         resp.sendError(400);
//         return;
//      }

      ProdVO prod = service.retriveProd(prodId);
      
      model.addAttribute("prod", prod);
      return "prod/prodView"; // logical view name
      
   }
}