package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import kr.or.ddit.enumpkg.ServiceResult;
import kr.or.ddit.exception.UserNotFoundException;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.PagingVO;
import kr.or.ddit.vo.ProdVO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProdServiceImpl implements ProdService {
	
	private final ProdDAO prodDAO;
	
	@Inject
	private WebApplicationContext context;
	
	private File saveFolder;
	@PostConstruct
	public void init() throws IOException {
		String saveFolderURL = "/resources/prodImages";
//	    ServletContext application = req.getServletContext();
//		String saveFolderPath = application.getRealPath(saveFolderURL);
		Resource saveFolderRes = context.getResource(saveFolderURL);
		saveFolder = saveFolderRes.getFile();
		if(!saveFolder.exists()) { 
			saveFolder.mkdirs();
		}
	}
	
	private void processProdImage(ProdVO prod) {
		try {
//			if(true) {
//				throw new RuntimeException("트랜잭션 관리 여부 확인 ");
//			}
			prod.saveTo(saveFolder);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public ProdVO retriveProd(String prodId) {
		ProdVO selectProd = prodDAO.selectProd(prodId);
		if(selectProd == null) {
			throw new RuntimeException(String.format(prodId+"에 해당하는 상품 없음."));
		}
	
		return selectProd;
		
		
	}
	@Override
	public void retrieveProdList(PagingVO<ProdVO> pagingVO) { //CALL BY REFERENCE
		pagingVO.setTotalRecord(prodDAO.selectTotalRecord(pagingVO));

		List<ProdVO> ProdList = prodDAO.selectProdList(pagingVO);
		pagingVO.setDataList(ProdList);
		log.info("ProdList : {}",ProdList);
		ProdList.stream()
		.forEach(System.out::println); // 메소드 레퍼런스 구조

	}
	@Inject
	private SqlSessionFactory SqlSessionFactory;
	@Override
	public ServiceResult createProd(ProdVO prod) {
		ServiceResult result = null;
		try(
			SqlSession sqlSession = SqlSessionFactory.openSession(); // 트랜잭션 시작
				
		){
			int rowcnt = prodDAO.insertProd(prod,sqlSession);
			processProdImage(prod);
			sqlSession.commit();
			
			result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
			
			return result;
		}
	}
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		
		ServiceResult result = null;
		retriveProd(prod.getProdId());
		
		int rowcnt = prodDAO.updateProd(prod);
		processProdImage(prod);
		
		result = rowcnt > 0 ? ServiceResult.OK : ServiceResult.FAIL;
		

		return result;
	}

}
