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
	
	//���� :
	//shopping-servlet.xml�� �������踦 �̿��Ͽ�... <bean>��ü��...
	//<bean id="sujak" name="/sujak.do" class="controller.ShoppingController"
	// 			p:shoppingDao-ref="shoppingDao"></bean> ��� �س��ұ� ������...
	//shoppingDao��ü�� �����ü� �ִ�.
	ShoppingDAO shoppingDao;//DB�� ������ DB�۾��Ұ�ü �����ͼ� ������ ���� ����
	
	//���� ��� //DB�� ������ DB�۾��Ұ�ü �����ͼ� ����
	public void setShoppingDao(ShoppingDAO shoppingDao){
		this.shoppingDao = shoppingDao;
	}
	
	@RequestMapping("/index.do")//index.do��� ��û�� ������ �Ʒ��� �޼ҵ带 ����
	public ModelAndView index(HttpSession session){//ȸ�� ���� ������ ����ϱ����Ͽ� ������ ����
		
		//�����Ϳ� jsp�� �������ִ� ��ü ����
		ModelAndView mav = new ModelAndView();
		
		//ȸ�������� ����ϱ� ���Ͽ� ���� ��ü�� �ҷ���
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//������ �̿��Ͽ� �α��� ó��
		if(mbean==null){//top.jsp���� �α��� ������ ó���ϱ� ���� �ҽ�
			mav.addObject("mbean",null); //�����͸� �Ʒ��� ShoppingMain.jsp�� �����ֱ����� ������ ���
			mav.setViewName("ShoppingMain");//ShoppingMain.jsp�������� ����
			
		}else{
			mav.addObject("mbean",mbean);//�����͸� �Ʒ��� ShoppingMain.jsp�� �����ֱ� ���� ������ ���
			mav.setViewName("ShoppingMain");//ShoppingMain.jsp �������� ����
		}
		
		return mav;
		
		//���۾� ������ �����ٸ� �ڵ����� ȣ��Ǵ� �޼ҵ�
		
	}
	
	@RequestMapping("/sujak.do")
	public ModelAndView suJak(String num){
		
		//������ ��ü ����
		ModelAndView mav = new ModelAndView();
		//��� ��û�� ���Դ����� ���� ���������ͼ� �ش� model�� �����Ͽ� �����͸� ����������
		if(num==null){//����ǰ�� �����ų� top�� �ִ� �޴��� ���۾� ������ư�� ������
			
			//��������!!
			//DB�� �����Ͽ�.. �˻��� ��� ���۾� ������ �����;��Ѵ�.
			//�׷��⿡ �ռ� DtoŬ������������!
			
			//DB�� �����Ͽ�.. �˻��� ��� ���۾� ������(SuBean)�� List�� ��Ƽ� ��������
			List<SuBean> list = shoppingDao.getAllSutool();
			
			//�����͸� �Ʒ��� ShoppingMain.jsp�� �����ֱ� ���� �����͸� new ModelAndView()��ü�� ���
			mav.addObject("list",list);
		}else{//�ش�޴����� ���ý� ���Ǵ� �ҽ�
			//���� ���۾� ���� ī�װ��� �޴��� �ϳ��� ������ ���� �����Ͽ� ...
			//DB���� �˻��� ���۾�������(SuBean��)�� List�� ��Ƽ� ��������
			List<SuBean> list = shoppingDao.getSelectSutool(num);
			
			//�����͸� �Ʒ��� ShoppingMain.jsp�� �����ֱ����� �����͸�new ModelAndView()��ü�� ���
			mav.addObject("list",list);
		}
		//centerȭ������ "SujakCenter.jsp"���ڿ� �����͸�
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ����� new ModelAndView()��ü�� ���
		mav.addObject("center","SujakCenter.jsp");
		
		//leftȭ������... "SujakLeft.jsp"���ڿ� �����͸�...
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ� ���� new ModelAndView()��ü�� ���
		mav.addObject("left","SujakLeft.jsp");
		
		//�̵��� �������� -> ShoppingMain.jsp�������� ����
		mav.setViewName("ShoppingMain");
		
		//�����Ϳ� MVC�� V������(jsp��)�� �������ִ� ��ü ����
		
		return mav;
		
	}//suJak�޼ҵ� ��
	
	//SujakCenter.jsp���������� �̹��� �ϳ��� Ŭ��������..(�󼼺��� ��û�� ��������...)
	//������ȣ�� ���޹޾� ���� �ϳ��� ������ �����ִ� �޼ҵ� ��������!
	@RequestMapping("/suinfo.do")
	public ModelAndView suInfo(int suno){
		//�󼼺� ������ȣ�������Ͽ� DB���� �˻��� �ϳ��� ���� ������ ��� �ִ� SuBean��ü�� ���� �޴´�.
		SuBean sbean = shoppingDao.getOneSutool(suno);
		
		//View(ShoppingMain.jsp) ������ �����͸� �� �Ѱ���
		ModelAndView mav = new ModelAndView();
		
		//�ϳ��� ���� ������ ��� �ִ� SuBean��ü��..
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ� ���� �����͸� new ModelAndView()��ü�� ���
		mav.addObject("sbean",sbean);
		
		//centerȭ������... "SuJakInfo.jsp"���ڿ� �����͸�..
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ����� new ModelAndView()��ü�� ���
		mav.addObject("center","SuJakInfo.jsp");
		
		//leftȭ������.. "SujakLeft.jsp"���ڿ� �����͸�..
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ����� new ModelAndView()��ü�� ���
		mav.addObject("left","SujakLeft.jsp");
		
		//�̵��� ��������-> ShoppingMain.jsp�������� ����
		mav.setViewName("ShoppingMain");
		
		//�����Ϳ� MVC�� V������(jsp��)�� �������ִ� ��ü ����
		return mav;
	}//suInfo��
	
	//SuJakInfo.jsp����������... "īƮ�� ��� ��ư"�� ������ ȣ��Ǵ� �޼ҵ�
	//"īƮ�� ���" ��ư��������... ���޹޴� ������ �ڵ�����.. SuCartBeanŬ����(�ڹٺ�)�� ���εǾ� ����Ǿ�..
	//SuCartBean��ü��... �Ʒ��� �޼ҵ��� �Ű������� �Ѿ�´�.
	@RequestMapping("/sutoolcart.do")
	public ModelAndView sutoolCart(SuCartBean cartbean, HttpSession session){
		//��������!! īƮ�� ���� ���۾����������� �����ϴ� �ڹٺ�Ŭ������ SuCartBean������
		
		System.out.println(cartbean.getSuname());
		
		//������ Cart��ü�� ����ϱ����ؼ� session�� ����Ǿ� �ִ� Cart��ü ��������!!
		//����! CartŬ�����ȿ� ArrayList��ü�� ��� �ִ�.
		//����! ó������ session��ü�ȿ� Cart��ü�� ��� null�� ���� ���� ���̴�.
		Cart cart = (Cart) session.getAttribute("cart");
		
		//������ session���� ������ Cart��ü�� ���ٸ�..
		if(cart == null){
			//Cart��ü�� ������ �Ŀ�..
			cart = new Cart();
			//���ǿ����� Cart��ü ����
			session.setAttribute("cart", cart);//���� ��Ʈ���� ���������� ��� ��īƲ�� ���ǿ����� ����
		}
		//�Ѿ�� ��ǰ�� Cart��ü�� �߰�
		cart.push(cartbean);
		
		//View(ShoppingMain.jsp)������ �����͸� ���Ѱ� �ֱ� ���� ��ü ����
		ModelAndView mav = new ModelAndView();
		
		//�ϳ��� īƮ�� ���� ���� ������ ����ִ�??
		//���� sutoolCart�޼ҵ��� �Ű������� ���� SuCatBean��ü��..
		//�Ʒ��� ShoppingMain.jsp�� �����ֱ� ����...
		//īƮ�� ����� ��ǰ �����͸� new ModelAndView()��ü�� ���
		mav.addObject("msg", cartbean.getSuname()+" �� ��ǰ�� " + cartbean.getSuqty()
		+" �� īƮ�� �߰� �߽��ϴ�.");
		mav.addObject("cart",cart);//cart��ü�� ����Ǿ� �ִ� ArrayList�ѱ������ cart ��ü���
		mav.addObject("center","SuCartResult.jsp");//īƮ ��ǰ�߰� ���� Ȯ�� �������� ���
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");//�̵��� ��������-> ShoppingMain.jsp�������� ����
		return mav;
	}//sutoolCart �޼ҵ� ��
	
	//īƮ�� �ϳ��� ����� �����ϴ� �޼ҵ�
	@RequestMapping("/sucartdel.do")
	public ModelAndView sucartDel(int suno,HttpSession session){//������ suno, ���� ���޹ޱ�
		
		//session������ ����Ǿ� �ִ� īƮ ��ü�� ����ϱ����Ͽ� Cart��ü�� �ҷ���
		Cart cart = (Cart) session.getAttribute("cart");
		//īƮ�� ��ǰ�� ����
		cart.deleteCart(suno);
		ModelAndView mav = new ModelAndView();
		mav.addObject("cart",cart);
		mav.addObject("center","SuCartResult.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	
		
	}
	
	//Top.jsp���� "ȸ������"��ư�� Ŭ��������... joinform.do��û�� ������...
	//ȸ������ �ۼ��������� �̵��Ͻÿ�.
	@RequestMapping("joinform.do")
	public ModelAndView joinForm(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","JoinForm.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	}
	/*ȸ������*/
	//ȸ���� ������ ��� �ִ� MemberBean��ü�� �Ű������� ���� �޾�..
	//MemberBean��ü�� �̿��Ͽ� ������ ���̽��� �����ϴ� �޼ҵ�
	@RequestMapping("/joinproc")
	public ModelAndView joinProc(MemberBean mbean, HttpSession session){
		//�����Ϳ� jsp�� �������ִ� ��ü ����
		ModelAndView mav = new ModelAndView();
		
		//������ ���̽��� �����ؼ� �ش� ���̵� ���� �ϴ����� �н����尡 �´����� �񱳸� ����..
		//getLogin()�޼ҵ��� ���ڷ� MemberBean��ü ���� �Ѵ�.
		//���� ������ 1=> �ش� ���̵� ����
		int result = shoppingDao.getLogin(mbean);
		
		//result���� == 1�̶��(�ش� ���̵� ���� �ϸ�..)
		//JoinForm.jsp �������� result�� 1�� �ٽ� ���� ����� �Ѵ�.
		if(result==1){
			mav.addObject("result","1");
			mav.addObject("center","JoinForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		//result���� == 0 �̶�� (�ش� ���̵� ���� ���� ������)	
		}else{
			//�Ű������� �Ѿ�� mbean��ü�� getter�޼ҵ带 ����Ͽ�...
			//��й�ȣ�� �ΰ��� ��ġ �� ��쿡�� ����
			if(mbean.getMempasswd1().equals(mbean.getMempasswd2())){
				//DB�� ȸ������ �ϱ� �޼ҵ带 �̿��Ͽ� ȸ�� ����!!!
				shoppingDao.insertMember(mbean);
				//���ǿ����� ������ ȸ������ ����
				session.setAttribute("mbean", mbean);
				//ȸ�� ���� �ִ� ���� �ð��� ����
				session.setMaxInactiveInterval(60*1);//30���� �ǹ�
				//ȸ�����Կ� ���� ������.. ShoppingMain.jsp���� ������ ��û!
				return new ModelAndView(new RedirectView("index.do"));
				
				//�Ű������� �Ѿ�� mbean��ü�� getter�޼ҵ带 ����Ͽ�...
				//��й�ȣ�� �ΰ��� ��ġ ���� ���� ��쿡..
				//JoinForm.jsp�������� result�� 2�� �ٽ� ���� ������Ѵ�.
			}else{
				mav.addObject("result","2");
				mav.addObject("center","JoinForm.jsp");
				mav.addObject("left","SujakLeft.jsp");
				mav.setViewName("ShoppingMain");
			}
		}
		
		return mav;
	}//joinProc�޼ҵ� ��
	
	//�α��� �������� �̵��ϴ� �޼ҵ�
	@RequestMapping("/login.do")
	public ModelAndView loGin(){
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","LoginForm.jsp");
		mav.addObject("left","SujakLeft.jsp");
		mav.setViewName("ShoppingMain");
		return mav;
	}
	
	//�α��� ó�� �޼ҵ�
	@RequestMapping("/Loginproc.do")
	public ModelAndView loginProc(HttpSession session, MemberBean mbean){
		//����! ���̵�, �н������ ���ε��� �Ű������� �޾Ƶ� ������..
		//MemberBean�� �ڵ����� ����ǹǷ� MemberBean��ü�� �Ѱܹ���
		
		ModelAndView mav = new ModelAndView();
		//������ ���̽��� �����Ͽ� �ش���̵�� �н����尡 �ִ����� �˾��ִ� �޼ҵ� ȣ���� ����� ����
		int result = shoppingDao.getLoginProc(mbean);
		
		if(result ==1){//ȸ���� ���� �Ѵٸ�
			session.setAttribute("mbean", mbean); //���ǰ� ����
			session.setMaxInactiveInterval(60*30);
			return new ModelAndView(new RedirectView("index.do"));
		}else{//�ش� ���̵�� �н����尡 ���ٸ� �ٽ� LoginForm.jsp�������� �̵��ϸ鼭... 1����
			mav.addObject("center","LoginForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
			mav.addObject("login","1");
			return mav;
		}
		
	}//loginProc�޼ҵ� ��
	//�α׾ƿ� ó�� �޼ҵ�
	@RequestMapping("/logout.do")
	public ModelAndView logOut(HttpSession session){
		//ȸ�� ������ ����ϱ� ���ؼ� ���ǿ��� �ҷ���
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		//ȸ�� ������ ������
		session.setAttribute("mbean", null);
		//session.invalidate();
		
		return new ModelAndView(new RedirectView("index.do"));
	}//��
	
	//��ǰ�� �ٷ� �����ϱ�
	@RequestMapping("/sutoolbuy.do")
	public ModelAndView sutoolBuy(HttpSession session, SuCartBean subean){
		//SuJakInfo.jsp(���۾� ���� ���� ȭ��)����...�����ϱ� ��ư�� ��������...
		//�������� �ѱ� ������ SuCartBeanŬ������ �ڵ����� �����Ͽ�..
		//���� �Ű��������� �ִ� SuCartBean��ü�� ���޹޴´�.
		
		ModelAndView mav = new ModelAndView();
		
		//ȸ�������� ������ ���Ͽ� ����
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		
		if(mbean == null){//�α����� �Ǿ� ���� ���� ���
			mav.addObject("center","LoginForm.jsp");//�α��� ������ �ٽ� �̵�
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
			
		}else{//�α��ε� ���     ����!
			mav.addObject("subean",subean);
			mav.addObject("center","SutoolBuy.jsp");//����Ȯ�� ȭ��(�����ݾ� �ѷ��ش�.)
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}//��
	
	//īƮ�� ����� ��� ��ǰ�� �����ϱ�
	@RequestMapping("/sucartbuy.do")
	public ModelAndView sucartBuy(HttpSession session, SuCartBean subean){
		
		ModelAndView mav = new ModelAndView();
		//�α����� ȸ�������� ������ ���Ͽ� ����
		MemberBean mbean = (MemberBean) session.getAttribute("mbean");
		
		if(mbean == null){//�α����� �Ǿ� ���� ���� ���
			mav.addObject("center","LoginForm.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}else{//�α��ε� ���
			//īƮ�� �ִ� ������ ����ϱ� ���ؼ� ������ ���Ͽ� īƮ ��ü�� ������
			Cart cart = (Cart) session.getAttribute("cart");
			mav.addObject("cart",cart);
			mav.addObject("center","SuCartBuy.jsp");
			mav.addObject("left","SujakLeft.jsp");
			mav.setViewName("ShoppingMain");
		}
		return mav;
	}//��
	//īƮ�� ���� ����
	@RequestMapping("/cartalldel.do")
	public ModelAndView cartallDel(HttpSession session){
		
		//���ǿ� �ִ� īƮ ��ü�� ����
		Cart cart = (Cart) session.getAttribute("cart");
		cart.clearCart();//īƮ�ȿ� �ִ� ��� ��ǰ�� ���� ���ִ� �޼ҵ� ȣ���Ͽ�.. īƮ���� ����
		
		return new ModelAndView(new RedirectView("index.do"));
	}//��
	
	//���ĸ� �Ұ�
	@RequestMapping("/stanlyinfo.do")
	public ModelAndView stanlyInfo(String name){
		//���ĸ� �Ұ� �޴��� ���� �޴� ī�װ� no�� �ޱ�
		//�Ұ� <-- name = 0
		//���� <-- name = 1
		//�۷ι���Ʈ��ũ <-- name = 2
		//ã�� ���ô±� <-- name = 3
		
		//�Ұ�, ����, �۷ι���Ʈ��ũ, ã�ƿ��ô±� ���� ���� �޴��� �ش��ϴ� �̹��� �̸� Ʋ �迭�� ����
		String [] imgarr ={"stanlycenterinfo", "stanlycenterhistory1",
				"stanlycenterglobal","stanlycentercompany"};
		
		//���� �޴� <-- �� �ִ� �̹��� �̸� 4�� �迭�� ����
		String [] history = {"stanlycenterhistory1","stanlycenterhistory2",
				"stanlycenterhistory3","stanlycenterhistory4"};
		
		//���ĸ� �Ұ� �޴��� �����޴� 4���� �ϳ��� �������� �ʾҴ��
		if(name==null){
			name="0";
		}
		//���ĸ� �Ұ� �޴��� �����޴� 4���� �����޴��� �����ߴٸ�
		if(name=="1"){
			ModelAndView mav = new ModelAndView();
			mav.addObject("name",name);//�����޴� ���� Ű�� "1" ����
			mav.addObject("history",history);//�����޴� �� �ִ� 4���� �̹����� ��� �ִ� history�迭 ����
			mav.addObject("center","StanlyInfoMain.jsp");//�߾�ȭ��
			mav.addObject("left","StanlyInfoLeft.jsp");//����ȭ��
			mav.setViewName("ShoppingMain");
			return mav;
			//���ĸ� �Ұ� �޴��� ���� �޴� 4���� �۷ι� ��Ʈ��ũ �Ǵ� ã�ƿ��ô±� �޴��� �ϳ��� �����ߴٸ�
		}else{//�۷ι���Ʈ��ũ  ���� ī�װ� name����? "2"
				//ã�ƿ��ô±� ���� ī�װ� name����? "3"
			ModelAndView mav = new ModelAndView();
			mav.addObject("imgname",imgarr[Integer.parseInt(name)]);//name="2" ���� "3"
			mav.addObject("center","StanlyInfoMain.jsp");
			mav.addObject("left","StanlyInfoLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;
			
		}
	}//stanlyInfo�޼ҵ� ��
	
	//���� ����
	@RequestMapping("tooluse.do")
	public ModelAndView toolUse(String name){
		//���� ���� �޴��� ���� �޴� ī�װ� no��(name��) �ޱ�
		//��ǰ �������� <== name = 0
		//���� <== name = 1
		//��ġ <== name = 2
		//���̵� ������ <== name = 3
		//Į <== name = 4
		//��ġ <== name = 5
		//���� <== name = 6
		//����̹� <== name = 7
		//�� <== name = 8
		//�Լ����� <== name = 9

		
		//�迭�� �̹����� �̸��� �̸� ��� ������
		String [] imgarr = {"tool1","tool2","tool3","tool4","tool5",
				"tool6","tool7","tool8-1","tool9","tool10",};
		
		//���� ���� �޴��� ���� �޴� ī�װ��� �������� �ʾ�����...
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
	
	//Top.jsp���������� �ٿ�ε� �޴� Ŭ���� ����Ǵ� �޼ҵ�
	@RequestMapping("/download.do")
	public ModelAndView downLoad(){
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("center","DownCenter.jsp"); //�ٿ�ε� �� ������ �߾�ȭ��
		mav.addObject("left","DownLeft.jsp");//���� ȭ��
		mav.setViewName("ShoppingMain");
		return mav;
	}//End
	
	//DownCenter.jsp����.. �ٿ�ε� ��ư�� Ŭ���ؾ�... downfile.do��� ��û�� ������..
	//���� �������� Ŭ���̾�Ʈ�� �ٿ��� �ǰ� �޼ҵ�
	@RequestMapping("/downfile.do")
	public ModelAndView downFile(int no){//���� filename�迭�� �ε��� ���� �޾ƿ���
		//DB�� �ִ� �ٿ�ε��� ���ϸ� ��ſ�..
		//�ٿ�ε��� ���ϸ� �� ���� �迭�� ���
		String [] filename = {"m0.pdf","m1.pdf","m2.pdf"};
		
		//�ٿ�ε��� ������ �ִ� ������� ����
		String path="D:/Car_workspace/ShoppingMall/WebContent/downfile/";
		
		//�ٿ�ε��� ���Ͽ� �����ϱ� ���� ��ü ����
		File downloadfile = new File(path + filename[no] );
		
		//�ٿ�ε��� ���� File downloadfile��ü��?...
		//�ٿ�ε� �۾��� Controller.DownloadView.java�� �����ϱ�����..
		//ModelAndView��ü�� �����Ͽ�... //shopping-servlet.xml�� �����Ѵ�.
		return new ModelAndView("downloadView", "downloadFile", downloadfile);
		
	}//��
	
	//��ü �Խñ��� �����ϴ� �޼ҵ�
		@RequestMapping("/board.do")
		public ModelAndView boardList(String pageNum){//���� �������� ������ �ѹ��� �ޱ� 
			
			ModelAndView mav = new ModelAndView();
			//ȭ�鿡 ������ �Խñ��ǰ����� ����
			int pageSize=10;
			
			int count =0;//��ü ���� ����
			int number =0;//������ �ѹ�����(���� ȭ�鿡 �����ִ� ������ �ѹ� ��)
			
			//ó�� �Խñ� ���⸦ ������ pageNum���⿡ nulló�����־���մϴ�.
			if(pageNum == null){
				pageNum="1";
			}
			//���� �������� ������ �ѹ���
			int currentPage  = Integer.parseInt(pageNum);
			//�Խñ��� �� ���� ���
			count = shoppingDao.getCount();
		
			//���� �������� ������ ���� ��ȣ�� ���� = ������ ���̽����� �ҷ��� ���� ��ȣ�� �ǹ�
			int startRow = (currentPage -1)*pageSize+1;
			int endRow = currentPage*pageSize;
			//���������� �����͸� ����
			List<BoardBean> vbean=null;
			
			//�Խñ��� �����Ѵٸ�
			if(count > 0 ){
				//10���� �������� �����͸� ������ ���̽����� �о�帲
				vbean = shoppingDao.getAllContent(startRow-1 , endRow);		
				//���̺� ǥ���� ��ȣ�� ����
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
		
		//�Խñ۾��� �� ������ �̵�
		@RequestMapping("/boardwrite.do")
		public ModelAndView boardWrite(){
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("center", "BoardWrite.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//�Խñ� ������ ���̽��� ����
		@RequestMapping("/boardwriteproc.do")
		public ModelAndView boardWriteProc(BoardBean bean){
				
			//�����ͺ��̽��� ��Ŭ������ ���Ѱ���
			shoppingDao.boardInsert(bean);
			
			return new ModelAndView(new RedirectView("board.do"));
		}
	 	
		//�Խñ� ����
		@RequestMapping("/boardinfo.do")
		public ModelAndView boardInfo(int num){
			
			ModelAndView mav = new ModelAndView();
			//�ϳ��� �Խñ��� �����ϴ� �޼ҵ� ȣ��
			BoardBean bean = shoppingDao.getOneContent(num);
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardInfo.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
			
		}
		
		//��۾��� �� 
		@RequestMapping("/boardrewrite.do")
		public ModelAndView boardRewrite(BoardBean bean){
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardRewrite.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//��۾��� ó��
		@RequestMapping("/boardrewriteproc.do")
		public ModelAndView boardrewriteProc(BoardBean bean){
			
			//������ ���̽��� �����͸� ����
			shoppingDao.reWriteboard(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		//�Խñ� �����ϱ�
		@RequestMapping("/boardupdate.do")
		public ModelAndView boardUpdate(BoardBean bean){
			
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("bean", bean);
			mav.addObject("center", "BoardUpdate.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;	
		}
		
		//�Խñ� ���� �Ϸ�޼ҵ�
		@RequestMapping("/boardupdateproc.do")
		public ModelAndView boardupdateProc(BoardBean bean){
			
			shoppingDao.boardUpdate(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		//�Խñ� ���� �ϱ�
		@RequestMapping("/boarddelete.do")
		public ModelAndView boardDelete(int num){
		
			ModelAndView mav = new ModelAndView();
			
			mav.addObject("num", num);
			mav.addObject("center", "BoardDelete.jsp");
			mav.addObject("left", "BoardLeft.jsp");
			mav.setViewName("ShoppingMain");
			return mav;
		
		}
		//�Խñ� ���� �ϱ�
		@RequestMapping("/boarddeleteproc.do")
		public ModelAndView boarddeleteProc(BoardBean bean){
		
			shoppingDao.boardDelete(bean);
			return new ModelAndView(new RedirectView("board.do"));
		}
		
		
		

	}//ShoppingController Ŭ���� ��
