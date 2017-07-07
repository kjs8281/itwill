package controller;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import model.BoardBean;
import model.MemberBean;
import model.ShoppingDAO;
import model.SuBean;
import model.SuCartBean;

public class ShoppingController {
	
	//설명 :
	//shopping-servlet.xml에 의존관계를 이용하여... <bean>객체에...
	//<bean id="sujak" name="/sujak.do" class="controller.ShoppingController"
	// 			p:shoppingDao-ref="shoppingDao"></bean> 라고 해놓았기 때문에...
	//shoppingDao객체를 가져올수 있다.
	ShoppingDAO shoppingDao;//DB와 연동된 DB작업할객체 가져와서 저장할 변수 선언
	
	//설정 취득 //DB와 연동된 DB작업할객체 가져와서 저장
	public void setShoppingDao(ShoppingDAO shoppingDao){
		this.shoppingDao = shoppingDao;
	}
	
	@RequestMapping("/index.do")//index.do라는 요청이 들어오면 아래의 메소드를 실행
	public ModelAndView index(HttpSession session){//회원 가입 정보를 사용하기위하여 세션을 설정
		
		//데이터와 jsp를 리턴해주는 객체 생성
		ModelAndView mav = new ModelAndView();
		
		//회원정보를 사용하기 위하여 세션 객체를 불러옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//세션을 이용하여 로그인 처리
		if(mbean==null){//top.jsp에서 로그인 정보를 처리하기 위한 소스
			mav.addObject("mbean",null); //데이터를 아래의 ShoppingMain.jsp로 보내주기위해 데이터 담기
			mav.setViewName("ShoppingMain");//ShoppingMain.jsp페이지명 셋팅
			
		}else{
			mav.addObject("mbean",mbean);//데이터를 아래의 ShoppingMain.jsp로 보내주기 위해 데이터 담기
			mav.setViewName("ShoppingMain");//ShoppingMain.jsp 페이지명 셋팅
		}
		
		return mav;
		
		//수작업 공구를 눌렀다면 자동으로 호출되는 메소드
		
	}
	
	@RequestMapping("/sujak.do")
	public ModelAndView suJak(String num){
		
		//리턴할 객체 생성
		ModelAndView mav = new ModelAndView();
		//어느 요청이 들어왔는지에 대한 정보를얻어와서 해당 model에 접근하여 데이터를 가져오도록
		if(num==null){//신제품을 누르거나 top에 있는 메뉴중 수작업 공구버튼을 누르면
			
			//먼저할일!!
			//DB에 접근하여.. 검색한 모든 수작업 공구를 가져와야한다.
			//그러기에 앞서 Dto클래스를만들자!
			
			//DB에 접근하여.. 검색한 모든 수작업 공구들(SuBean)을 List에 담아서 가져오기
			List<SuBean> list = shoppingDao.getAllSutool();
			
			//데이터를 아래의 ShoppingMain.jsp로 보내주기 위해 데이터를 new ModelAndView()객체에 담기
			mav.addObject("list",list);
		}else{//해당메뉴들을 선택시 사용되는 소스
			//왼쪽 수작업 공구 카테고리별 메뉴중 하나를 선택한 값을 전달하여 ...
			//DB에서 검색한 수작업공구글(SuBean들)을 List에 담아서 가져오기
			List<SuBean> list = shoppingDao.getSelectSutool(num);
			
			//데이터를 아래의 ShoppingMain.jsp로 보내주기위해 데이터를new ModelAndView()객체에 담기
			mav.addObject("list",list);
		}
		//center화면으로 "SujakCenter.jsp"문자열 데이터를
		//아래의 ShoppingMain.jsp로 보내주기위해 new ModelAndView()객체에 담기
		mav.addObject("center","SujakCenter.jsp");
		
		//left화면으로... "SujakLeft.jsp"문자열 데이터를...
		//아래의 ShoppingMain.jsp로 보내주기 위해 new ModelAndView()객체에 담기
		mav.addObject("left","SujakLeft.jsp");
		
		//이동할 뷰페이지 -> ShoppingMain.jsp페이지명 셋팅
		mav.setViewName("ShoppingMain");
		
		//데이터와 MVC중 V페이지(jsp명)를 리턴해주는 객체 리턴
		
		return mav;
		
	}//suJak메소드 끝
	
	//SujakCenter.jsp페이지에서 이미지 하나를 클릭했을떄..(상세보기 요청이 들어왔을때...)
	//공구번호를 전달받아 공구 하나의 정보를 보여주는 메소드 실행하자!
	@RequestMapping("/suinfo.do")
	public ModelAndView suInfo(int suno){
		//상세볼 공구번호를전달하여 DB에서 검색한 하나의 공구 정보를 담고 있는 SuBean객체를 리턴 받는다.
		SuBean sbean = shoppingDao.getOneSutool(suno);
		
		//View(ShoppingMain.jsp) 쪽으로 데이터를 떠 넘겨줌
		ModelAndView mav = new ModelAndView();
		
		//하나의 공구 정보를 담고 있는 SuBean객체를..
		//아래의 ShoppingMain.jsp로 보내주기 위해 데이터를 new ModelAndView()객체에 담기
		mav.addObject("sbean",sbean);
		
		//center화면으로... "SuJakInfo.jsp"문자열 데이터를..
		//아래의 ShoppingMain.jsp로 보내주기위해 new ModelAndView()객체에 담기
		mav.addObject("center","SuJakInfo.jsp");
		
		//left화면으로.. "SujakLeft.jsp"문자열 데이터를..
		//아래의 ShoppingMain.jsp로 보내주기위해 new ModelAndView()객체에 담기
		mav.addObject("left","SujakLeft.jsp");
		
		//이동할 뷰페이지-> ShoppingMain.jsp페이지명 셋팅
		mav.setViewName("ShoppingMain");
		
		//데이터와 MVC중 V페이지(jsp명)를 리턴해주는 객체 리턴
		return mav;
	}//suInfo끝
	
	//SuJakInfo.jsp페이지에서... "카트에 담기 버튼"을 누르면 호출되는 메소드
	//"카트에 담기" 버튼을누르면... 전달받는 값들은 자동으로.. SuCartBean클래스(자바빈)에 맵핑되어 저장되어..
	//SuCartBean객체가... 아래의 메소드의 매개변수로 넘어온다.
	@RequestMapping("/sutoolcart.do")
	public ModelAndView sutoolCart(SuCartBean cartbean, HttpSession session){
		//먼저할일!! 카트에 담을 수작업공구정보를 저장하는 자바빈클래스인 SuCartBean만들자
		
		System.out.println(cartbean.getSuname());
		
		//기존의 Cart객체를 사용하기위해서 session에 저장되어 있는 Cart객체 꺼내오기!!
		//참고! Cart클래스안에 ArrayList객체가 담겨 있다.
		//참고! 처음에는 session객체안에 Cart객체가 없어서 null을 리턴 받을 것이다.
		Cart cart = (Cart) session.getAttribute("cart");
		
		//기존에 session에서 꺼내온 Cart객체가 없다면..
		if(cart == null){
			//Cart객체를 생성한 후에..
			cart = new Cart();
			//세션영역에 Cart객체 저장
			session.setAttribute("cart", cart);//내가 마트에서 나갈때까지 계속 이카틀르 세션영역에 유지
		}
		//넘어온 상품을 Cart객체에 추가
		cart.push(cartbean);
		
		//View(ShoppingMain.jsp)쪽으로 데이터를 떠넘겨 주기 위한 객체 생성
		ModelAndView mav = new ModelAndView();
		
		//하나의 카트에 담은 공구 정보를 담고있는??
		//위의 sutoolCart메소드의 매개변수로 받은 SuCatBean객체를..
		//아래의 ShoppingMain.jsp로 보내주기 위해...
		//카트에 저장된 상품 데이터를 new ModelAndView()객체에 담기
		mav.addObject("msg", cartbean.getSuname()+" 의 상품을 " + cartbean.getSuqty()
		+" 개 카트에 추가 했습니다.");
		mav.addObject("cart",cart);//cart객체에 저장되어 있는 ArrayList넘기기위해 cart 객체담기
		mav.addObject("center","SuCartResult.jsp");//카트 상품추가 내용 확인 페이지명 담기
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");//이동할 뷰페이지-> ShoppingMain.jsp페이지명 셋팅
		return mav;
	}//sutoolCart 메소드 끝
	
	//카트의 하나의 목록을 삭제하는 메소드
	@RequestMapping("/sucartdel.do")
	public ModelAndView sucartDel(int suno,HttpSession session){//삭제할 suno, 세션 전달받기
		
		//session영역에 저장되어 있는 카트 객체를 사용하기위하여 Cart객체를 불러옴
		Cart cart = (Cart) session.getAttribute("cart");
		//카트에 상품을 삭제
		cart.deleteCart(suno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cart",cart);
		mav.addObject("center","SuCartResult.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	
		
	}
	
	//Top.jsp에서 "회원가입"버튼을 클릭했을때... joinform.do요청이 들어오면...
	//회원가입 작성페이지로 이동하시오.
	@RequestMapping("joinform.do")
	public ModelAndView joinForm(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","JoinForm.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	}
	/*회원가입*/
	//회원의 정보를 담고 있는 MemberBean객체를 매개변수로 전달 받아..
	//MemberBean객체를 이용하여 데이터 베이스에 저장하는 메소드
	@RequestMapping("/joinproc")
	public ModelAndView joinProc(MemberBean mbean, HttpSession session){
		//데이터와 jsp를 리턴해주는 객체 생성
		ModelAndView mav = new ModelAndView();
		
		//데이터 베이스에 접근해서 해당 아이디가 존재 하는지와 패스워드가 맞는지를 비교를 위해..
		//getLogin()메소드의 인자로 MemberBean객체 전달 한다.
		//리턴 값으로 1=> 해당 아이디가 존재
		int result = shoppingDao.getLogin(mbean);
		
		//result값이 == 1이라면(해당 아이디가 존재 하면..)
		//JoinForm.jsp 페이지로 result값 1을 다시 리턴 해줘야 한다.
		if(result==1){
			mav.addObject("result","1");
			mav.addObject("center","JoinForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		//result값이 == 0 이라면 (해당 아이디가 존재 하지 않으면)	
		}else{
			//매개변수로 넘어온 mbean객체의 getter메소드를 사용하여...
			//비밀번호가 두개다 일치 할 경우에만 저장
			if(mbean.getMempasswd1().equals(mbean.getMempasswd2())){
				//DB에 회원가입 하기 메소드를 이용하여 회원 가입!!!
				shoppingDao.insertMember(mbean);
				//세션영역에 가입한 회원정보 저장
				session.setAttribute("mbean", mbean);
				//회원 세션 최대 유지 시간을 설정
				session.setMaxInactiveInterval(60*1);//30분을 의미
				//회원가입에 성공 했을때.. ShoppingMain.jsp메인 페이지 요청!
				return new ModelAndView(new RedirectView("index.do"));
				
				//매개변수로 넘어온 mbean객체의 getter메소드를 사용하여...
				//비밀번호가 두개다 일치 하지 않을 경우에..
				//JoinForm.jsp페이지로 result값 2을 다시 리턴 해줘야한다.
			}else{
				mav.addObject("result","2");
				mav.addObject("center","JoinForm.jsp");
				mav.addObject("left","SujakLeft.jsp");
				mav.setViewName("ShoppingMain");
			}
		}
		
		return mav;
	}//joinProc메소드 끝
	
	//로그인 페이지로 이동하는 메소드
	@RequestMapping("/login.do")
	public ModelAndView loGin(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","LoginForm.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	}
	
	//로그인 처리 메소드
	@RequestMapping("/Loginproc.do")
	public ModelAndView loginProc(HttpSession session, MemberBean mbean){
		//참고! 아이디, 패스워드는 따로따로 매개변수로 받아도 되지만..
		//MemberBean에 자동으로 저장되므로 MemberBean객체를 넘겨받음
		
		ModelAndView mav = new ModelAndView();
		//데이터 베이스에 접근하여 해당아이디와 패스워드가 있는지를 알아주는 메소드 호출후 결과를 저장
		int result = shoppingDao.getLoginProc(mbean);
		
		if(result ==1){//회원이 존재 한다면
			session.setAttribute("mbean", mbean); //세션값 저장
			session.setMaxInactiveInterval(60*30);
			return new ModelAndView(new RedirectView("index.do"));
		}else{//해당 아이디와 패스워드가 없다면 다시 LoginForm.jsp페이지로 이동하면서... 1전달
			mav.addObject("center","LoginForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
			mav.addObject("login","1");
			return mav;
		}
		
	}//loginProc메소드 끝
	//로그아웃 처리 메소드
	@RequestMapping("/logout.do")
	public ModelAndView logOut(HttpSession session){
		//회원 정보를 사용하기 위해서 세션에서 불러옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//회원 정보를 없애줌
		session.setAttribute("mbean", null);
		//session.invalidate();
		
		return new ModelAndView(new RedirectView("index.do"));
	}//끝
	
	//상품을 바로 구매하기
	@RequestMapping("/sutoolbuy.do")
	public ModelAndView sutoolBuy(HttpSession session, SuCartBean subean){
		//SuJakInfo.jsp(수작업 공구 구매 화면)에서...구매하기 버튼을 눌렀을때...
		//히든으로 넘길 값들을 SuCartBean클래스에 자동으로 저장하여..
		//위의 매개변수에서 있는 SuCartBean객체를 전달받는다.
		
		ModelAndView mav = new ModelAndView();
		
		//회원정보를 세션을 통하여 얻어옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		
		if(mbean == null){//로그인이 되어 있지 않은 경우
			mav.addObject("center","LoginForm.jsp");//로그인 폼으로 다시 이동
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
			
		}else{//로그인된 경우     먼저!
			mav.addObject("subean",subean);
			mav.addObject("center","SutoolBuy.jsp");//결제확인 화면(결제금액 뿌려준다.)
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}//끝
	
	//카트에 담겨진 모든 상품을 구매하기
	@RequestMapping("/sucartbuy.do")
	public ModelAndView sucartBuy(HttpSession session, SuCartBean subean){
		
		ModelAndView mav = new ModelAndView();
		//로그인한 회원정보를 세션을 통하여 얻어옴
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		
		if(mbean == null){//로그인이 되어 있지 않은 경우
			mav.addObject("center","LoginForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}else{//로그인된 경우
			//카트에 있는 내용을 계산하기 위해서 세션을 통하여 카트 객체를 가져옴
			Cart cart = (Cart) session.getAttribute("cart");
			mav.addObject("cart",cart);
			mav.addObject("center","SuCartBuy.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}//끝
	//카트에 내용 비우기
	@RequestMapping("/cartalldel.do")
	public ModelAndView cartallDel(HttpSession session){
		
		//세션에 있는 카트 객체를 얻어옴
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();//카트안에 있는 모든 상품을 삭제 해주는 메소드 호출하여.. 카트내용 비우기
		
		return new ModelAndView(new RedirectView("index.do"));
	}//끝
	
	//스탠리 소개
	@RequestMapping("/stanlyinfo.do")
	public ModelAndView stanlyInfo(String name){
		//스탠리 소개 메뉴의 하위 메뉴 카테고리 no값 받기
		//소개 <-- name = 0
		//연혁 <-- name = 1
		//글로벌네트워크 <-- name = 2
		//찾아 오시는길 <-- name = 3
		
		//소개, 연혁, 글로벌네트워크, 찾아오시는길 각각 하위 메뉴에 해당하는 이미지 이름 틀 배열에 저장
		String [] imgarr ={"stanlycenterinfo", "stanlycenterhistory1",
				"stanlycenterglobal","stanlycentercompany"};
		
		//연혁 메뉴 <-- 에 있는 이미지 이름 4개 배열에 저장
		String [] history = {"stanlycenterhistory1","stanlycenterhistory2",
				"stanlycenterhistory3","stanlycenterhistory4"};
		
		//스탠리 소개 메뉴의 하위메뉴 4개중 하나라도 선택하지 않았대면
		if(name==null){
			name="0";
		}
		//스탠리 소개 메뉴의 하위메뉴 4개중 연혁메뉴를 선택했다면
		if(name=="1"){
			ModelAndView mav = new ModelAndView();
			mav.addObject("name",name);//연혁메뉴 선택 키값 "1" 저장
			mav.addObject("history",history);//연혁메뉴 에 있는 4개의 이미지를 담고 있는 history배열 저장
			mav.addObject("center","StanlyInfoMain.jsp");//중앙화면
			mav.addObject("left","StanlyInfoLeft.jsp");//왼쪽화면
			mav.setViewName("ShoppingMain");
			return mav;
			//스탠리 소개 메뉴의 하위 메뉴 4개중 글로벌 네트워크 또는 찾아오시는길 메뉴중 하나를 선택했다면
		}else{//글로벌네트워크  선택 카테고리 name값은? "2"
				//찾아오시는길 선택 카테고리 name값은? "3"
			ModelAndView mav = new ModelAndView();
			mav.addObject("imgname",imgarr[Integer.parseInt(name)]);//name="2" 도는 "3"
			mav.addObject("center","StanlyInfoMain.jsp");
			mav.addObject("left","StanlyInfoLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;
			
		}
	}//stanlyInfo메소드 끝
	
	//공구 사용법
	@RequestMapping("tooluse.do")
	public ModelAndView toolUse(String name){
		//공구 사용법 메뉴의 하위 메뉴 카테고리 no값(name값) 받기
		//제품 안전사항 <== name = 0
		//줄자 <== name = 1
		//펀치 <== name = 2
		//블레이드 나이프 <== name = 3
		//칼 <== name = 4
		//망치 <== name = 5
		//대패 <== name = 6
		//드라이버 <== name = 7
		//정 <== name = 8
		//함석가위 <== name = 9

		
		//배열에 이미지의 이름을 미리 담아 놓도록
		String [] imgarr = {"tool1","tool2","tool3","tool4","tool5",
				"tool6","tool7","tool8-1","tool9","tool10",};
		
		//공구 사용법 메뉴의 하위 메뉴 카테고리를 선택하지 않았을때...
		if(name==null){
			name = "0";
		}
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("imgname", imgarr[Integer.parseInt(name)]);
		mav.addObject("center","ToolUseCenter.jsp");
		mav.addObject("left","ToolUseLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	}//end
	
	//Top.jsp페이지에서 다운로드 메뉴 클릭시 실행되는 메소드
	@RequestMapping("/download.do")
	public ModelAndView downLoad(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","DownCenter.jsp"); //다운로드 할 페이지 중앙화면
		mav.addObject("left","DownLeft.jsp");//왼쪽 화면
		mav.setViewName("ShoppingMain");
		return mav;
	}//End
	
	//DownCenter.jsp에서.. 다운로드 버튼을 클릭해야... downfile.do라는 요청이 들어오면..
	//실제 서버에서 클라이언트로 다운이 되게 메소드
	@RequestMapping("/downfile.do")
	public ModelAndView downFile(int no){//밑의 filename배열의 인덱스 번지 받아오기
		//DB에 있는 다운로드할 파일명 대신에..
		//다운로드할 파일명 을 직접 배열에 담기
		String [] filename = {"m0.pdf","m1.pdf","m2.pdf"};
		
		//다운로드할 파일이 있는 서버경로 설정
		String path="D:/Car_workspace/ShoppingMall/WebContent/downfile/";
		
		//다운로드할 파일에 접근하기 위한 객체 생성
		File downloadfile = new File(path + filename[no] );
		
		//다운로드할 파일 File downloadfile객체를?...
		//다운로드 작업할 Controller.DownloadView.java에 전달하기위해..
		//ModelAndView객체에 저장하여... //shopping-servlet.xml에 리턴한다.
		return new ModelAndView("downloadView", "downloadFile", downloadfile);
		
	}//끝
	
	//전체 게시글을 리턴하는 메소드
		@RequestMapping("/board.do")
		public ModelAndView boardList(String pageNum){//현재 보여지는 페이지 넘버값 받기 
			
			ModelAndView mav = new ModelAndView();
			//화면에 보여질 게시글의갯수를 지정
			int pageSize=10;
			
			int count =0;//전체 글의 갯수
			int number =0;//페이지 넘버링수(현재 화면에 보고있는 페이지 넘버 값)
			
			//처음 게시글 보기를 누르면 pageNum없기에 null처리해주어야합니다.
			if(pageNum == null){
				pageNum="1";
			}
			//현재 보여지는 페이지 넘버값
			int currentPage  = Integer.parseInt(pageNum);
			//게시글의 총 갯수 얻기
			count = shoppingDao.getCount();
		
			//현제 페이지에 보여줄 시작 번호를 설정 = 데이터 베이스에서 불러올 시작 번호를 의미
			int startRow = (currentPage -1)*pageSize+1;
			int endRow = currentPage*pageSize;
			//가변길으로 데이터를 저장
			List<BoardBean> vbean=null;
			
			//게시글이 존재한다면
			if(count > 0 ){
				//10개를 기준으로 데이터를 데이터 베이스에서 읽어드림
				vbean = shoppingDao.getAllContent(startRow-1 , endRow);		
				//테이블에 표시할 번호를 설정
				number = count -(currentPage -1) * pageSize;
				
			}
			//BoardList.jsp
			mav.addObject("vbean", vbean);
			mav.addObject("number", number);
			mav.addObject("pageSize", pageSize);
			mav.addObject("count", count);
			mav.addObject("currentPage", currentPage);
			mav.addObject("center", "BoardList.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
			
		}
		
		//게시글쓰기 폼 페이지 이동
		@RequestMapping("/boardwrite.do")
		public ModelAndView boardWrite(){
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("center", "BoardWrite.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//게시글 데이터 베이스에 저장
		@RequestMapping("/boardwriteproc.do")
		public ModelAndView boardWriteProc(BoardBean bean){
				
			//데이터베이스로 빈클래스를 떠넘겨줌
			shoppingDao.boardInsert(bean);
			
			return new ModelAndView(new RedirectView("board.do"));
		}
	 	
		//게시글 보기
		@RequestMapping("/boardinfo.do")
		public ModelAndView boardInfo(int num){
			
			ModelAndView mav = new ModelAndView();
			//하나의 게시글을 리턴하는 메소드 호출
			BoardBean bean = shoppingDao.getOneContent(num);
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardInfo.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
			
		}
		
		//답글쓰기 폼 
		@RequestMapping("/boardrewrite.do")
		public ModelAndView boardRewrite(BoardBean bean){
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardRewrite.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//답글쓰기 처리
		@RequestMapping("/boardrewriteproc.do")
		public ModelAndView boardrewriteProc(BoardBean bean){
			
			//데이터 베이스에 데이터를 저장
			shoppingDao.reWriteboard(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		//게시글 수정하기
		@RequestMapping("/boardupdate.do")
		public ModelAndView boardUpdate(BoardBean bean){
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardUpdate.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//게시글 수정 완료메소드
		@RequestMapping("/boardupdateproc.do")
		public ModelAndView boardupdateProc(BoardBean bean){
			
			shoppingDao.boardUpdate(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		//게시글 삭제 하기
		@RequestMapping("/boarddelete.do")
		public ModelAndView boardDelete(int num){
		
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("num", num);
			mav.addObject("center", "BoardDelete.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;
		
		}
		//게시글 삭제 하기
		@RequestMapping("/boarddeleteproc.do")
		public ModelAndView boarddeleteProc(BoardBean bean){
		
			shoppingDao.boardDelete(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		
		

	}//ShoppingController 클래스 끝
