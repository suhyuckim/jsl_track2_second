package common;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MailSand
 */
@WebServlet("/MailSand")
public class MailSand extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MailSand() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();

		String from = "obull123@naver.com"; // 보내는 주소
		String to = "suhyuckim@gmail.com"; // 받는 주소
		String subject = "비밀번호를 안내";
		String content = 
		//이부분 꾸미면 예쁘게 만들 수 있음
		"<table border='0' > "+
			"<tr> "+
			"	<td>변경된 비밀번호 :  </td>"+
			"	<td> 땡땡땡~ 입니다</td> "+
			"</tr> "+
		"</table> "; 

		// SMTP 서버에 접속하기 위한 정보들
		Properties p = new Properties(); // 정보를 담을 객체
		p.put("mail.smtp.host","smtp.naver.com"); // 네이버 SMTP
		p.put("mail.smtp.port", "465"); 
		p.put("mail.smtp.starttls.enable", "true");
		p.put("mail.smtp.auth", "true");
		p.put("mail.smtp.debug", "true");
		p.put("mail.smtp.socketFactory.port", "465");
		p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		p.put("mail.smtp.socketFactory.fallback", "false");
		// SMTP 서버에 접속하기 위한 정보들
		try{
			Authenticator auth = new SMTPAuthenticatior();
			Session ses = Session.getInstance(p, auth);
			 
			//ses.setDebug(true);
			 
			MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
			msg.setSubject(subject); // 제목
			 
			Address fromAddr = new InternetAddress(from);
			msg.setFrom(fromAddr); // 보내는 사람
			 
			Address toAddr = new InternetAddress(to);
			msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람
			 
			msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩
			 
			Transport.send(msg); // 전송
		} catch(Exception e){
			e.printStackTrace();
			out.println("<script>alert('Send Mail Failed..');history.back();</script>");
			// 오류 발생시 뒤로 돌아가도록
			return;
		}
		out.println("<script>");
		out.println("alert('Send Mail Success!!');");
		out.println("location.href='/Index';");
		out.println("</script>");
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}