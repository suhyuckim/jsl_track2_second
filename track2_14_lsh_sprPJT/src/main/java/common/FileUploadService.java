package common;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileUploadService {
   
//	public static String noticeFileDir = "C:/Users/admin/Desktop/eclipse_spring_teacher_source/spring_kss_dbcp3/src/main/webapp/file_room/";
//	public static String newsFileDir = "";
	
	String fileDIR ="";
	
	public boolean restore(MultipartFile multipartFile,String fName,String saveDir) {
		boolean result = true;
		fileDIR = saveDir;
		/*
		if(saveDir.equals("noticeDir")) {
			fileDIR = noticeFileDir;
		} else if(saveDir.equals("newsDir")) {
			fileDIR = newsFileDir;
		}
        */
		try {
			// �뙆�씪 �젙蹂�
//			String originFilename = multipartFile.getOriginalFilename();
//			String extName  = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
//			Long size = multipartFile.getSize();
	   
			String saveFileName = fName;

//			System.out.println("originFilename : " + originFilename);
//			System.out.println("extensionName : " + extName);
//			System.out.println("size : " + size);
//			System.out.println("saveFileName : " + saveFileName);

			writeFile(multipartFile, saveFileName);
		} catch (IOException e) {
			result = false;
			
			throw new RuntimeException(e);
		}
		return result;
	}
   
   
  // �뙆�씪�쓣 �떎�젣濡� write �븯�뒗 硫붿꽌�뱶
	private void writeFile(MultipartFile multipartFile, String saveFileName)throws IOException{
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(fileDIR + saveFileName);
		fos.write(data);
		fos.close();
	}
}