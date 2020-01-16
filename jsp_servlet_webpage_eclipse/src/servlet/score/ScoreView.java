package servlet.score;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Score_DAO;
import dto.Score_DTO;

/**
 * Servlet implementation class ScoreView
 */
@WebServlet("/ScoreView")
public class ScoreView extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScoreView() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score_DAO dao = new Score_DAO();
		String id = request.getParameter("t_id");
		Score_DTO dto = dao.getScoreView(id);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/score/score_v.jsp");
		rdp.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}