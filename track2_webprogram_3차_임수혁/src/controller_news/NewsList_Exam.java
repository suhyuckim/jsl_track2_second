package controller_news;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.News_DAO;
import dto.News_DTO;

/**
 * Servlet implementation class NewsList_Exam
 */
@WebServlet("/NewsList_Exam")
public class NewsList_Exam extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewsList_Exam() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		News_DAO dao = new News_DAO();
		String selValue = request.getParameter("t_sel"); 
		String txtValue = request.getParameter("t_search");
		if(selValue == null){
			selValue = "title";
			txtValue = "";
		}
			ArrayList<News_DTO> dtos = dao.getNewsList_exam(selValue, txtValue);
			request.setAttribute("t_dtos", dtos);
			request.setAttribute("selValue", selValue);
			request.setAttribute("txtValue", txtValue);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/news/news_list.jsp");
			dispatcher.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
