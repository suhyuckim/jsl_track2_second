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
	//������ �ּҿ� dbcon�̶�� ��û�ϸ� �ش� ������ �߰� ���ش�.
	public String dbcon() {
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracleJSL");
			Connection con = dataSource.getConnection();
			
			if(con != null) {
				System.out.println("db ���� ����");
			} else {
				System.out.println("db ���� ����");
			}
		}catch(Exception e) {
			
		}
		return "db_connect_test";
	}
}