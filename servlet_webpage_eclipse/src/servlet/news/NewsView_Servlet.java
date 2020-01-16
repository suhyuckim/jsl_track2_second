package servlet.news;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.News_DAO;
import dto.News_DTO;
/**
 * Servlet implementation class NewsView_Servlet
 */
@WebServlet("/NewsView_Servlet")
public class NewsView_Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsView_Servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		News_DAO dao = new News_DAO();
		String news_no   = request.getParameter("t_newsNo");
		int nHit 		 = dao.newsHit_servlet(news_no);
		News_DTO dto 	 = dao.getNewsView_serlvet(news_no);
		request.setAttribute("t_dto", dto);
		RequestDispatcher rdp = request.getRequestDispatcher("/news/news_view.jsp");
		rdp.forward(request, response);	
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}