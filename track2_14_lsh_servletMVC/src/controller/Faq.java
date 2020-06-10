package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import command.faq.FaqCommand;
import command.faq.FaqDelete;
import command.faq.FaqList;
import command.faq.FaqSave;
import command.faq.FaqUpdate;
import command.faq.FaqView;

/**
 * Servlet implementation class Faq
 */
@WebServlet("/Faq")
public class Faq extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Faq() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gubun = request.getParameter("t_gubun");
		
		if(gubun == null) gubun = "list";
		String viewPage = "";
		
		if(gubun.equals("list")) {
			FaqCommand faq = new FaqList();
			faq.execute(request);
			viewPage = "/faq/faq_list.jsp";
			
		} else if(gubun.equals("view")) {
			FaqCommand faq = new FaqView();
			faq.execute(request);
			viewPage = "/faq/faq_view.jsp";
			
		} else if(gubun.equals("writeForm")) {
			viewPage = "/faq/faq_write.jsp";
			
		} else if(gubun.equals("save")) {
			FaqCommand faq = new FaqSave();
			faq.execute(request);
			viewPage = "/common_alert_page.jsp";
			
		} else if(gubun.equals("updateForm")) {
			FaqCommand faq = new FaqView();
			faq.execute(request);
			viewPage =  "/faq/faq_update.jsp";
			
		} else if(gubun.equals("update")) {
			FaqCommand faq = new FaqUpdate();
			faq.execute(request);
			viewPage = "/common_alert_page.jsp";
			
		} else if(gubun.equals("delete")) {
			FaqCommand faq = new FaqDelete();
			faq.execute(request);
			viewPage = "/common_alert_page.jsp";
		}
		RequestDispatcher rd = request.getRequestDispatcher(viewPage);
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}