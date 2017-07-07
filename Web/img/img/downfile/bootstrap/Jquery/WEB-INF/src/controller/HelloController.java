package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class HelloController {
	
	// hello.do��� ��û�� url�� ���� ������ �� �Ʒ� �޼��� �����Ű�ÿ�.
	@RequestMapping("/hello.do")
	public ModelAndView printHello(){
		String data = "Hello World!!";	// jsp�������� �Ѱ��� ��
		
		// ������ �����ӿ�ũ���� ����ϰ� �ִ� jsp������! ��, view�������� �����͸� �ѱ�� ���� ��ü
		ModelAndView mav = new ModelAndView();
		mav.addObject("data",data);		// "Hello World!!"�����͸� �Ʒ��� HelloPrint.jsp�� ������ ���� ���.
		mav.setViewName("HelloPrint");	// Ȯ���� ���� ������ jsp������ ���ϸ� ����
		
		// ModelAndView ��ü ����
		return mav;
	}
}
