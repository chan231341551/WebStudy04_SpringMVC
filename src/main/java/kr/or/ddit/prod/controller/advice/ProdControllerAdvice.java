package kr.or.ddit.prod.controller.advice;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import kr.or.ddit.prod.dao.OthersDAO;
import kr.or.ddit.vo.BuyerVO;

// 1.빈으로 등록한다.
// 2.타겟으로 지정한다.
@ControllerAdvice("kr.or.ddit.prod.controller")
public class ProdControllerAdvice {
	
	@Inject
	private OthersDAO othersDAO;
	

	@ModelAttribute("lprodList")
	public List<Map<String, Object>> buyerList() {
		return othersDAO.selectLprodList();
	}

	@ModelAttribute("buyerList")
	public List<BuyerVO> lprodList() {
		return othersDAO.selectBuyerList(null);
	}
}
