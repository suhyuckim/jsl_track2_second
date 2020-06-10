package command.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.User_dao;

public class UserIdCheck {
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		User_dao dao = new User_dao();
		String id = request.getParameter("t_id");
		String result = dao.getCheck(id);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(result);
	}
}