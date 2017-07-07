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
		
		//��Ʈ�ѷ����� �ѱ� ���� ��ü�� �޾���
		File file = (File) model.get("downloadFile");
		
		//����ڿ��� ������ �ϱ����ؼ� res��ü ���
		res.setContentType(getContentType());
		res.setContentLength((int)file.length());
		
		String userAgent = req.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1; //-1v������ ������
		String filename = null;
		
		if(ie){
			//������ �о�帲
			filename = URLEncoder.encode(file.getName() , "utf-8");
		}else{
			//���ϸ� ������ ĳ���ͼ��� Ʋ���� �ٲ��ֽÿ�
			filename = new String(file.getName().getBytes("utf-8"), "iso-8859-1");
		}
		
		//������� ����
		res.setHeader("Content-Disposition", "attachment; filename=\"" + filename+"\";");
		res.setHeader("Content-Transfer-Encoding", "binary");
		
		//������ �Ϸ�Ǿ��ٸ� Ŭ���̾�Ʈ ������ ���������Ͽ� outputStream��ü ����
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
					fis.close();//�ڿ� �ݳ�
				} catch (Exception e2) {

				}
			}
		}
		//���� �����͸� �������ÿ� ���,
		out.flush();
	}

}
