package com.saltlux.mydictionary.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.saltlux.mydictionary.security.Auth;
import com.saltlux.mydictionary.service.OneToOneService;
import com.saltlux.mydictionary.vo.OneToOneVo;
import com.saltlux.mydictionary.vo.UserVo;

@Controller
@RequestMapping("/oneToOne")
public class OneToOneController {

	@Autowired
	private OneToOneService oneToOneService;

	@Auth
	@RequestMapping("")
	public String index(HttpSession session, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		int page = 1;
		List<OneToOneVo> list = oneToOneService.findAll(page, authUser.getName());
		model.addAttribute("list", list);
		model.addAttribute("p", page);

		return "oneToOne/index";
	}

	@RequestMapping(value = "/{page}")
	public String index(HttpSession session, @PathVariable("page") int page, Model model) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		List<OneToOneVo> list = oneToOneService.findAll(page, authUser.getName());
		model.addAttribute("list", list);
		model.addAttribute("p", page);

		return "oneToOne/index";
	}

	@RequestMapping("/writeForm")
	public String writeForm() {

		return "oneToOne/write";
	}

	@RequestMapping("/write")
	public String write(HttpSession session, @RequestParam String title, @RequestParam String content) {
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		
		OneToOneVo vo = new OneToOneVo();
		vo.setTitle(title);
		vo.setContent(content);
		vo.setWriter(authUser.getName());
		oneToOneService.write(vo);
		
		return "redirect:/oneToOne/";
	}

	@RequestMapping(value = "/detail/{no}")
	public String detail(@PathVariable("no") String no, Model model) {
		OneToOneVo vo = oneToOneService.findOne(no);

		model.addAttribute("vo", vo);

		return "oneToOne/detail";
	}

	@RequestMapping("/modify")
	public String modify() {

		return "oneToOne/modify";
	}

	@RequestMapping("/search")
	public String search(@RequestParam(defaultValue = "title") String searchOption,
			@RequestParam(defaultValue = "") String keyword, Model model) {
		int page = 1;
		List<OneToOneVo> list = oneToOneService.search(searchOption, keyword, page);
		model.addAttribute("list", list);
		model.addAttribute("p", page);

		return "oneToOne/search";
	}

	@RequestMapping(value = "/search/{page}")
	public String search(@RequestParam(defaultValue = "title") String searchOption,
			@RequestParam(defaultValue = "") String keyword, @PathVariable("page") int page, Model model) {
		List<OneToOneVo> list = oneToOneService.search(searchOption, keyword, page);
		model.addAttribute("list", list);
		model.addAttribute("p", page);

		return "oneToOne/search";
	}
}