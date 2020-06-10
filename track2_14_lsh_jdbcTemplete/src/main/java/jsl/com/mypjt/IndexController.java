package jsl.com.mypjt;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import common.CommonTemplate;
import dao.Index_dao;
import dto.News_dto;

@Controller
public class IndexController {
	@Autowired
	public JdbcTemplate template;
	
	@Autowired
	public void setTemp1() {
		CommonTemplate.setTempl(template);
	}
	
	@RequestMapping("/")
	public String index(Model model) {
		Index_dao dao = new Index_dao();
		ArrayList<News_dto> dtos = dao.getIndexNewsList();
		model.addAttribute("t_news", dtos);
		return "/index";
	}
}