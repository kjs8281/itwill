package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;



public class DownloadView extends AbstractView{
	
	public DownloadView(){
		setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		
		//컨트롤러에서 넘긴 파일 객체를 받아줌
		File file = (File) model.get("downloadFile");
		
		//사용자에게 응답을 하기위해서 res객체 사용
		res.setContentType(getContentType());
		res.setContentLength((int)file.length());
		
		String userAgent = req.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1; //-1v파일의 끝까지
		String filename = null;
		
		if(ie){
			//파일을 읽어드림
			filename = URLEncoder.encode(file.getName() , "utf-8");
		}else{
			//파일명 패턴이 캐릭터셋이 틀리면 바꿔주시오
			filename = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		
		//응답헤더 설정
		res.setHeader("Content-Disposition", "attachment; filename=\"" + filename+"\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
		//설정이 완료되었다면 클라이언트 측으로 보내기위하여 outputStream객체 생성
		OutputStream out = res.getOutputStream();
		FileInputStream fis = null;
		
		try {
			fis = new FileInputStream(file);
			
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fis != null){
				try {
					fis.close();//자원 반납
				} catch (Exception e2) {

				}
			}
		}
		//실제 데이터를 내보내시오 즉시,
		out.flush();
	}

}
