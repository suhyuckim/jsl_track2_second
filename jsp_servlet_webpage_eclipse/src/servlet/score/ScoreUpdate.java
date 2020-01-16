package servlet.score;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Score_DAO;
import dto.Score_DTO;

/**
 * Servlet implementation class ScoreUpdate
 */
@WebServlet("/ScoreUpdate")
public class ScoreUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ScoreUpdate() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Score_DAO dao = new Score_DAO();
		String id = request.getParameter("t_id");
		String name = request.getParameter("t_name");
		String kor = request.getParameter("t_kor");
		String eng = request.getParameter("t_eng");
		String mat = request.getParameter("t_mat");
		int total   = dao.getTotal(Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat));
		double ava	= dao.getAva(total, 3);
		Score_DTO dto = new Score_DTO(id, name, Integer.parseInt(kor), Integer.parseInt(eng), Integer.parseInt(mat), total, ava);
		int result = dao.getScoreUpdate(dto);
		
		if(result > 0) {
			System.out.println("수정 완료");
		} else {
			System.out.println("수정 실패");
		}
		response.sendRedirect("/ScoreList");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}