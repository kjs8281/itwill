package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

public class HelloController {
	
	// hello.do라는 요청이 url을 통해 들어왔을 때 아래 메서드 실행시키시오.
	@RequestMapping("/hello.do")
	public ModelAndView printHello(){
		String data = "Hello World!!";	// jsp페이지로 넘겨줄 값
		
		// 스프링 프레임워크에서 사용하고 있는 jsp페이지! 즉, view페이지로 데이터를 넘기기 위한 객체
		ModelAndView mav = new ModelAndView();
		mav.addObject("data",data);		// "Hello World!!"데이터를 아래의 HelloPrint.jsp로 보내기 위해 담기.
		mav.setViewName("HelloPrint");	// 확장자 명을 제외한 jsp페이지 파일명 설정
		
		// ModelAndView 객체 리턴
		return mav;
	}
}
