package common;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class FileUploadService {
//	public String resFileDir = "C:/eclipse project/track2online/track2_14_mybatis/src/main/webapp/file_room/resource/";
	String fileDIR ="";
	
	public boolean restore(MultipartFile multipartFile,String fName,String saveDir) {
		boolean result = true;
//		fileDIR = saveDir;
		
		if(saveDir.equals("resDir")) {
			fileDIR = CommonUtil.resFileDir;
		} else if(saveDir.equals("newsDir")) {
//			fileDIR = newsFileDir;
		}
        
		try {
			// 파일정보
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
   
   
  // 占쎈솁占쎌뵬占쎌뱽 占쎈뼄占쎌젫嚥∽옙 write 占쎈릭占쎈뮉 筌롫뗄苑뚳옙諭�
	private void writeFile(MultipartFile multipartFile, String saveFileName)throws IOException{
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(fileDIR + saveFileName);
		fos.write(data);
		fos.close();
	}
}