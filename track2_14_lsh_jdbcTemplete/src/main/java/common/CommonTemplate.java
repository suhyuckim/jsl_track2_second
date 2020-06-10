package common;

import org.springframework.jdbc.core.JdbcTemplate;

public class CommonTemplate {
	public static JdbcTemplate templ;
	
	public static JdbcTemplate getTempl() {
		return templ;
	}
	public static void setTempl(JdbcTemplate templ) {
		CommonTemplate.templ = templ;
	}
	
}