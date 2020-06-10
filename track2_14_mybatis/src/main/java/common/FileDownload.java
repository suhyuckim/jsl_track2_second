package common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FileDownload {
	public void fileDown(HttpServletRequest request, HttpServletResponse response) {
		String savePath = "";
	 	String fileName = request.getParameter("t_file");
	 	String gubun = request.getParameter("t_gubun");

		if(gubun.equals("resDir")) {
			savePath = CommonUtil.resFileDir;
			//savePath = "C:/Users/JSLHRD/Desktop/39_source/jsl39_source/jsl39_00_springPJT/src/main/webapp/file_room/resource/";			
		} else if(gubun.equals("news")){
			savePath = "";
		}	

			
	    String orgfilename = fileName ;
	 
	    InputStream in = null;
	    OutputStream os = null;
	    File file = null;
	    boolean skip = false;
	    String client = "";
	  
	    try{
	        try{
	            file = new File(savePath, fileName);
	            in = new FileInputStream(file);
	        }catch(FileNotFoundException fe){
	            skip = true;
	        }
	         
	        client = request.getHeader("User-Agent");
	        response.reset() ;
	        response.setContentType("application/octet-stream");
	        response.setHeader("Content-Description", "JSP Generated Data");
	 
	        if(!skip){
	 
	            // IE
	            if(client.indexOf("MSIE") != -1){
	                response.setHeader ("Content-Disposition", "attachment; filename="+orgfilename);
	 
	            }else{
	            	// 한글 파일명 처리
	                orgfilename = new String(orgfilename.getBytes("utf-8"),"iso-8859-1");

	                response.setHeader("Content-Disposition", "attachment; filename=\"" + orgfilename + "\"");
	                response.setHeader("Content-Type", "application/octet-stream; charset=utf-8");
	            } 
	             
	            response.setHeader ("Content-Length", ""+file.length() );
	       
	            os = response.getOutputStream();
	            byte b[] = new byte[(int)file.length()];
	            int leng = 0;
	             
	            while( (leng = in.read(b)) > 0 ){
	                os.write(b,0,leng);
	            }
	 
	        }else{
	            response.setContentType("text/html;charset=UTF-8");
	            PrintWriter out = response.getWriter();
	            out.println("<script language='javascript'>alert('�뙆�씪�쓣 李얠쓣 �닔 �뾾�뒿�땲�떎');history.back();</script>");
	        }
	         
	        in.close();
	        os.close();
	 
	    }catch(Exception e){
	      e.printStackTrace();
	    }		
	}
}
