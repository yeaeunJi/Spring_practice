package com.saltlux.mysite.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mysite.repository.GuestbookRepository;
import com.saltlux.mysite.service.GuestbookService;
import com.saltlux.mysite.vo.GuestbookVo;

@Controller
@RequestMapping("/guestbook")
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;
	
	@RequestMapping("")
	public String index(Model model) {
		List<GuestbookVo> list = guestbookService.findAll();
		model.addAttribute("list", list);
		return "guestbook/index";
	}
	
	@RequestMapping("/insert")
	public String add(GuestbookVo vo) {
		guestbookService.insert(vo);
		return "redirect:/guestbook";
	}
	@RequestMapping(value="/delete", method=RequestMethod.GET) 
	public String delete(@RequestParam("no")  Long no, Model model) {
		model.addAttribute("no", no);
		return "guestbook/delete";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.POST) 
	public String delete(GuestbookVo vo) {
		guestbookService.delete(vo);
		return "redirect:/guestbook";
	}
}
