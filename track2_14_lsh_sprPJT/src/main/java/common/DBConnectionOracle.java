package common;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBConnectionOracle {
	public Connection getConnection() {
		Connection con = null;
		try {
			Context context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracleJSL");
			con = dataSource.getConnection();
			if(con == null) System.out.println("db 연결 실패");
		}catch(Exception e) {
			System.out.println("*Class명:"+this.getClass().getName()+" *Method_name:"+Thread.currentThread().getStackTrace()[1].getMethodName()+" *SQL error :"+e.getMessage());
		}
		return con;
	}	
	
	public void close(Connection con, PreparedStatement ps) {
		try {
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");	
		}
    }
	
	public void close(Connection con, PreparedStatement ps, ResultSet result) {
		try {
			if(result != null)	result.close();
			if(ps != null) 		ps.close();
			if(con != null)		con.close();
		} catch(Exception e) {
			System.out.println("=========== 종료 error ===========");
		}
    }	
}	