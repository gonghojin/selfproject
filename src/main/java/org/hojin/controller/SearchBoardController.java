package org.hojin.controller;

import java.util.List;

import javax.inject.Inject;

import org.hojin.domain.BoardVO;
import org.hojin.domain.PageMaker;
import org.hojin.domain.SearchCriteria;
import org.hojin.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/sboard/*")
public class SearchBoardController {
	@Inject
	private BoardService service;
	
	private static final Logger logger = LoggerFactory.getLogger(SearchBoardController.class);
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		
		
		model.addAttribute("list", service.listSearchCriteria(cri));
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listSearchCount(cri));
		
		model.addAttribute(pageMaker);
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void readPage(@ModelAttribute("cri") SearchCriteria cri, int bno, Model model) throws Exception{
		model.addAttribute(service.read(bno));
		
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String removePage(SearchCriteria cri, int bno, RedirectAttributes model) throws Exception{
		service.delete(bno);
		
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		
		model.addFlashAttribute("msg", "success");
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyPageGET(int bno, @ModelAttribute("cri") SearchCriteria cri, Model model ) throws Exception{
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPagePOST(BoardVO vo, SearchCriteria cri, RedirectAttributes model) throws Exception{
		logger.info(cri.toString());
		service.update(vo);
		
		model.addAttribute("page", cri.getPage());
		model.addAttribute("perPageNum", cri.getPerPageNum());
		model.addAttribute("searchType", cri.getSearchType());
		model.addAttribute("keyword", cri.getKeyword());
		
		model.addFlashAttribute("msg", "success");
		
		logger.info(model.toString());
		return "redirect:/sboard/list";
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET() throws Exception{
		logger.info("registerGET...........");
	}
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes attr) throws Exception{
		logger.info("registerPOST...........");
		logger.info(vo.toString());
		
		service.insert(vo);
		
		attr.addFlashAttribute("msg", "success");
		
		return "redirect:/sboard/list";
		
		
	}
	
	@RequestMapping("/getAttach/{bno}")
	@ResponseBody
	public List<String> getAttach(@PathVariable("bno") Integer bno) throws Exception{
		return service.getAttach(bno);
	}
}
