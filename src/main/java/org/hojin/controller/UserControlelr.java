package org.hojin.controller;

import java.util.Date;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hojin.domain.UserVO;
import org.hojin.dto.LoginDTO;
import org.hojin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/user")
public class UserControlelr {
	
	private static final Logger logger = LoggerFactory.getLogger(UserControlelr.class);
	
	@Inject
	private UserService service;
	
	@RequestMapping(value = "/signUp", method = RequestMethod.GET)
	public void signUpGET(Model model){
		model.addAttribute("user", new UserVO());
		
	}
	
	@RequestMapping(value = "/signUp", method = RequestMethod.POST)
	
	public String signUpPOST(@Valid UserVO user, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return "/user/signUp";
		}
		return "redirect:/sboard/list";
	}
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void loginGET(@ModelAttribute("dto") LoginDTO dto){
		
	}
	
	@RequestMapping(value = "/loginPost", method = RequestMethod.POST)
	public void loginPOST(LoginDTO dto, HttpSession session, Model model) throws Exception{
		
		UserVO userVO = service.login(dto);
		
		if(userVO == null){
			return;
		}
		model.addAttribute("userVO", userVO);
		
		if(dto.isUseCookie()){
			
			int amount = 60 * 60* 24 * 7;
			
			Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
			
			service.keepLogin(userVO.getUid(), session.getId(), sessionLimit);
			
		}
		
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception{
		
		Object obj = session.getAttribute("login");
		
		if(obj != null){
			UserVO vo = (UserVO) obj;
			
			session.removeAttribute("login");
			session.invalidate();
			
			Cookie loginCookie = WebUtils.getCookie(request,"loginCookie");
			
			if(loginCookie != null){
				loginCookie.setMaxAge(0);
				response.addCookie(loginCookie);
				service.keepLogin(vo.getUid(), session.getId(), new Date());
				
			}
			
		}
		return "redirect:/sboard/list";
	}
}
