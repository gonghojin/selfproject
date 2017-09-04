package org.hojin.controller;

import javax.inject.Inject;

import org.hojin.domain.BoardVO;
import org.hojin.domain.Criteria;
import org.hojin.domain.PageMaker;
import org.hojin.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/board/*")
public class BoardController {
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject
	private BoardService service;
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model){
		logger.info("register get.........");
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String registerPOST(BoardVO vo, RedirectAttributes rttr) throws Exception{
		logger.info("register post...........");
		
		service.insert(vo);
		
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listAll";
	}
	
	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public void listAll(Model model) throws Exception{
		logger.info("show all list..............");
		
		model.addAttribute("list", service.readAll());
	}
	
	@RequestMapping(value = "/readPage", method = RequestMethod.GET)
	public void read(Criteria cri, @RequestParam("bno") int bno, Model model) throws Exception{
		logger.info("read.......");
		
		model.addAttribute(service.read(bno));
		model.addAttribute("cri", cri);
	}
	
	@RequestMapping(value = "/removePage", method = RequestMethod.POST)
	public String remove(@RequestParam("bno") int bno, Criteria cri, RedirectAttributes rttr) throws Exception{
		logger.info("remove..........");
		
		service.delete(bno);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		rttr.addFlashAttribute("msg", "success");
		
		return "redirect:/board/listPage";
		
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.GET)
	public void modifyGET(@ModelAttribute("cri") Criteria cri, @RequestParam("bno") int bno, Model model) throws Exception{
		logger.info("modifyGET............");
		
		model.addAttribute(service.read(bno));
		
	}
	
	@RequestMapping(value = "/modifyPage", method = RequestMethod.POST)
	public String modifyPOST(Criteria cri, BoardVO vo, RedirectAttributes attr) throws Exception{
		logger.info("modifyPOST...........");
		
		service.update(vo);
		attr.addAttribute("page", cri.getPage());
		attr.addAttribute("perPageNum", cri.getPerPageNum());
		attr.addFlashAttribute("msg", "success");
		return "redirect:/board/listPage";
	}
	
	@RequestMapping(value = "/listCri", method = RequestMethod.GET)
	public void listCri(Criteria cri, Model model) throws Exception{
		logger.info("listCri...........");
		
		model.addAttribute("list", service.listCriteria(cri));
		
	}
	
	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Criteria cri, Model model) throws Exception{
		logger.info(cri.toString());
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(service.listCountCriterial());
		
		model.addAttribute("list", service.listCriteria(cri));
		model.addAttribute("pageMaker", pageMaker);
		
	}
}
