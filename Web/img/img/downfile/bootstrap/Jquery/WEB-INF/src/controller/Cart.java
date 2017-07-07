package controller;

import java.util.ArrayList;
import java.util.List;

import model.SuCartBean;

//상품에 내용을 ArrayList에 저장하고 또는 기존에 상품이 있다면 수량만 증가하는 클래스, 삭제기능과 모든 카트내용삭제
public class Cart {//수작업공구 뿐만 아니라 전동공구에 동시에 사용할수 있기 때문에... 일부러 하나의 Cart클래스를 만듬
	
	//여러개의 SuCarBean상품(수작업공구상품,전동공구상품)을 저장하기 위한 ArrayList객체(카트객체) 생성
	//참고! 이ArrayList객체(cart객체)는 나중에... session영역에 저장 한다.
	private List<SuCartBean> itemlist = new ArrayList<SuCartBean>();
	
	//ArrayList객체(카트객체)객체를 리턴하는 메소드
	
	public List<SuCartBean> getItemlist(){
		return itemlist;
	}
	
	//메소드 설명!!
	//SuJakInfo.jsp페이지에서.. "카트담기" 버튼을 눌렀을때..
	//ShoppingController.java의 sutoolCart(SuCartBean cartbean, HttpSession session)메소드의...
	//인자로 전달받은 cartbean객체를 다시!! 아래의 push(SuCartBean cartbean)메소드의 인자로 전달받는다.
	//cartbean객체를 인자로 전달받아.. ArrayList객체(카트객체)객체에 상품을 추가하는 메소드인데..
	//하는일 :
	//기존에 상품이 있다면 수량만 증가하고 기존에 상품이 있는지 알아내는 변수 값을 true로 변경해 주고...
	//기존에 상품이 없는 경우에는 상품을 추가 시켜주는 일을 하는 메소드
	public void push(SuCartBean cartbean){
		//기존에 상품이 있는지를 알아내는 변수
		boolean alreadysutool = false;
		//itemlist안에 데이터를 반복문을 돌면서 기존에 있는 데이터가 있는지 비교를
		for(SuCartBean suCartBean : itemlist){//확장 for문 ArrayList에 cartbean객체가 있는 만큼 반복
			//ArrayList(카트, 장바구니)에 담겨 있는 수작업 공구 번호와..
			//push()메소드의 매개변수로 넘어온 cartbean객체(카트에 담을 수작업 공구 상품 정보객체)의
			//공구번호가 같을때...
			if(cartbean.getSuno() == suCartBean.getSuno()){
				//동일한 상품이 존재 하기에 수량만 증가
				suCartBean.setSuqty(suCartBean.getSuqty()+cartbean.getSuqty());
				//기존에 상품이 있을때.. true로
				alreadysutool = true;
				break;
			}
			
		}//for문 끝
		
		//기존에 상품이 없는 경우에는 상품을 추가 시켜주어야 합니다.
		if(alreadysutool==false){
			//ArrayList객체(카트객체)에 상품 추가
			itemlist.add(cartbean);
		}
	}//push메소드 끝
	
	//카트의 하나의 상품을 삭제해주는 메소드
	public void deleteCart(int suno) {
		//반복문을 돌면서 해당 공구의 번호를 기준으로 삭제
		for(SuCartBean suCartBean : itemlist){
			//삭제하고자 하는 번호가 있다면 itemlist 에서 해당 상품을 삭제
			if(suCartBean.getSuno() == suno){
				itemlist.remove(suCartBean);
				break;
			}
		}
		
	}
	
	//카트의 모든 상품을 삭제 해주는 메소드
	//카트 비우기
	public void clearCart() {
		itemlist.removeAll(itemlist);
		
	}

}//cart클래스 끝
