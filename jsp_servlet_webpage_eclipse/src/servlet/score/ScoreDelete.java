package servlet.score;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Score_DAO;
import dao.Smember_DAO;

/**
 * Servlet implementation class ScoreDelete
 */
@WebServlet("/ScoreDelete")
public class ScoreDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScoreDelete() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score_DAO dao = new Score_DAO();
		String id = request.getParameter("t_id");
		int result = dao.getScoreDelete(id);
		
		if(result > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
		response.sendRedirect("/ScoreList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}