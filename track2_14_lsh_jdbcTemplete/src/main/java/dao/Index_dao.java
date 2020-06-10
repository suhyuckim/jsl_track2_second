package dao;

import java.util.ArrayList;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import common.CommonTemplate;
import dto.News_dto;

public class Index_dao {
	private JdbcTemplate tem = CommonTemplate.getTempl();
	
	public ArrayList<News_dto> getIndexNewsList(){ //list
		RowMapper<News_dto> News_dtoS = new BeanPropertyRowMapper<News_dto>(News_dto.class);	
		String query = " select no, substr(title,0,26) as title, substr(content,0,32) as content, to_char(reg_date,'yyyy-MM-dd') as reg_date "+ 
					   " from track2_14_news "+ 
				       " order by no desc ";
		ArrayList<News_dto> dtos = (ArrayList<News_dto>)tem.query(query, News_dtoS);
		return dtos;
	}
}
