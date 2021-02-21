package shop.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CartBean implements Serializable{
	private List<ProductVO> cartList
	=new ArrayList<>();

/**��ٱ��Ͽ� ��ǰ�� �߰��ϴ� �޼ҵ�*/
public void addProduct(int pnum, ProductVO item) {
	/* 1. ���� �߰��Ϸ��� ��ǰ�� �̹� ��ٱ���(cartList)
	 * �� �ִ��� �˻��Ѵ�.
	 * 1) �̹� �ִ� ��ǰ�̶�� => �̹� �߰��� ��ǰ�Դϴ� => �ŷ���? ó���Ұ�
	 * 
	 * */
	for(ProductVO pd:cartList) {
		int pnum2=pd.getPnum();
		if(pnum2==pnum) {
			//�̹� ��� ��ǰ�̶��
			System.out.println("ó����");
			return;
		}//if--
		
	}//for------
	
	/*2. �׷��� �ʰ� ���Ӱ� �߰��ϴ� ��ǰ�̶��
	 *   ��ٱ���(cartList)�� ��������*/
	if(item!=null) {
		
		cartList.add(item);
	}
	
	
	
	
}

public List<ProductVO> getCartList() {
	return cartList;
}//----

/*��ٱ��� ��ǰ�� �Ѿװ� ������Ʈ�� ��ȯ�ϴ� �޼ҵ�*/
public Map<String,Integer> getCartTotal(){
	Map<String,Integer> map=new HashMap<>();
	int totalPrice=0;
	for(ProductVO pd:cartList) {
		totalPrice+=pd.getTotalPrice();
	}//for----
	map.put("TotalPrice", totalPrice);
	return map;
}//------------------

/**��ٱ��Ͽ��� Ư�� ��ǰ�� �����ϴ� �޼ҵ�*/
public void removeProduct(int pnum) {
	//�ݺ��� ���鼭 cartList���� ��ǰ�� �����ͼ�
	//�ش� ��ǰ�� ��ǰ��ȣ�� �����Ϸ��� ��ǰ��ȣ�� ������ üũ
	//���ٸ� cartList���� �ش� ��ǰ ����
	for(ProductVO pd:cartList) {
		
		if(pd.getPnum()==pnum) {
			cartList.remove(pd);
			break;
		}//if
	}///for
}///

}
