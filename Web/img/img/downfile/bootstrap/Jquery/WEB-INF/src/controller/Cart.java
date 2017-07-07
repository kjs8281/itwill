package controller;

import java.util.ArrayList;
import java.util.List;

import model.SuCartBean;

//��ǰ�� ������ ArrayList�� �����ϰ� �Ǵ� ������ ��ǰ�� �ִٸ� ������ �����ϴ� Ŭ����, ������ɰ� ��� īƮ�������
public class Cart {//���۾����� �Ӹ� �ƴ϶� ���������� ���ÿ� ����Ҽ� �ֱ� ������... �Ϻη� �ϳ��� CartŬ������ ����
	
	//�������� SuCarBean��ǰ(���۾�������ǰ,����������ǰ)�� �����ϱ� ���� ArrayList��ü(īƮ��ü) ����
	//����! ��ArrayList��ü(cart��ü)�� ���߿�... session������ ���� �Ѵ�.
	private List<SuCartBean> itemlist = new ArrayList<SuCartBean>();
	
	//ArrayList��ü(īƮ��ü)��ü�� �����ϴ� �޼ҵ�
	
	public List<SuCartBean> getItemlist(){
		return itemlist;
	}
	
	//�޼ҵ� ����!!
	//SuJakInfo.jsp����������.. "īƮ���" ��ư�� ��������..
	//ShoppingController.java�� sutoolCart(SuCartBean cartbean, HttpSession session)�޼ҵ���...
	//���ڷ� ���޹��� cartbean��ü�� �ٽ�!! �Ʒ��� push(SuCartBean cartbean)�޼ҵ��� ���ڷ� ���޹޴´�.
	//cartbean��ü�� ���ڷ� ���޹޾�.. ArrayList��ü(īƮ��ü)��ü�� ��ǰ�� �߰��ϴ� �޼ҵ��ε�..
	//�ϴ��� :
	//������ ��ǰ�� �ִٸ� ������ �����ϰ� ������ ��ǰ�� �ִ��� �˾Ƴ��� ���� ���� true�� ������ �ְ�...
	//������ ��ǰ�� ���� ��쿡�� ��ǰ�� �߰� �����ִ� ���� �ϴ� �޼ҵ�
	public void push(SuCartBean cartbean){
		//������ ��ǰ�� �ִ����� �˾Ƴ��� ����
		boolean alreadysutool = false;
		//itemlist�ȿ� �����͸� �ݺ����� ���鼭 ������ �ִ� �����Ͱ� �ִ��� �񱳸�
		for(SuCartBean suCartBean : itemlist){//Ȯ�� for�� ArrayList�� cartbean��ü�� �ִ� ��ŭ �ݺ�
			//ArrayList(īƮ, ��ٱ���)�� ��� �ִ� ���۾� ���� ��ȣ��..
			//push()�޼ҵ��� �Ű������� �Ѿ�� cartbean��ü(īƮ�� ���� ���۾� ���� ��ǰ ������ü)��
			//������ȣ�� ������...
			if(cartbean.getSuno() == suCartBean.getSuno()){
				//������ ��ǰ�� ���� �ϱ⿡ ������ ����
				suCartBean.setSuqty(suCartBean.getSuqty()+cartbean.getSuqty());
				//������ ��ǰ�� ������.. true��
				alreadysutool = true;
				break;
			}
			
		}//for�� ��
		
		//������ ��ǰ�� ���� ��쿡�� ��ǰ�� �߰� �����־�� �մϴ�.
		if(alreadysutool==false){
			//ArrayList��ü(īƮ��ü)�� ��ǰ �߰�
			itemlist.add(cartbean);
		}
	}//push�޼ҵ� ��
	
	//īƮ�� �ϳ��� ��ǰ�� �������ִ� �޼ҵ�
	public void deleteCart(int suno) {
		//�ݺ����� ���鼭 �ش� ������ ��ȣ�� �������� ����
		for(SuCartBean suCartBean : itemlist){
			//�����ϰ��� �ϴ� ��ȣ�� �ִٸ� itemlist ���� �ش� ��ǰ�� ����
			if(suCartBean.getSuno() == suno){
				itemlist.remove(suCartBean);
				break;
			}
		}
		
	}
	
	//īƮ�� ��� ��ǰ�� ���� ���ִ� �޼ҵ�
	//īƮ ����
	public void clearCart() {
		itemlist.removeAll(itemlist);
		
	}

}//cartŬ���� ��
