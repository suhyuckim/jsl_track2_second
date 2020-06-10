package jsl.spr.mypjt;

import java.sql.Connection;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DB_connect_test {
	
	@RequestMapping(value = "/dbcon") 
	//브라우저 주소에 dbcon이라고 요청하면 해당 내용을 뜨게 해준다.
	public String dbcon() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracleJSL");
			Connection con = dataSource.getConnection();
			
			if(con != null) {
				System.out.println("db 연결 성공");
			} else {
				System.out.println("db 연결 실패");
			}
		}catch(Exception e) {
			
		}
		return "db_connect_test";
	}
}