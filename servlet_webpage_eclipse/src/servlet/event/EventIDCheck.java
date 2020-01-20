package servlet.event;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class EventIDCheck
 */
@WebServlet("/EventIDCheck")
public class EventIDCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EventIDCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		JSONObject obj = new JSONObject(); //json-simple-1.1.1.jar에 들어있음
		obj.put("t_name2", "홍길동"); //왼쪽 : key, 오른쪽 : value t_name2에 홍길동을 대입함
		obj.put("t_addr2", "대전"); 
				
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(obj); //json을 넣음
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
